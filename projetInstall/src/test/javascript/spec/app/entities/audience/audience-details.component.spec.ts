/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AudienceDetailComponent from '@/entities/audience/audience-details.vue';
import AudienceClass from '@/entities/audience/audience-details.component';
import AudienceService from '@/entities/audience/audience.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Audience Management Detail Component', () => {
    let wrapper: Wrapper<AudienceClass>;
    let comp: AudienceClass;
    let audienceServiceStub: SinonStubbedInstance<AudienceService>;

    beforeEach(() => {
      audienceServiceStub = sinon.createStubInstance<AudienceService>(AudienceService);

      wrapper = shallowMount<AudienceClass>(AudienceDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { audienceService: () => audienceServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAudience = { id: 123 };
        audienceServiceStub.find.resolves(foundAudience);

        // WHEN
        comp.retrieveAudience(123);
        await comp.$nextTick();

        // THEN
        expect(comp.audience).toBe(foundAudience);
      });
    });
  });
});
