<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('audiencierApp.contentieux.home.title')" id="contentieux-heading">Contentieux</span>
            <router-link :to="{name: 'ContentieuxCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-contentieux">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('audiencierApp.contentieux.home.createLabel')">
                    Create a new Contentieux
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && contentieuxes && contentieuxes.length === 0">
            <span v-text="$t('audiencierApp.contentieux.home.notFound')">No contentieuxes found</span>
        </div>
        <div class="table-responsive" v-if="contentieuxes && contentieuxes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('entite')"><span v-text="$t('audiencierApp.contentieux.entite')">Entite</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'entite'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('refContentieux')"><span v-text="$t('audiencierApp.contentieux.refContentieux')">Ref Contentieux</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'refContentieux'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('datePremiereAudience')"><span v-text="$t('audiencierApp.contentieux.datePremiereAudience')">Date Premiere Audience</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'datePremiereAudience'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('requerant.nom')"><span v-text="$t('audiencierApp.contentieux.requerant')">Requerant</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'requerant.nom'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('avocat.nom')"><span v-text="$t('audiencierApp.contentieux.avocat')">Avocat</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'avocat.nom'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('juridiction.nom')"><span v-text="$t('audiencierApp.contentieux.juridiction')">Juridiction</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'juridiction.nom'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contentieux in contentieuxes"
                    :key="contentieux.id">
                    <td>
                        <router-link :to="{name: 'ContentieuxView', params: {contentieuxId: contentieux.id}}">{{contentieux.id}}</router-link>
                    </td>
                    <td v-text="$t('audiencierApp.Entite.' + contentieux.entite)">{{contentieux.entite}}</td>
                    <td>{{contentieux.refContentieux}}</td>
                    <td>{{contentieux.datePremiereAudience}}</td>
                    <td>
                        <div v-if="contentieux.requerant">
                            <router-link :to="{name: 'RequerantView', params: {requerantId: contentieux.requerant.id}}">{{contentieux.requerant.nom}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="contentieux.avocat">
                            <router-link :to="{name: 'AvocatView', params: {avocatId: contentieux.avocat.id}}">{{contentieux.avocat.nom}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="contentieux.juridiction">
                            <router-link :to="{name: 'JuridictionView', params: {juridictionId: contentieux.juridiction.id}}">{{contentieux.juridiction.nom}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContentieuxView', params: {contentieuxId: contentieux.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.viewAudience')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ContentieuxEdit', params: {contentieuxId: contentieux.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contentieux)"
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
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="audiencierApp.contentieux.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-contentieux-heading" v-text="$t('audiencierApp.contentieux.delete.question', {'id': removeId})">Are you sure you want to delete this Contentieux?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-contentieux" v-text="$t('entity.action.delete')" v-on:click="removeContentieux()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./contentieux.component.ts">
</script>
