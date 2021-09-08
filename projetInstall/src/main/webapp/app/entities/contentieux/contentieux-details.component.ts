import { Component, Vue, Inject } from 'vue-property-decorator';

import { IContentieux } from '@/shared/model/contentieux.model';
import ContentieuxService from './contentieux.service';

//New
import { IAudience } from '@/shared/model/audience.model';

//New ++
import AudienceService from '../audience/audience.service';

//New
import { mixins } from 'vue-class-component';

import Vue2Filters from 'vue2-filters';
import AlertMixin from '@/shared/alert/alert.mixin';

import JhiDataUtils from '@/shared/data/data-utils.service';
///

import VueSimpleAlert from "vue-simple-alert";

Vue.use(VueSimpleAlert);

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ContentieuxDetails extends Vue {
  @Inject('contentieuxService') private contentieuxService: () => ContentieuxService;
  public contentieux: IContentieux = {};

  //New ++
  @Inject('audienceService') private audienceService: () => AudienceService;
  public audience: IAudience = {};

  // New ++
  public audiences: IAudience[] = [];

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contentieuxId) {
        vm.retrieveContentieux(to.params.contentieuxId);
      }
    });
  }

  public retrieveContentieux(contentieuxId) {
    this.contentieuxService()
      .find(contentieuxId)
      .then(res => {
        this.contentieux = res;
        this.retrieveAudienceByContentieuxId(contentieuxId);
      });
  }

  // New ++
  public retrieveAudienceByContentieuxId(contentieuxId) {
    console.log('demo audience ' + contentieuxId);

    this.audienceService()
      .findByContentieuxId(contentieuxId)
      .then(res => {
        this.audiences = res;

      });

  }
  
  //TODO  ++
    public prepareRemove(instance: IAudience,contentieuxId: number): void {
    this.removeId = instance.id;
	console.log('--- Call  prepareRemove  Audience  ' + instance.id);

	this.$confirm("Etes-vous certain de vouloir supprimer le Audience " + instance.id +" ?").then(() => {
			//Remove Audience
			this.removeAudience(contentieuxId);
			
			//vm.$forceUpdate();
	});
	
	
  }
  
  
    public removeAudience(contentieuxId: number): void {
    this.audienceService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('audiencierApp.audience.deleted', { param: this.removeId });
        this.removeId = null;
		//Refresh - ReloadPage
       this.retrieveAudienceByContentieuxId(contentieuxId);
      });
  }


  public previousState() {
    this.$router.go(-1);
  }
}
