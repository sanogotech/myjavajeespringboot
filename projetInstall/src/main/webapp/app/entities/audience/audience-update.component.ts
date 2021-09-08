import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import ContentieuxService from '../contentieux/contentieux.service';
import { IContentieux } from '@/shared/model/contentieux.model';

import AlertService from '@/shared/alert/alert.service';
import { IAudience, Audience } from '@/shared/model/audience.model';
import AudienceService from './audience.service';

const validations: any = {
  audience: {
    mois: {
      required,
    },
    stade: {
      required,
    },
    dateAudience: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class AudienceUpdate extends mixins(JhiDataUtils) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('audienceService') private audienceService: () => AudienceService;
  public audience: IAudience = new Audience();

  @Inject('contentieuxService') private contentieuxService: () => ContentieuxService;

  public contentieuxes: IContentieux[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.audienceId) {
        vm.retrieveAudience(to.params.audienceId);
      }
	  
	  //TODO  NeW  :  get  contentieuxId
	  if (to.params.contentieuxId) {
		    console.log("ParamContentieuxId = " + to.params.contentieuxId)
			vm.initRelationshipsWithContentieux(to.params.contentieuxId);
	  }
	  
      //vm.initRelationships();
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
    if (this.audience.id) {
      this.audienceService()
        .update(this.audience)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.audience.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.audienceService()
        .create(this.audience)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('audiencierApp.audience.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveAudience(audienceId): void {
    this.audienceService()
      .find(audienceId)
      .then(res => {
        this.audience = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.contentieuxService()
      .retrieve()
      .then(res => {
        this.contentieuxes = res.data;
      });
  }
  
  //TODO  New 
   public initRelationshipsWithContentieux(contentieuxId): void {
    this.contentieuxService()
      //.find(contentieuxId)
	  .retrieveByContentieux(contentieuxId)
      .then(res => {
        this.contentieuxes = res.data;
		console.log("--11 contentieuxes= " + this.contentieuxes);
      });
	  
	  console.log("--22 contentieuxes= " + this.contentieuxes);
	  
  }
}
