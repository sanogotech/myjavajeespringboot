/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AvocatComponent from '@/entities/avocat/avocat.vue';
import AvocatClass from '@/entities/avocat/avocat.component';
import AvocatService from '@/entities/avocat/avocat.service';

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
  describe('Avocat Management Component', () => {
    let wrapper: Wrapper<AvocatClass>;
    let comp: AvocatClass;
    let avocatServiceStub: SinonStubbedInstance<AvocatService>;

    beforeEach(() => {
      avocatServiceStub = sinon.createStubInstance<AvocatService>(AvocatService);
      avocatServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AvocatClass>(AvocatComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          avocatService: () => avocatServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      avocatServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAvocats();
      await comp.$nextTick();

      // THEN
      expect(avocatServiceStub.retrieve.called).toBeTruthy();
      expect(comp.avocats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      avocatServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeAvocat();
      await comp.$nextTick();

      // THEN
      expect(avocatServiceStub.delete.called).toBeTruthy();
      expect(avocatServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
