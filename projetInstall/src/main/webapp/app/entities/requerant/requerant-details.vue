<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="requerant">
                <h2 class="jh-entity-heading"><span v-text="$t('audiencierApp.requerant.detail.title')">Requerant</span> {{requerant.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('audiencierApp.requerant.nom')">Nom</span>
                    </dt>
                    <dd>
                        <span>{{requerant.nom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.requerant.prenom')">Prenom</span>
                    </dt>
                    <dd>
                        <span>{{requerant.prenom}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.requerant.numCNI')">Num CNI</span>
                    </dt>
                    <dd>
                        <span>{{requerant.numCNI}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="requerant.id" :to="{name: 'RequerantEdit', params: {requerantId: requerant.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./requerant-details.component.ts">
</script>
