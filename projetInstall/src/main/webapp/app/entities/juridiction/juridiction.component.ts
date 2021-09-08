import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IJuridiction } from '@/shared/model/juridiction.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import JuridictionService from './juridiction.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Juridiction extends mixins(AlertMixin) {
  @Inject('juridictionService') private juridictionService: () => JuridictionService;
  private removeId: number = null;

  public juridictions: IJuridiction[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllJuridictions();
  }

  public clear(): void {
    this.retrieveAllJuridictions();
  }

  public retrieveAllJuridictions(): void {
    this.isFetching = true;

    this.juridictionService()
      .retrieve()
      .then(
        res => {
          this.juridictions = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IJuridiction): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeJuridiction(): void {
    this.juridictionService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('audiencierApp.juridiction.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllJuridictions();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
