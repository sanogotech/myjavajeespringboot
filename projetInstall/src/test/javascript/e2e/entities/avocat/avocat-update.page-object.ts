import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class AvocatUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('audiencierApp.avocat.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomInput: ElementFinder = element(by.css('input#avocat-nom'));

  prenomInput: ElementFinder = element(by.css('input#avocat-prenom'));

  emailInput: ElementFinder = element(by.css('input#avocat-email'));

  phoneInput: ElementFinder = element(by.css('input#avocat-phone'));
}
