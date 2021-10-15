<template>
    <div class="home row">
        <div class="col-md-3">
            <span class="audiencier justice rounded"></span>
            <span class="audiencier ciesodeci rounded"></span>
            
        </div>
        <div class="col-md-9">
            <h1 class="display-4" v-text="$t('home.title')">Welcome, Java Hipster!</h1>
            <p class="lead" v-text="$t('home.subtitle')">This is your homepage</p>

            <div>
                <div class="alert alert-success" v-if="authenticated">
                    <span v-if="username" v-text="$t('home.logged.message', { 'username': username})">You are logged in as user "{{username}}"</span>
                </div>

                <div class="alert alert-warning" v-if="!authenticated">
            
                    <p style="text-align:center">
                        Comment mettre en place une gestion du contentieux ?
                    </p>
                </div>
             

            </div>

            

            <p style="text-align:center">
                <!--  <span class="audiencier img-fluid rounded"></span>
                <span class="audiencier contentieux rounded"></span>
				-->

            </p>
        </div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
