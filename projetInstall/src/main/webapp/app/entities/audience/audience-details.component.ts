import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IAudience } from '@/shared/model/audience.model';
import AudienceService from './audience.service';

@Component
export default class AudienceDetails extends mixins(JhiDataUtils) {
  @Inject('audienceService') private audienceService: () => AudienceService;
  public audience: IAudience = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.audienceId) {
        vm.retrieveAudience(to.params.audienceId);
      }
    });
  }

  public retrieveAudience(audienceId) {
    this.audienceService()
      .find(audienceId)
      .then(res => {
        this.audience = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
