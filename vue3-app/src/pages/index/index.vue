<script lang="ts" setup>

import { useUserStore } from '@/stores/useUserStore'
import type { ContaListProps } from '@/types'

const store = useUserStore()

const contaListProps = ref<ContaListProps>()


</script>

<template>
  <div class="home-page">
    <div v-if="!store.isLoggedIn" class="banner">
      <div class="container">
        <h1 class="logo-font">
          Desafio
        </h1>
        <p>Aplicacao de exemplo.</p>
      </div>
    </div>

    <div class="container page">
      <div class="row">
        <div class="col-md-9">
          <conta-list :remote-params="contaListProps" />
        </div>


      </div>
    </div>
  </div>
</template>
