/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ContentieuxComponent from '@/entities/contentieux/contentieux.vue';
import ContentieuxClass from '@/entities/contentieux/contentieux.component';
import ContentieuxService from '@/entities/contentieux/contentieux.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
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
  describe('Contentieux Management Component', () => {
    let wrapper: Wrapper<ContentieuxClass>;
    let comp: ContentieuxClass;
    let contentieuxServiceStub: SinonStubbedInstance<ContentieuxService>;

    beforeEach(() => {
      contentieuxServiceStub = sinon.createStubInstance<ContentieuxService>(ContentieuxService);
      contentieuxServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ContentieuxClass>(ContentieuxComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          contentieuxService: () => contentieuxServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      contentieuxServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllContentieuxs();
      await comp.$nextTick();

      // THEN
      expect(contentieuxServiceStub.retrieve.called).toBeTruthy();
      expect(comp.contentieuxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      contentieuxServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(contentieuxServiceStub.retrieve.called).toBeTruthy();
      expect(comp.contentieuxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      contentieuxServiceStub.retrieve.reset();
      contentieuxServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(contentieuxServiceStub.retrieve.callCount).toEqual(2);
      expect(comp.page).toEqual(1);
      expect(comp.contentieuxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      contentieuxServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeContentieux();
      await comp.$nextTick();

      // THEN
      expect(contentieuxServiceStub.delete.called).toBeTruthy();
      expect(contentieuxServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
