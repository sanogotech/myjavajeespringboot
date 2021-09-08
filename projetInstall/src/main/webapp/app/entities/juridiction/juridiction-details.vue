<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="juridiction">
                <h2 class="jh-entity-heading"><span v-text="$t('audiencierApp.juridiction.detail.title')">Juridiction</span> {{juridiction.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('audiencierApp.juridiction.nom')">Nom</span>
                    </dt>
                    <dd>
                        <span>{{juridiction.nom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.juridiction.ville')">Ville</span>
                    </dt>
                    <dd>
                        <span>{{juridiction.ville}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="juridiction.id" :to="{name: 'JuridictionEdit', params: {juridictionId: juridiction.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./juridiction-details.component.ts">
</script>
