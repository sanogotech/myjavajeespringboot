/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import RequerantComponent from '@/entities/requerant/requerant.vue';
import RequerantClass from '@/entities/requerant/requerant.component';
import RequerantService from '@/entities/requerant/requerant.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Requerant Management Component', () => {
    let wrapper: Wrapper<RequerantClass>;
    let comp: RequerantClass;
    let requerantServiceStub: SinonStubbedInstance<RequerantService>;

    beforeEach(() => {
      requerantServiceStub = sinon.createStubInstance<RequerantService>(RequerantService);
      requerantServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RequerantClass>(RequerantComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          requerantService: () => requerantServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      requerantServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRequerants();
      await comp.$nextTick();

      // THEN
      expect(requerantServiceStub.retrieve.called).toBeTruthy();
      expect(comp.requerants[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      requerantServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeRequerant();
      await comp.$nextTick();

      // THEN
      expect(requerantServiceStub.delete.called).toBeTruthy();
      expect(requerantServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
