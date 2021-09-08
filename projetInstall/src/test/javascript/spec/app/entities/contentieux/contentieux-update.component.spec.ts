/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ContentieuxUpdateComponent from '@/entities/contentieux/contentieux-update.vue';
import ContentieuxClass from '@/entities/contentieux/contentieux-update.component';
import ContentieuxService from '@/entities/contentieux/contentieux.service';

import RequerantService from '@/entities/requerant/requerant.service';

import AvocatService from '@/entities/avocat/avocat.service';

import JuridictionService from '@/entities/juridiction/juridiction.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Contentieux Management Update Component', () => {
    let wrapper: Wrapper<ContentieuxClass>;
    let comp: ContentieuxClass;
    let contentieuxServiceStub: SinonStubbedInstance<ContentieuxService>;

    beforeEach(() => {
      contentieuxServiceStub = sinon.createStubInstance<ContentieuxService>(ContentieuxService);

      wrapper = shallowMount<ContentieuxClass>(ContentieuxUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          contentieuxService: () => contentieuxServiceStub,

          requerantService: () => new RequerantService(),

          avocatService: () => new AvocatService(),

          juridictionService: () => new JuridictionService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.contentieux = entity;
        contentieuxServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contentieuxServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.contentieux = entity;
        contentieuxServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contentieuxServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
