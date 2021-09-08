<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('audiencierApp.avocat.home.title')" id="avocat-heading">Avocats</span>
            <router-link :to="{name: 'AvocatCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-avocat">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('audiencierApp.avocat.home.createLabel')">
                    Create a new Avocat
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
        <div class="alert alert-warning" v-if="!isFetching && avocats && avocats.length === 0">
            <span v-text="$t('audiencierApp.avocat.home.notFound')">No avocats found</span>
        </div>
        <div class="table-responsive" v-if="avocats && avocats.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('audiencierApp.avocat.nom')">Nom</span></th>
                    <th><span v-text="$t('audiencierApp.avocat.prenom')">Prenom</span></th>
                    <th><span v-text="$t('audiencierApp.avocat.email')">Email</span></th>
                    <th><span v-text="$t('audiencierApp.avocat.phone')">Phone</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="avocat in avocats"
                    :key="avocat.id">
                    <td>
                        <router-link :to="{name: 'AvocatView', params: {avocatId: avocat.id}}">{{avocat.id}}</router-link>
                    </td>
                    <td>{{avocat.nom}}</td>
                    <td>{{avocat.prenom}}</td>
                    <td>{{avocat.email}}</td>
                    <td>{{avocat.phone}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'AvocatView', params: {avocatId: avocat.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'AvocatEdit', params: {avocatId: avocat.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(avocat)"
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
            <span slot="modal-title"><span id="audiencierApp.avocat.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-avocat-heading" v-text="$t('audiencierApp.avocat.delete.question', {'id': removeId})">Are you sure you want to delete this Avocat?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-avocat" v-text="$t('entity.action.delete')" v-on:click="removeAvocat()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./avocat.component.ts">
</script>
