<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="audiencierApp.juridiction.home.createOrEditLabel" v-text="$t('audiencierApp.juridiction.home.createOrEditLabel')">Create or edit a Juridiction</h2>
                <div>
                    <div class="form-group" v-if="juridiction.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="juridiction.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.juridiction.nom')" for="juridiction-nom">Nom</label>
                        <input type="text" class="form-control" name="nom" id="juridiction-nom"
                            :class="{'valid': !$v.juridiction.nom.$invalid, 'invalid': $v.juridiction.nom.$invalid }" v-model="$v.juridiction.nom.$model"  required/>
                        <div v-if="$v.juridiction.nom.$anyDirty && $v.juridiction.nom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.juridiction.nom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.juridiction.nom.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.juridiction.ville')" for="juridiction-ville">Ville</label>
                        <input type="text" class="form-control" name="ville" id="juridiction-ville"
                            :class="{'valid': !$v.juridiction.ville.$invalid, 'invalid': $v.juridiction.ville.$invalid }" v-model="$v.juridiction.ville.$model"  required/>
                        <div v-if="$v.juridiction.ville.$anyDirty && $v.juridiction.ville.$invalid">
                            <small class="form-text text-danger" v-if="!$v.juridiction.ville.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.juridiction.ville.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.juridiction.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./juridiction-update.component.ts">
</script>
