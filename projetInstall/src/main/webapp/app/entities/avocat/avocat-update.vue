<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="audiencierApp.avocat.home.createOrEditLabel" v-text="$t('audiencierApp.avocat.home.createOrEditLabel')">Create or edit a Avocat</h2>
                <div>
                    <div class="form-group" v-if="avocat.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="avocat.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.avocat.nom')" for="avocat-nom">Nom</label>
                        <input type="text" class="form-control" name="nom" id="avocat-nom"
                            :class="{'valid': !$v.avocat.nom.$invalid, 'invalid': $v.avocat.nom.$invalid }" v-model="$v.avocat.nom.$model"  required/>
                        <div v-if="$v.avocat.nom.$anyDirty && $v.avocat.nom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.avocat.nom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.avocat.nom.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.avocat.prenom')" for="avocat-prenom">Prenom</label>
                        <input type="text" class="form-control" name="prenom" id="avocat-prenom"
                            :class="{'valid': !$v.avocat.prenom.$invalid, 'invalid': $v.avocat.prenom.$invalid }" v-model="$v.avocat.prenom.$model"  required/>
                        <div v-if="$v.avocat.prenom.$anyDirty && $v.avocat.prenom.$invalid">
                            <small class="form-text text-danger" v-if="!$v.avocat.prenom.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.avocat.prenom.minLength" v-text="$t('entity.validation.minlength', {min: 2})">
                                This field is required to be at least 2 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.avocat.email')" for="avocat-email">Email</label>
                        <input type="text" class="form-control" name="email" id="avocat-email"
                            :class="{'valid': !$v.avocat.email.$invalid, 'invalid': $v.avocat.email.$invalid }" v-model="$v.avocat.email.$model"  required/>
                        <div v-if="$v.avocat.email.$anyDirty && $v.avocat.email.$invalid">
                            <small class="form-text text-danger" v-if="!$v.avocat.email.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.avocat.phone')" for="avocat-phone">Phone</label>
                        <input type="text" class="form-control" name="phone" id="avocat-phone"
                            :class="{'valid': !$v.avocat.phone.$invalid, 'invalid': $v.avocat.phone.$invalid }" v-model="$v.avocat.phone.$model"  required/>
                        <div v-if="$v.avocat.phone.$anyDirty && $v.avocat.phone.$invalid">
                            <small class="form-text text-danger" v-if="!$v.avocat.phone.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.avocat.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./avocat-update.component.ts">
</script>
