/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AudienceUpdateComponent from '@/entities/audience/audience-update.vue';
import AudienceClass from '@/entities/audience/audience-update.component';
import AudienceService from '@/entities/audience/audience.service';

import ContentieuxService from '@/entities/contentieux/contentieux.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Audience Management Update Component', () => {
    let wrapper: Wrapper<AudienceClass>;
    let comp: AudienceClass;
    let audienceServiceStub: SinonStubbedInstance<AudienceService>;

    beforeEach(() => {
      audienceServiceStub = sinon.createStubInstance<AudienceService>(AudienceService);

      wrapper = shallowMount<AudienceClass>(AudienceUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          audienceService: () => audienceServiceStub,

          contentieuxService: () => new ContentieuxService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.audience = entity;
        audienceServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(audienceServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.audience = entity;
        audienceServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(audienceServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
