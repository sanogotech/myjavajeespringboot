import { Component, Vue, Inject } from 'vue-property-decorator';

import { IJuridiction } from '@/shared/model/juridiction.model';
import JuridictionService from './juridiction.service';

@Component
export default class JuridictionDetails extends Vue {
  @Inject('juridictionService') private juridictionService: () => JuridictionService;
  public juridiction: IJuridiction = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.juridictionId) {
        vm.retrieveJuridiction(to.params.juridictionId);
      }
    });
  }

  public retrieveJuridiction(juridictionId) {
    this.juridictionService()
      .find(juridictionId)
      .then(res => {
        this.juridiction = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
