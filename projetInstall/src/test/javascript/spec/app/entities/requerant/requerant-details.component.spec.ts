/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import RequerantDetailComponent from '@/entities/requerant/requerant-details.vue';
import RequerantClass from '@/entities/requerant/requerant-details.component';
import RequerantService from '@/entities/requerant/requerant.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Requerant Management Detail Component', () => {
    let wrapper: Wrapper<RequerantClass>;
    let comp: RequerantClass;
    let requerantServiceStub: SinonStubbedInstance<RequerantService>;

    beforeEach(() => {
      requerantServiceStub = sinon.createStubInstance<RequerantService>(RequerantService);

      wrapper = shallowMount<RequerantClass>(RequerantDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { requerantService: () => requerantServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRequerant = { id: 123 };
        requerantServiceStub.find.resolves(foundRequerant);

        // WHEN
        comp.retrieveRequerant(123);
        await comp.$nextTick();

        // THEN
        expect(comp.requerant).toBe(foundRequerant);
      });
    });
  });
});
