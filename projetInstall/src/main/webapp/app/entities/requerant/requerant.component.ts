import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRequerant } from '@/shared/model/requerant.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import RequerantService from './requerant.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Requerant extends mixins(AlertMixin) {
  @Inject('requerantService') private requerantService: () => RequerantService;
  private removeId: number = null;

  public requerants: IRequerant[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRequerants();
  }

  public clear(): void {
    this.retrieveAllRequerants();
  }

  public retrieveAllRequerants(): void {
    this.isFetching = true;

    this.requerantService()
      .retrieve()
      .then(
        res => {
          this.requerants = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IRequerant): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRequerant(): void {
    this.requerantService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('audiencierApp.requerant.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllRequerants();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
