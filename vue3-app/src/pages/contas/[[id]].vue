<script lang="ts" setup>
import type { RouteLocationNormalizedLoaded } from 'vue-router'
import { useAsyncState } from '@vueuse/core'
import api from '@/api'
import { formatDate, formatterMoney } from '@/utils'
import type { Conta, ExtratoSaldo, Transacao } from '@/types'

import { useUserStore } from '@/stores/useUserStore'


const route = useRoute() as RouteLocationNormalizedLoaded
const store = useUserStore()

const conta = ref<Conta>({
  id: '',
  agencia: '',
  conta: '',
  createdAt: ''
})

const transacoes = ref<ExtratoSaldo>({
  movimentacoes: [],
  saldo:''
})



const isAuthor = computed(() => {
  return store.userInfo?.username
})

async function getActive() {
  const res = await api.getConta(route.params.id as string)
  conta.value = res.conta
}

async function getExtratoSaldo() {
  await api.getExtratoSaldo(route.params.id as string).then((res) => {
    transacoes.value.movimentacoes = res.extrato.movimentacoes
    transacoes.value.saldo = res.extrato.saldo
      })

}



//watch(
//  () => props,
//  () => {
//    getExtSaldo()
//  },
//)

onMounted(() => {
  getActive()
  getExtratoSaldo()
})
</script>

<template>
  <div class="article-page">
    <div class="banner">
      <div class="container">
        <h1>{{ conta.conta }}</h1>
        <div class="article-meta">
          <div class="info">
            <span class="date">{{ formatDate(conta.createdAt) }}</span>
          </div>
          <template v-if="isAuthor">
            <edit-conta-button :to="`/contas/editor/${conta.id}`" />
            &nbsp;&nbsp;
            <delete-conta-button :id="conta.id" />
            &nbsp;&nbsp;
            <credita-conta-button :to="`/contas/creditar/${conta.id}`"  />
            &nbsp;&nbsp;
            <transfere-conta-button :to="`/contas/transferir/${conta.id}`"  />
          </template>

        </div>
      </div>
    </div>

    <div class="container page">
      <div class="row article-content">
        <div class="col-xs-12">
          <p>Conta:&nbsp;{{ conta.agencia }} &nbsp;Agência: {{ conta.conta }}</p>
          <p v-html="conta.id" ></p>
        </div>
      </div>

      <div class="row article-content">
        <div class="col-xs-12">

          <div class="article-list" v-for="(transacao, index) in transacoes.movimentacoes" :key="index" :transacao="transacao">
            <p>ID:&nbsp;{{ transacao.id }}</p>  
            <p>Transação:&nbsp;{{ transacao.transacao }}</p> 
            <p>Data:&nbsp;{{ formatDate(transacao.createdAt) }}</p> 
          </div>       
          <p>Saldo:&nbsp;{{ formatterMoney(transacoes.saldo) }}</p> 
        </div>

      </div>

      <hr>

      <div class="article-actions">
        <div class="article-meta">
          <div class="info">
            <span class="date">{{ formatDate(conta.createdAt) }}</span>
          </div>

          <template v-if="isAuthor">
            <edit-conta-button :to="`/contas/editor/${conta.id}`" />
            &nbsp;&nbsp;
            <delete-conta-button :id="conta.id" />
            &nbsp;&nbsp;
            <credita-conta-button :to="`/contas/creditar/${conta.id}`"  />
            &nbsp;&nbsp;
            <transfere-conta-button :to="`/contas/transferir/${conta.id}`"  />
          </template>

        </div>
      </div>

      <div v-if="!store.isLoggedIn" class="row">
        <div class="col-xs-12 col-md-8 offset-md-2">
          <p>
            <router-link to="/login">
              Entrar
            </router-link>
            &nbsp; or &nbsp;
            <router-link to="/register">
              Cadastrar
            </router-link>
            &nbsp; to add comments on this article.
          </p>
        </div>
      </div>


    </div>
  </div>
</template>
