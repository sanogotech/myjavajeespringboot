<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="avocat">
                <h2 class="jh-entity-heading"><span v-text="$t('audiencierApp.avocat.detail.title')">Avocat</span> {{avocat.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('audiencierApp.avocat.nom')">Nom</span>
                    </dt>
                    <dd>
                        <span>{{avocat.nom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.avocat.prenom')">Prenom</span>
                    </dt>
                    <dd>
                        <span>{{avocat.prenom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.avocat.email')">Email</span>
                    </dt>
                    <dd>
                        <span>{{avocat.email}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.avocat.phone')">Phone</span>
                    </dt>
                    <dd>
                        <span>{{avocat.phone}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="avocat.id" :to="{name: 'AvocatEdit', params: {avocatId: avocat.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./avocat-details.component.ts">
</script>
