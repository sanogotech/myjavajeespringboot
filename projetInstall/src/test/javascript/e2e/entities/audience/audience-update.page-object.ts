import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class AudienceUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('audiencierApp.audience.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  moisSelect = element(by.css('select#audience-mois'));

  stadeInput: ElementFinder = element(by.css('textarea#audience-stade'));

  dateAudienceInput: ElementFinder = element(by.css('input#audience-dateAudience'));

  contentieuxSelect = element(by.css('select#audience-contentieux'));
}
