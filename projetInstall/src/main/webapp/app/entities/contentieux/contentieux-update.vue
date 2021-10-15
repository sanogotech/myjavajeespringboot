<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="audiencierApp.contentieux.home.createOrEditLabel" v-text="$t('audiencierApp.contentieux.home.createOrEditLabel')">Create or edit a Contentieux</h2>
                <div>
                    <div class="form-group" v-if="contentieux.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="contentieux.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.contentieux.entite')" for="contentieux-entite">Entite</label>
                        <select class="form-control" name="entite" :class="{'valid': !$v.contentieux.entite.$invalid, 'invalid': $v.contentieux.entite.$invalid }" v-model="$v.contentieux.entite.$model" id="contentieux-entite" >
                            <option value="CIE" v-bind:label="$t('audiencierApp.Entite.CIE')">CIE</option>
                            <option value="SODECI" v-bind:label="$t('audiencierApp.Entite.SODECI')">SODECI</option>
                            <option value="AWALE" v-bind:label="$t('audiencierApp.Entite.AWALE')">AWALE</option>
							<option value="GS2E" v-bind:label="$t('audiencierApp.Entite.GS2E')">GS2E</option>
							<option value="SMART_ENERGY" v-bind:label="$t('audiencierApp.Entite.SMART_ENERGY')">SMART_ENERGY</option>
                        </select>
                    </div>
                 
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.contentieux.datePremiereAudience')" for="contentieux-datePremiereAudience">Date Premiere Audience</label>
                        <b-input-group class="mb-3">
                            <b-input-group-prepend>
                                <b-form-datepicker
                                    aria-controls="contentieux-datePremiereAudience"
                                    v-model="$v.contentieux.datePremiereAudience.$model"
                                    name="datePremiereAudience"
                                    class="form-control"
                                    :locale="currentLanguage"
                                    button-only
                                    today-button
                                    reset-button
                                    close-button
                                >
                                </b-form-datepicker>
                            </b-input-group-prepend>
                            <b-form-input id="contentieux-datePremiereAudience" type="text" class="form-control" name="datePremiereAudience"  :class="{'valid': !$v.contentieux.datePremiereAudience.$invalid, 'invalid': $v.contentieux.datePremiereAudience.$invalid }"
                            v-model="$v.contentieux.datePremiereAudience.$model"  required />
                        </b-input-group>
                        <div v-if="$v.contentieux.datePremiereAudience.$anyDirty && $v.contentieux.datePremiereAudience.$invalid">
                            <small class="form-text text-danger" v-if="!$v.contentieux.datePremiereAudience.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.contentieux.requerant')" for="contentieux-requerant">Requerant</label>
                        <select class="form-control" id="contentieux-requerant" name="requerant" v-model="contentieux.requerant">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contentieux.requerant && requerantOption.id === contentieux.requerant.id ? contentieux.requerant : requerantOption" v-for="requerantOption in requerants" :key="requerantOption.id">{{requerantOption.nom}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.contentieux.avocat')" for="contentieux-avocat">Avocat</label>
                        <select class="form-control" id="contentieux-avocat" name="avocat" v-model="contentieux.avocat">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contentieux.avocat && avocatOption.id === contentieux.avocat.id ? contentieux.avocat : avocatOption" v-for="avocatOption in avocats" :key="avocatOption.id">{{avocatOption.nom}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('audiencierApp.contentieux.juridiction')" for="contentieux-juridiction">Juridiction</label>
                        <select class="form-control" id="contentieux-juridiction" name="juridiction" v-model="contentieux.juridiction">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contentieux.juridiction && juridictionOption.id === contentieux.juridiction.id ? contentieux.juridiction : juridictionOption" v-for="juridictionOption in juridictions" :key="juridictionOption.id">{{juridictionOption.nom}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.contentieux.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./contentieux-update.component.ts">
</script>
