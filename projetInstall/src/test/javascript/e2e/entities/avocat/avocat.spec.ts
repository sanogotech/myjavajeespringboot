/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AvocatComponentsPage, { AvocatDeleteDialog } from './avocat.page-object';
import AvocatUpdatePage from './avocat-update.page-object';
import AvocatDetailsPage from './avocat-details.page-object';

import {
  clear,
  click,
  getRecordsCount,
  isVisible,
  selectLastOption,
  waitUntilAllDisplayed,
  waitUntilAnyDisplayed,
  waitUntilCount,
  waitUntilDisplayed,
  waitUntilHidden,
} from '../../util/utils';

const expect = chai.expect;

describe('Avocat e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: AvocatUpdatePage;
  let detailsPage: AvocatDetailsPage;
  let listPage: AvocatComponentsPage;
  let deleteDialog: AvocatDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Avocats', async () => {
    await navBarPage.getEntityPage('avocat');
    listPage = new AvocatComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Avocat page', async () => {
      await listPage.createButton.click();
      updatePage = new AvocatUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/audiencierApp.avocat.home.createOrEditLabel/);
    });

    it('should create and save Avocats', async () => {
      await updatePage.nomInput.sendKeys('nom');
      expect(await updatePage.nomInput.getAttribute('value')).to.match(/nom/);

      await updatePage.prenomInput.sendKeys('prenom');
      expect(await updatePage.prenomInput.getAttribute('value')).to.match(/prenom/);

      await updatePage.emailInput.sendKeys('email');
      expect(await updatePage.emailInput.getAttribute('value')).to.match(/email/);

      await updatePage.phoneInput.sendKeys('phone');
      expect(await updatePage.phoneInput.getAttribute('value')).to.match(/phone/);

      expect(await updatePage.saveButton.isEnabled()).to.be.true;
      await updatePage.saveButton.click();

      await waitUntilHidden(updatePage.saveButton);
      expect(await isVisible(updatePage.saveButton)).to.be.false;

      await waitUntilDisplayed(listPage.successAlert);
      expect(await listPage.successAlert.isDisplayed()).to.be.true;

      await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      expect(await listPage.records.count()).to.eq(beforeRecordsCount + 1);
    });

    describe('Details, Update, Delete flow', () => {
      after(async () => {
        const deleteButton = listPage.getDeleteButton(listPage.records.last());
        await click(deleteButton);

        deleteDialog = new AvocatDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/audiencierApp.avocat.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Avocat page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.last());
        await click(detailsButton);

        detailsPage = new AvocatDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Avocat page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.last());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.nomInput.clear();
        await updatePage.nomInput.sendKeys('modified');
        expect(await updatePage.nomInput.getAttribute('value')).to.match(/modified/);

        await updatePage.prenomInput.clear();
        await updatePage.prenomInput.sendKeys('modified');
        expect(await updatePage.prenomInput.getAttribute('value')).to.match(/modified/);

        await updatePage.emailInput.clear();
        await updatePage.emailInput.sendKeys('modified');
        expect(await updatePage.emailInput.getAttribute('value')).to.match(/modified/);

        await updatePage.phoneInput.clear();
        await updatePage.phoneInput.sendKeys('modified');
        expect(await updatePage.phoneInput.getAttribute('value')).to.match(/modified/);

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
