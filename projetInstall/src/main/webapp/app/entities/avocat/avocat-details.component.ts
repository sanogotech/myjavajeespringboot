import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAvocat } from '@/shared/model/avocat.model';
import AvocatService from './avocat.service';

@Component
export default class AvocatDetails extends Vue {
  @Inject('avocatService') private avocatService: () => AvocatService;
  public avocat: IAvocat = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.avocatId) {
        vm.retrieveAvocat(to.params.avocatId);
      }
    });
  }

  public retrieveAvocat(avocatId) {
    this.avocatService()
      .find(avocatId)
      .then(res => {
        this.avocat = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
