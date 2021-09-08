<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="audiencierApp.audience.home.createOrEditLabel" v-text="$t('audiencierApp.audience.home.createOrEditLabel')">Create or edit a Audience</h2> 
		
                <div>
                    <div class="form-group" v-if="audience.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="audience.id" readonly />
                    </div>
					
				
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.audience.mois')" for="audience-mois">Mois</label>
                        <select class="form-control" name="mois" :class="{'valid': !$v.audience.mois.$invalid, 'invalid': $v.audience.mois.$invalid }" v-model="$v.audience.mois.$model" id="audience-mois"  required>
                            <option value="Janvier" v-bind:label="$t('audiencierApp.Mois.Janvier')">Janvier</option>
                            <option value="Fevrier" v-bind:label="$t('audiencierApp.Mois.Fevrier')">Fevrier</option>
                            <option value="Mars" v-bind:label="$t('audiencierApp.Mois.Mars')">Mars</option>
                            <option value="Avril" v-bind:label="$t('audiencierApp.Mois.Avril')">Avril</option>
                            <option value="Mai" v-bind:label="$t('audiencierApp.Mois.Mai')">Mai</option>
                            <option value="Juin" v-bind:label="$t('audiencierApp.Mois.Juin')">Juin</option>
                            <option value="Juillet" v-bind:label="$t('audiencierApp.Mois.Juillet')">Juillet</option>
                            <option value="Aout" v-bind:label="$t('audiencierApp.Mois.Aout')">Aout</option>
                            <option value="Septembre" v-bind:label="$t('audiencierApp.Mois.Septembre')">Septembre</option>
                            <option value="Octobre" v-bind:label="$t('audiencierApp.Mois.Octobre')">Octobre</option>
                            <option value="Novembre" v-bind:label="$t('audiencierApp.Mois.Novembre')">Novembre</option>
                            <option value="Decembre" v-bind:label="$t('audiencierApp.Mois.Decembre')">Decembre</option>
                        </select>
                        <div v-if="$v.audience.mois.$anyDirty && $v.audience.mois.$invalid">
                            <small class="form-text text-danger" v-if="!$v.audience.mois.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.audience.stade')" for="audience-stade">Stade</label>
                        <textarea class="form-control" name="stade" id="audience-stade"
                            :class="{'valid': !$v.audience.stade.$invalid, 'invalid': $v.audience.stade.$invalid }" v-model="$v.audience.stade.$model"  required></textarea>
                        <div v-if="$v.audience.stade.$anyDirty && $v.audience.stade.$invalid">
                            <small class="form-text text-danger" v-if="!$v.audience.stade.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.audience.dateAudience')" for="audience-dateAudience">Date Audience</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="audience-dateAudience"
                                    v-model="$v.audience.dateAudience.$model"
                                    name="dateAudience"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="audience-dateAudience" type="text" class="form-control" name="dateAudience"  :class="{'valid': !$v.audience.dateAudience.$invalid, 'invalid': $v.audience.dateAudience.$invalid }"
                            v-model="$v.audience.dateAudience.$model"  required />
                        </b-input-group>
                        <div v-if="$v.audience.dateAudience.$anyDirty && $v.audience.dateAudience.$invalid">
                            <small class="form-text text-danger" v-if="!$v.audience.dateAudience.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.audience.contentieux')" for="audience-contentieux">Contentieux</label>
                        <select class="form-control" id="audience-contentieux" name="contentieux"  v-model="audience.contentieux" required>
                            <option   v-bind:value="audience.contentieux && contentieuxOption.id === audience.contentieux.id ? audience.contentieux : contentieuxOption" v-for="contentieuxOption in contentieuxes" :key="contentieuxOption.id">{{contentieuxOption.refContentieux}}</option>
                        </select>
						
						
						 <div v-if="$v.audience.contentieux">
                            <small class="form-text text-danger" v-if="!$v.audience.contentieux.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
						
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.audience.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./audience-update.component.ts">
</script>
