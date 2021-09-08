<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('audiencierApp.juridiction.home.title')" id="juridiction-heading">Juridictions</span>
            <router-link :to="{name: 'JuridictionCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-juridiction">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('audiencierApp.juridiction.home.createLabel')">
                    Create a new Juridiction
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
        <div class="alert alert-warning" v-if="!isFetching && juridictions && juridictions.length === 0">
            <span v-text="$t('audiencierApp.juridiction.home.notFound')">No juridictions found</span>
        </div>
        <div class="table-responsive" v-if="juridictions && juridictions.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('audiencierApp.juridiction.nom')">Nom</span></th>
                    <th><span v-text="$t('audiencierApp.juridiction.ville')">Ville</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="juridiction in juridictions"
                    :key="juridiction.id">
                    <td>
                        <router-link :to="{name: 'JuridictionView', params: {juridictionId: juridiction.id}}">{{juridiction.id}}</router-link>
                    </td>
                    <td>{{juridiction.nom}}</td>
                    <td>{{juridiction.ville}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'JuridictionView', params: {juridictionId: juridiction.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'JuridictionEdit', params: {juridictionId: juridiction.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(juridiction)"
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
            <span slot="modal-title"><span id="audiencierApp.juridiction.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-juridiction-heading" v-text="$t('audiencierApp.juridiction.delete.question', {'id': removeId})">Are you sure you want to delete this Juridiction?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-juridiction" v-text="$t('entity.action.delete')" v-on:click="removeJuridiction()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./juridiction.component.ts">
</script>
