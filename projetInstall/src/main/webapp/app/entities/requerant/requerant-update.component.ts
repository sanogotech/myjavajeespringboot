import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IRequerant, Requerant } from '@/shared/model/requerant.model';
import RequerantService from './requerant.service';

const validations: any = {
  requerant: {
    nom: {
      required,
      minLength: minLength(2),
    },
    prenom: {
      required,
      minLength: minLength(2),
    },
    numCNI: {
      required,
      minLength: minLength(2),
    },
  },
};

@Component({
  validations,
})
export default class RequerantUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('requerantService') private requerantService: () => RequerantService;
  public requerant: IRequerant = new Requerant();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.requerantId) {
        vm.retrieveRequerant(to.params.requerantId);
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
    if (this.requerant.id) {
      this.requerantService()
        .update(this.requerant)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.requerant.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.requerantService()
        .create(this.requerant)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.requerant.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveRequerant(requerantId): void {
    this.requerantService()
      .find(requerantId)
      .then(res => {
        this.requerant = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
