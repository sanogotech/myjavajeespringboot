/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AvocatDetailComponent from '@/entities/avocat/avocat-details.vue';
import AvocatClass from '@/entities/avocat/avocat-details.component';
import AvocatService from '@/entities/avocat/avocat.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Avocat Management Detail Component', () => {
    let wrapper: Wrapper<AvocatClass>;
    let comp: AvocatClass;
    let avocatServiceStub: SinonStubbedInstance<AvocatService>;

    beforeEach(() => {
      avocatServiceStub = sinon.createStubInstance<AvocatService>(AvocatService);

      wrapper = shallowMount<AvocatClass>(AvocatDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { avocatService: () => avocatServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAvocat = { id: 123 };
        avocatServiceStub.find.resolves(foundAvocat);

        // WHEN
        comp.retrieveAvocat(123);
        await comp.$nextTick();

        // THEN
        expect(comp.avocat).toBe(foundAvocat);
      });
    });
  });
});
