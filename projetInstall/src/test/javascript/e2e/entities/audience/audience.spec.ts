/* tslint:disable no-unused-expression */
import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import AudienceComponentsPage, { AudienceDeleteDialog } from './audience.page-object';
import AudienceUpdatePage from './audience-update.page-object';
import AudienceDetailsPage from './audience-details.page-object';

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

describe('Audience e2e test', () => {
  let navBarPage: NavBarPage;
  let updatePage: AudienceUpdatePage;
  let detailsPage: AudienceDetailsPage;
  let listPage: AudienceComponentsPage;
  let deleteDialog: AudienceDeleteDialog;
  let beforeRecordsCount = 0;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    await navBarPage.login('admin', 'admin');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });

  it('should load Audiences', async () => {
    await navBarPage.getEntityPage('audience');
    listPage = new AudienceComponentsPage();

    await waitUntilAllDisplayed([listPage.title, listPage.footer]);

    expect(await listPage.title.getText()).not.to.be.empty;
    expect(await listPage.createButton.isEnabled()).to.be.true;

    await waitUntilAnyDisplayed([listPage.noRecords, listPage.table]);
    beforeRecordsCount = (await isVisible(listPage.noRecords)) ? 0 : await getRecordsCount(listPage.table);
  });
  describe('Create flow', () => {
    it('should load create Audience page', async () => {
      await listPage.createButton.click();
      updatePage = new AudienceUpdatePage();

      await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

      expect(await updatePage.title.getAttribute('id')).to.match(/audiencierApp.audience.home.createOrEditLabel/);
    });

    it('should create and save Audiences', async () => {
      await selectLastOption(updatePage.moisSelect);

      await waitUntilDisplayed(updatePage.stadeInput);
      await updatePage.stadeInput.sendKeys('stade');

      expect(await updatePage.stadeInput.getAttribute('value')).to.match(/stade/);

      await updatePage.dateAudienceInput.sendKeys('2001-01-01');
      expect(await updatePage.dateAudienceInput.getAttribute('value')).to.eq('2001-01-01');

      // await  selectLastOption(updatePage.contentieuxSelect);

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
        const deleteButton = listPage.getDeleteButton(listPage.records.first());
        await click(deleteButton);

        deleteDialog = new AudienceDeleteDialog();
        await waitUntilDisplayed(deleteDialog.dialog);

        expect(await deleteDialog.title.getAttribute('id')).to.match(/audiencierApp.audience.delete.question/);

        await click(deleteDialog.confirmButton);
        await waitUntilHidden(deleteDialog.dialog);

        expect(await isVisible(deleteDialog.dialog)).to.be.false;
        expect(await listPage.dangerAlert.isDisplayed()).to.be.true;

        await waitUntilCount(listPage.records, beforeRecordsCount);
        expect(await listPage.records.count()).to.eq(beforeRecordsCount);
      });

      it('should load details Audience page and fetch data', async () => {
        const detailsButton = listPage.getDetailsButton(listPage.records.first());
        await click(detailsButton);

        detailsPage = new AudienceDetailsPage();

        await waitUntilAllDisplayed([detailsPage.title, detailsPage.backButton, detailsPage.firstDetail]);

        expect(await detailsPage.title.getText()).not.to.be.empty;
        expect(await detailsPage.firstDetail.getText()).not.to.be.empty;

        await click(detailsPage.backButton);
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });

      it('should load edit Audience page, fetch data and update', async () => {
        const editButton = listPage.getEditButton(listPage.records.first());
        await click(editButton);

        await waitUntilAllDisplayed([updatePage.title, updatePage.footer, updatePage.saveButton]);

        expect(await updatePage.title.getText()).not.to.be.empty;

        await updatePage.stadeInput.clear();
        await updatePage.stadeInput.sendKeys('updatedstade');
        expect(await updatePage.stadeInput.getAttribute('value')).to.match(/updatedstade/);

        await updatePage.dateAudienceInput.clear();
        await updatePage.dateAudienceInput.sendKeys('2019-01-01');
        expect(await updatePage.dateAudienceInput.getAttribute('value')).to.eq('2019-01-01');

        await updatePage.saveButton.click();

        await waitUntilHidden(updatePage.saveButton);

        expect(await isVisible(updatePage.saveButton)).to.be.false;
        expect(await listPage.infoAlert.isDisplayed()).to.be.true;
        await waitUntilCount(listPage.records, beforeRecordsCount + 1);
      });
    });
  });
});
