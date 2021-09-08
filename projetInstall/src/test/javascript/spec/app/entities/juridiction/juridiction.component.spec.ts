/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import JuridictionComponent from '@/entities/juridiction/juridiction.vue';
import JuridictionClass from '@/entities/juridiction/juridiction.component';
import JuridictionService from '@/entities/juridiction/juridiction.service';

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
  describe('Juridiction Management Component', () => {
    let wrapper: Wrapper<JuridictionClass>;
    let comp: JuridictionClass;
    let juridictionServiceStub: SinonStubbedInstance<JuridictionService>;

    beforeEach(() => {
      juridictionServiceStub = sinon.createStubInstance<JuridictionService>(JuridictionService);
      juridictionServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<JuridictionClass>(JuridictionComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          juridictionService: () => juridictionServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      juridictionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllJuridictions();
      await comp.$nextTick();

      // THEN
      expect(juridictionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.juridictions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      juridictionServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeJuridiction();
      await comp.$nextTick();

      // THEN
      expect(juridictionServiceStub.delete.called).toBeTruthy();
      expect(juridictionServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
