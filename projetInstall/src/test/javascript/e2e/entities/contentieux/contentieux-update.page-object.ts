import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class ContentieuxUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('audiencierApp.contentieux.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  entiteSelect = element(by.css('select#contentieux-entite'));

  refContentieuxInput: ElementFinder = element(by.css('input#contentieux-refContentieux'));

  datePremiereAudienceInput: ElementFinder = element(by.css('input#contentieux-datePremiereAudience'));

  requerantSelect = element(by.css('select#contentieux-requerant'));

  avocatSelect = element(by.css('select#contentieux-avocat'));

  juridictionSelect = element(by.css('select#contentieux-juridiction'));
}
