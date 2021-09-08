/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import JuridictionDetailComponent from '@/entities/juridiction/juridiction-details.vue';
import JuridictionClass from '@/entities/juridiction/juridiction-details.component';
import JuridictionService from '@/entities/juridiction/juridiction.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Juridiction Management Detail Component', () => {
    let wrapper: Wrapper<JuridictionClass>;
    let comp: JuridictionClass;
    let juridictionServiceStub: SinonStubbedInstance<JuridictionService>;

    beforeEach(() => {
      juridictionServiceStub = sinon.createStubInstance<JuridictionService>(JuridictionService);

      wrapper = shallowMount<JuridictionClass>(JuridictionDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { juridictionService: () => juridictionServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundJuridiction = { id: 123 };
        juridictionServiceStub.find.resolves(foundJuridiction);

        // WHEN
        comp.retrieveJuridiction(123);
        await comp.$nextTick();

        // THEN
        expect(comp.juridiction).toBe(foundJuridiction);
      });
    });
  });
});
