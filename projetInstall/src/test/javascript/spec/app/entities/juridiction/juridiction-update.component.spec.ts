/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import JuridictionUpdateComponent from '@/entities/juridiction/juridiction-update.vue';
import JuridictionClass from '@/entities/juridiction/juridiction-update.component';
import JuridictionService from '@/entities/juridiction/juridiction.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Juridiction Management Update Component', () => {
    let wrapper: Wrapper<JuridictionClass>;
    let comp: JuridictionClass;
    let juridictionServiceStub: SinonStubbedInstance<JuridictionService>;

    beforeEach(() => {
      juridictionServiceStub = sinon.createStubInstance<JuridictionService>(JuridictionService);

      wrapper = shallowMount<JuridictionClass>(JuridictionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          juridictionService: () => juridictionServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.juridiction = entity;
        juridictionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(juridictionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.juridiction = entity;
        juridictionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(juridictionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
