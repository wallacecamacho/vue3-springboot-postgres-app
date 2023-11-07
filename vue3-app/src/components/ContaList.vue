<script lang="ts" setup>
import { useAsyncState } from '@vueuse/core'
import api from '@/api'
import type { ContaListProps } from '@/types'

const props = defineProps<{ remoteParams: ContaListProps }>()

const {
  state,
  error,
  isLoading,
  execute: getContas,} = useAsyncState(
  async () => {
    const params = { ...props.remoteParams }

    return await api.getContas(params)
  },
  { contas: [] },
)


</script>

<template>
  <div v-if="error" class="article-preview">
    Conta  error
  </div>
  <div v-else-if="isLoading" class="article-preview">
    Contas...
  </div>
  <div v-else-if="state.contas.length === 0" class="article-preview">
    ...
  </div>
  <div v-else class="article-list">
    <conta-preview v-for="(conta, index) in state.contas" :key="index" :conta="conta" />
  </div>
</template>
