import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class JuridictionUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('audiencierApp.juridiction.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomInput: ElementFinder = element(by.css('input#juridiction-nom'));

  villeInput: ElementFinder = element(by.css('input#juridiction-ville'));
}
