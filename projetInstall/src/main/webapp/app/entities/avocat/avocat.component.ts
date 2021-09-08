import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAvocat } from '@/shared/model/avocat.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import AvocatService from './avocat.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Avocat extends mixins(AlertMixin) {
  @Inject('avocatService') private avocatService: () => AvocatService;
  private removeId: number = null;

  public avocats: IAvocat[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAvocats();
  }

  public clear(): void {
    this.retrieveAllAvocats();
  }

  public retrieveAllAvocats(): void {
    this.isFetching = true;

    this.avocatService()
      .retrieve()
      .then(
        res => {
          this.avocats = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IAvocat): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeAvocat(): void {
    this.avocatService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('audiencierApp.avocat.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllAvocats();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
