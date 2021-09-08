import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class RequerantUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('audiencierApp.requerant.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nomInput: ElementFinder = element(by.css('input#requerant-nom'));

  prenomInput: ElementFinder = element(by.css('input#requerant-prenom'));

  numCNIInput: ElementFinder = element(by.css('input#requerant-numCNI'));
}
