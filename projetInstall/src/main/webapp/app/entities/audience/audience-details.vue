<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="audience">
                <h2 class="jh-entity-heading"><span v-text="$t('audiencierApp.audience.detail.title')">Audience</span> {{audience.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('audiencierApp.audience.mois')">Mois</span>
                    </dt>
                    <dd>
                        <span v-text="$t('audiencierApp.Mois.' + audience.mois)">{{audience.mois}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.audience.stade')">Stade</span>
                    </dt>
                    <dd>
                        <span>{{audience.stade}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.audience.dateAudience')">Date Audience</span>
                    </dt>
                    <dd>
                        <span>{{audience.dateAudience}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.audience.contentieux')">Contentieux</span>
                    </dt>
                    <dd>
                        <div v-if="audience.contentieux">
                            <router-link :to="{name: 'ContentieuxView', params: {contentieuxId: audience.contentieux.id}}">{{audience.contentieux.refContentieux}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="audience.id" :to="{name: 'AudienceEdit', params: {audienceId: audience.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./audience-details.component.ts">
</script>
