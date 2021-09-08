import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Audience = () => import('@/entities/audience/audience.vue');
// prettier-ignore
const AudienceUpdate = () => import('@/entities/audience/audience-update.vue');
// prettier-ignore
const AudienceDetails = () => import('@/entities/audience/audience-details.vue');
// prettier-ignore
const Requerant = () => import('@/entities/requerant/requerant.vue');
// prettier-ignore
const RequerantUpdate = () => import('@/entities/requerant/requerant-update.vue');
// prettier-ignore
const RequerantDetails = () => import('@/entities/requerant/requerant-details.vue');
// prettier-ignore
const Juridiction = () => import('@/entities/juridiction/juridiction.vue');
// prettier-ignore
const JuridictionUpdate = () => import('@/entities/juridiction/juridiction-update.vue');
// prettier-ignore
const JuridictionDetails = () => import('@/entities/juridiction/juridiction-details.vue');
// prettier-ignore
const Avocat = () => import('@/entities/avocat/avocat.vue');
// prettier-ignore
const AvocatUpdate = () => import('@/entities/avocat/avocat-update.vue');
// prettier-ignore
const AvocatDetails = () => import('@/entities/avocat/avocat-details.vue');
// prettier-ignore
const Contentieux = () => import('@/entities/contentieux/contentieux.vue');
// prettier-ignore
const ContentieuxUpdate = () => import('@/entities/contentieux/contentieux-update.vue');
// prettier-ignore
const ContentieuxDetails = () => import('@/entities/contentieux/contentieux-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/audience',
    name: 'Audience',
    component: Audience,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/new',
    name: 'AudienceCreate',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/edit',
    name: 'AudienceEdit',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/view',
    name: 'AudienceView',
    component: AudienceDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/requerant',
    name: 'Requerant',
    component: Requerant,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/requerant/new',
    name: 'RequerantCreate',
    component: RequerantUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/requerant/:requerantId/edit',
    name: 'RequerantEdit',
    component: RequerantUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/requerant/:requerantId/view',
    name: 'RequerantView',
    component: RequerantDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/juridiction',
    name: 'Juridiction',
    component: Juridiction,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/juridiction/new',
    name: 'JuridictionCreate',
    component: JuridictionUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/juridiction/:juridictionId/edit',
    name: 'JuridictionEdit',
    component: JuridictionUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/juridiction/:juridictionId/view',
    name: 'JuridictionView',
    component: JuridictionDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/avocat',
    name: 'Avocat',
    component: Avocat,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/avocat/new',
    name: 'AvocatCreate',
    component: AvocatUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/avocat/:avocatId/edit',
    name: 'AvocatEdit',
    component: AvocatUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/avocat/:avocatId/view',
    name: 'AvocatView',
    component: AvocatDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/audience',
    name: 'Audience',
    component: Audience,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/new',
    name: 'AudienceCreate',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  
  // TODO New
  {
    path: '/audience/:contentieuxId/new',
    name: 'AudienceCreateWithContentieux',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/edit',
    name: 'AudienceEdit',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/view',
    name: 'AudienceView',
    component: AudienceDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/audience',
    name: 'Audience',
    component: Audience,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/new',
    name: 'AudienceCreate',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/edit',
    name: 'AudienceEdit',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/view',
    name: 'AudienceView',
    component: AudienceDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/contentieux',
    name: 'Contentieux',
    component: Contentieux,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/new',
    name: 'ContentieuxCreate',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/edit',
    name: 'ContentieuxEdit',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/view',
    name: 'ContentieuxView',
    component: ContentieuxDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience',
    name: 'Audience',
    component: Audience,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/new',
    name: 'AudienceCreate',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/edit',
    name: 'AudienceEdit',
    component: AudienceUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/audience/:audienceId/view',
    name: 'AudienceView',
    component: AudienceDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/contentieux',
    name: 'Contentieux',
    component: Contentieux,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/new',
    name: 'ContentieuxCreate',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/edit',
    name: 'ContentieuxEdit',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/view',
    name: 'ContentieuxView',
    component: ContentieuxDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/contentieux',
    name: 'Contentieux',
    component: Contentieux,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/new',
    name: 'ContentieuxCreate',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/edit',
    name: 'ContentieuxEdit',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/view',
    name: 'ContentieuxView',
    component: ContentieuxDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/contentieux',
    name: 'Contentieux',
    component: Contentieux,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/new',
    name: 'ContentieuxCreate',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/edit',
    name: 'ContentieuxEdit',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/view',
    name: 'ContentieuxView',
    component: ContentieuxDetails,
    meta: { authorities: [Authority.USER] },
  },

  {
    path: '/contentieux',
    name: 'Contentieux',
    component: Contentieux,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/new',
    name: 'ContentieuxCreate',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/edit',
    name: 'ContentieuxEdit',
    component: ContentieuxUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contentieux/:contentieuxId/view',
    name: 'ContentieuxView',
    component: ContentieuxDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
