import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import RequerantService from '../requerant/requerant.service';
import { IRequerant } from '@/shared/model/requerant.model';

import AvocatService from '../avocat/avocat.service';
import { IAvocat } from '@/shared/model/avocat.model';

import JuridictionService from '../juridiction/juridiction.service';
import { IJuridiction } from '@/shared/model/juridiction.model';

import AlertService from '@/shared/alert/alert.service';
import { IContentieux, Contentieux } from '@/shared/model/contentieux.model';
import ContentieuxService from './contentieux.service';

const validations: any = {
  contentieux: {
    entite: {},
    refContentieux: {},
    datePremiereAudience: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class ContentieuxUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contentieuxService') private contentieuxService: () => ContentieuxService;
  public contentieux: IContentieux = new Contentieux();

  @Inject('requerantService') private requerantService: () => RequerantService;

  public requerants: IRequerant[] = [];

  @Inject('avocatService') private avocatService: () => AvocatService;

  public avocats: IAvocat[] = [];

  @Inject('juridictionService') private juridictionService: () => JuridictionService;

  public juridictions: IJuridiction[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contentieuxId) {
        vm.retrieveContentieux(to.params.contentieuxId);
      }
      vm.initRelationships();
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
    if (this.contentieux.id) {
      this.contentieuxService()
        .update(this.contentieux)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.contentieux.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.contentieuxService()
        .create(this.contentieux)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.contentieux.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveContentieux(contentieuxId): void {
    this.contentieuxService()
      .find(contentieuxId)
      .then(res => {
        this.contentieux = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.requerantService()
      .retrieve()
      .then(res => {
        this.requerants = res.data;
      });
    this.avocatService()
      .retrieve()
      .then(res => {
        this.avocats = res.data;
      });
    this.juridictionService()
      .retrieve()
      .then(res => {
        this.juridictions = res.data;
      });
  }
}
