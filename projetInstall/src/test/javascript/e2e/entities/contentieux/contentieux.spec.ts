/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import ContentieuxComponentsPage, { ContentieuxDeleteDialog } from './contentieux.page-object';
import ContentieuxUpdatePage from './contentieux-update.page-object';
import ContentieuxDetailsPage from './contentieux-details.page-object';

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

describe('Contentieux e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: ContentieuxUpdatePage;
  let detailsPage: ContentieuxDetailsPage;
  let listPage: ContentieuxComponentsPage;
  let deleteDialog: ContentieuxDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Contentieuxes', async () => {
    await navBarPage.getEntityPage('contentieux');
    listPage = new ContentieuxComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Contentieux page', async () => {
      await listPage.createButton.click();
      updatePage = new ContentieuxUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/audiencierApp.contentieux.home.createOrEditLabel/);
    });

    it('should create and save Contentieuxes', async () => {
      await selectLastOption(updatePage.entiteSelect);

      await updatePage.refContentieuxInput.sendKeys('refContentieux');
      expect(await updatePage.refContentieuxInput.getAttribute('value')).to.match(/refContentieux/);

      await updatePage.datePremiereAudienceInput.sendKeys('2001-01-01');
      expect(await updatePage.datePremiereAudienceInput.getAttribute('value')).to.eq('2001-01-01');

      // await  selectLastOption(updatePage.requerantSelect);
      // await  selectLastOption(updatePage.avocatSelect);
      // await  selectLastOption(updatePage.juridictionSelect);

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

        deleteDialog = new ContentieuxDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/audiencierApp.contentieux.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Contentieux page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.last());
        await click(detailsButton);

        detailsPage = new ContentieuxDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Contentieux page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.last());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.refContentieuxInput.clear();
        await updatePage.refContentieuxInput.sendKeys('modified');
        expect(await updatePage.refContentieuxInput.getAttribute('value')).to.match(/modified/);

        await updatePage.datePremiereAudienceInput.clear();
        await updatePage.datePremiereAudienceInput.sendKeys('2019-01-01');
        expect(await updatePage.datePremiereAudienceInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
