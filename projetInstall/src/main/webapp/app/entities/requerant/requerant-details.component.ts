import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRequerant } from '@/shared/model/requerant.model';
import RequerantService from './requerant.service';

@Component
export default class RequerantDetails extends Vue {
  @Inject('requerantService') private requerantService: () => RequerantService;
  public requerant: IRequerant = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.requerantId) {
        vm.retrieveRequerant(to.params.requerantId);
      }
    });
  }

  public retrieveRequerant(requerantId) {
    this.requerantService()
      .find(requerantId)
      .then(res => {
        this.requerant = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
