/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import AvocatUpdateComponent from '@/entities/avocat/avocat-update.vue';
import AvocatClass from '@/entities/avocat/avocat-update.component';
import AvocatService from '@/entities/avocat/avocat.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Avocat Management Update Component', () => {
    let wrapper: Wrapper<AvocatClass>;
    let comp: AvocatClass;
    let avocatServiceStub: SinonStubbedInstance<AvocatService>;

    beforeEach(() => {
      avocatServiceStub = sinon.createStubInstance<AvocatService>(AvocatService);

      wrapper = shallowMount<AvocatClass>(AvocatUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          avocatService: () => avocatServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.avocat = entity;
        avocatServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(avocatServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.avocat = entity;
        avocatServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(avocatServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
