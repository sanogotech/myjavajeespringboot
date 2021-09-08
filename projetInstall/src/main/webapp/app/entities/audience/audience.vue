<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('audiencierApp.audience.home.title')" id="audience-heading">Audiences</span>
        </h2>
    
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && audiences && audiences.length === 0">
            <span v-text="$t('audiencierApp.audience.home.notFound')">No audiences found</span>
        </div>
		
		
        <div class="table-responsive" v-if="audiences && audiences.length > 0">
		
			<download-excel
		  class="btn btn-default"
		  :data="audiences"
		  :fields="json_fields"
		  worksheet="My Worksheet"
		  type="csv"
		  name="data.csv">
		  Téléchager les Audiences ...
		</download-excel>
		
		<br/>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mois')"><span v-text="$t('audiencierApp.audience.mois')">Mois</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mois'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('stade')"><span v-text="$t('audiencierApp.audience.stade')">Stade</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'stade'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('dateAudience')"><span v-text="$t('audiencierApp.audience.dateAudience')">Date Audience</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dateAudience'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('contentieux.refContentieux')"><span v-text="$t('audiencierApp.audience.contentieux')">Contentieux</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'contentieux.refContentieux'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="audience in audiences"
                    :key="audience.id">
                    <td>
                        <router-link :to="{name: 'AudienceView', params: {audienceId: audience.id}}">{{audience.id}}</router-link>
                    </td>
                    <td v-text="$t('audiencierApp.Mois.' + audience.mois)">{{audience.mois}}</td>
                    <td>{{audience.stade}}</td>
                    <td>{{audience.dateAudience}}</td>
                    <td>
                        <div v-if="audience.contentieux">
                            <router-link :to="{name: 'ContentieuxView', params: {contentieuxId: audience.contentieux.id}}">{{audience.contentieux.refContentieux}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'AudienceView', params: {audienceId: audience.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'AudienceEdit', params: {audienceId: audience.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(audience)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="audiencierApp.audience.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-audience-heading" v-text="$t('audiencierApp.audience.delete.question', {'id': removeId})">Are you sure you want to delete this Audience?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-audience" v-text="$t('entity.action.delete')" v-on:click="removeAudience()">Delete</button>
            </div>
        </b-modal>
        <div v-show="audiences && audiences.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./audience.component.ts">
</script>
