import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IJuridiction, Juridiction } from '@/shared/model/juridiction.model';
import JuridictionService from './juridiction.service';

const validations: any = {
  juridiction: {
    nom: {
      required,
      minLength: minLength(2),
    },
    ville: {
      required,
      minLength: minLength(2),
    },
  },
};

@Component({
  validations,
})
export default class JuridictionUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('juridictionService') private juridictionService: () => JuridictionService;
  public juridiction: IJuridiction = new Juridiction();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.juridictionId) {
        vm.retrieveJuridiction(to.params.juridictionId);
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
    if (this.juridiction.id) {
      this.juridictionService()
        .update(this.juridiction)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.juridiction.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.juridictionService()
        .create(this.juridiction)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.juridiction.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveJuridiction(juridictionId): void {
    this.juridictionService()
      .find(juridictionId)
      .then(res => {
        this.juridiction = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
