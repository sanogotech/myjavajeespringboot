<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="audiencierApp.requerant.home.createOrEditLabel" v-text="$t('audiencierApp.requerant.home.createOrEditLabel')">Create or edit a Requerant</h2>
                <div>
                    <div class="form-group" v-if="requerant.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="requerant.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.requerant.nom')" for="requerant-nom">Nom</label>
                        <input type="text" class="form-control" name="nom" id="requerant-nom"
                            :class="{'valid': !$v.requerant.nom.$invalid, 'invalid': $v.requerant.nom.$invalid }" v-model="$v.requerant.nom.$model"  required/>
                        <div v-if="$v.requerant.nom.$anyDirty && $v.requerant.nom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.requerant.nom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.requerant.nom.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.requerant.prenom')" for="requerant-prenom">Prenom</label>
                        <input type="text" class="form-control" name="prenom" id="requerant-prenom"
                            :class="{'valid': !$v.requerant.prenom.$invalid, 'invalid': $v.requerant.prenom.$invalid }" v-model="$v.requerant.prenom.$model"  required/>
                        <div v-if="$v.requerant.prenom.$anyDirty && $v.requerant.prenom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.requerant.prenom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.requerant.prenom.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.requerant.numCNI')" for="requerant-numCNI">Num CNI</label>
                        <input type="text" class="form-control" name="numCNI" id="requerant-numCNI"
                            :class="{'valid': !$v.requerant.numCNI.$invalid, 'invalid': $v.requerant.numCNI.$invalid }" v-model="$v.requerant.numCNI.$model"  required/>
                        <div v-if="$v.requerant.numCNI.$anyDirty && $v.requerant.numCNI.$invalid">
                            <small class="form-text text-danger" v-if="!$v.requerant.numCNI.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.requerant.numCNI.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.requerant.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./requerant-update.component.ts">
</script>
