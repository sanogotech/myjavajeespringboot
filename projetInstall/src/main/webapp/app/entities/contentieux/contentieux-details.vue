<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="contentieux">
                <h2 class="jh-entity-heading"><span v-text="$t('audiencierApp.contentieux.detail.title')">Contentieux</span> {{contentieux.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('audiencierApp.contentieux.entite')">Entite</span>
                    </dt>
                    <dd>
                        <span v-text="$t('audiencierApp.Entite.' + contentieux.entite)">{{contentieux.entite}}</span>
                    </dd>


                    <dt>
                        <span v-text="$t('audiencierApp.contentieux.datePremiereAudience')">Date Premiere Audience</span>
                    </dt>
                    <dd>
                        <span>{{contentieux.datePremiereAudience}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.contentieux.requerant')">Requerant</span>
                    </dt>
                    <dd>
                        <div v-if="contentieux.requerant">
                            <router-link :to="{name: 'RequerantView', params: {requerantId: contentieux.requerant.id}}">{{contentieux.requerant.nom}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.contentieux.avocat')">Avocat</span>
                    </dt>
                    <dd>
                        <div v-if="contentieux.avocat">
                            <router-link :to="{name: 'AvocatView', params: {avocatId: contentieux.avocat.id}}">{{contentieux.avocat.nom}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('audiencierApp.contentieux.juridiction')">Juridiction</span>
                    </dt>
                    <dd>
                        <div v-if="contentieux.juridiction">
                            <router-link :to="{name: 'JuridictionView', params: {juridictionId: contentieux.juridiction.id}}">{{contentieux.juridiction.nom}}</router-link>
                        </div>
                    </dd>
                </dl>
                
		<br/>
		<br/>		
		<h2 id="page-heading">
            <span v-text="$t('audiencierApp.audience.home.title')" id="audience-heading">Audiences</span>
            <router-link :to="{name: 'AudienceCreateWithContentieux',params: {contentieuxId: contentieux.id}}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-audience">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('audiencierApp.audience.home.createLabel')">
                    Create a new Audience
                </span>
            </router-link>
        </h2>
      
        <br/>
		<br/>
		
		
				
				<div class="alert alert-warning" v-if=" audiences && audiences.length === 0">
					<span v-text="$t('audiencierApp.audience.home.notFound')">No audiences found</span>
				</div>
				<div class="table-responsive" v-if="audiences">
				
					<table class="table table-striped">
						<thead>
						<tr>
							<th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
							<th v-on:click="changeOrder('mois')"><span v-text="$t('audiencierApp.audience.mois')">Mois</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mois'"></jhi-sort-indicator></th>
							<th v-on:click="changeOrder('stade')"><span v-text="$t('audiencierApp.audience.stade')">Stade</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'stade'"></jhi-sort-indicator></th>
							<th v-on:click="changeOrder('dateAudience')"><span v-text="$t('audiencierApp.audience.dateAudience')">Date Audience</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dateAudience'"></jhi-sort-indicator></th>
							<th v-on:click="changeOrder('contentieux.refContentieux')"><span v-text="$t('audiencierApp.audience.contentieux')">Contentieux</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'contentieux.refContentieux'"></jhi-sort-indicator></th>
							<th></th>
						</tr>
						</thead>
						<tbody>
						<tr v-for="audience in audiences"
							:key="audience.id">
							<td>
								<router-link :to="{name: 'AudienceView', params: {audienceId: audience.id}}">{{audience.id}}</router-link>
							</td>
							<td v-text="$t('audiencierApp.Mois.' + audience.mois)">{{audience.mois}}</td>
							<td>{{audience.stade}}</td>
							<td>{{audience.dateAudience}}</td>
							<td>
								<div v-if="audience.contentieux">
									<router-link :to="{name: 'ContentieuxView', params: {contentieuxId: audience.contentieux.id}}">{{audience.contentieux.refContentieux}}</router-link>
								</div>
							</td>
							<td class="text-right">
								<div class="btn-group">
									<router-link :to="{name: 'AudienceView', params: {audienceId: audience.id}}" tag="button" class="btn btn-info btn-sm details">
										<font-awesome-icon icon="eye"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
									</router-link>
									<router-link :to="{name: 'AudienceEdit', params: {audienceId: audience.id}}"  tag="button" class="btn btn-primary btn-sm edit">
										<font-awesome-icon icon="pencil-alt"></font-awesome-icon>
										<span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
									</router-link>
									<b-button v-on:click="prepareRemove(audience,contentieuxId)"
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

                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="contentieux.id" :to="{name: 'ContentieuxEdit', params: {contentieuxId: contentieux.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contentieux-details.component.ts">
</script>


