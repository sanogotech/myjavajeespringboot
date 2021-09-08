/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ContentieuxDetailComponent from '@/entities/contentieux/contentieux-details.vue';
import ContentieuxClass from '@/entities/contentieux/contentieux-details.component';
import ContentieuxService from '@/entities/contentieux/contentieux.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Contentieux Management Detail Component', () => {
    let wrapper: Wrapper<ContentieuxClass>;
    let comp: ContentieuxClass;
    let contentieuxServiceStub: SinonStubbedInstance<ContentieuxService>;

    beforeEach(() => {
      contentieuxServiceStub = sinon.createStubInstance<ContentieuxService>(ContentieuxService);

      wrapper = shallowMount<ContentieuxClass>(ContentieuxDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { contentieuxService: () => contentieuxServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundContentieux = { id: 123 };
        contentieuxServiceStub.find.resolves(foundContentieux);

        // WHEN
        comp.retrieveContentieux(123);
        await comp.$nextTick();

        // THEN
        expect(comp.contentieux).toBe(foundContentieux);
      });
    });
  });
});
