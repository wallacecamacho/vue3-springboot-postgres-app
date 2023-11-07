<script lang="ts" setup>
import { useAsyncState } from '@vueuse/core'
import api from '@/api'
import type { Conta } from '@/types'
import { formatDate } from '@/utils'
import { useUserStore } from '@/stores/useUserStore'

defineProps({ conta: { type: Object as PropType<Conta>, required: true } })

const router = useRouter()
const store = useUserStore()
const { isLoading, execute: onFavorited } = useAsyncState(
  async (args: Conta) => {
    if (!store.isLoggedIn) {
      router.push('/register') 
    }
  },
  null,
  { immediate: false },
)
</script>

<template>
  <div class="article-preview">
    <div class="article-meta">

      <div class="info">

        <span class="date">{{ formatDate(conta.createdAt) }}</span>
      </div>

    </div>
    <router-link class="preview-link" :to="`/contas/${conta.id}`">
      <h1>{{ conta.conta }}</h1>
      <p>{{ conta.agencia }}</p>
      <span>Editar...</span>

    </router-link>
  </div>
</template>
