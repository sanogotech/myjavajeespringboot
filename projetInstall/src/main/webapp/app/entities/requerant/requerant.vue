<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('audiencierApp.requerant.home.title')" id="requerant-heading">Requerants</span>
            <router-link :to="{name: 'RequerantCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-requerant">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('audiencierApp.requerant.home.createLabel')">
                    Create a new Requerant
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
        <div class="alert alert-warning" v-if="!isFetching && requerants && requerants.length === 0">
            <span v-text="$t('audiencierApp.requerant.home.notFound')">No requerants found</span>
        </div>
        <div class="table-responsive" v-if="requerants && requerants.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('audiencierApp.requerant.nom')">Nom</span></th>
                    <th><span v-text="$t('audiencierApp.requerant.prenom')">Prenom</span></th>
                    <th><span v-text="$t('audiencierApp.requerant.numCNI')">Num CNI</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="requerant in requerants"
                    :key="requerant.id">
                    <td>
                        <router-link :to="{name: 'RequerantView', params: {requerantId: requerant.id}}">{{requerant.id}}</router-link>
                    </td>
                    <td>{{requerant.nom}}</td>
                    <td>{{requerant.prenom}}</td>
                    <td>{{requerant.numCNI}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'RequerantView', params: {requerantId: requerant.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'RequerantEdit', params: {requerantId: requerant.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(requerant)"
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
            <span slot="modal-title"><span id="audiencierApp.requerant.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-requerant-heading" v-text="$t('audiencierApp.requerant.delete.question', {'id': removeId})">Are you sure you want to delete this Requerant?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-requerant" v-text="$t('entity.action.delete')" v-on:click="removeRequerant()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./requerant.component.ts">
</script>
