import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IAvocat, Avocat } from '@/shared/model/avocat.model';
import AvocatService from './avocat.service';

const validations: any = {
  avocat: {
    nom: {
      required,
      minLength: minLength(2),
    },
    prenom: {
      required,
      minLength: minLength(2),
    },
    email: {
      required,
    },
    phone: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class AvocatUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('avocatService') private avocatService: () => AvocatService;
  public avocat: IAvocat = new Avocat();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.avocatId) {
        vm.retrieveAvocat(to.params.avocatId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.avocat.id) {
      this.avocatService()
        .update(this.avocat)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.avocat.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.avocatService()
        .create(this.avocat)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.avocat.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveAvocat(avocatId): void {
    this.avocatService()
      .find(avocatId)
      .then(res => {
        this.avocat = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
