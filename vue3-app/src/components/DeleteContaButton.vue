<script lang="ts" setup>
import { useAsyncState } from '@vueuse/core'
import api from '@/api'

const props = defineProps<{ id: string }>()
const router = useRouter()
const { isLoading, execute: onDeleteConta } = useAsyncState(
  async () => {
    await api.deleteConta(props.id)
    router.push('/')
  },
  null,
  {
    immediate: false,
  },
)
</script>

<template>
  <button
    :disabled="isLoading"
    class="btn btn-outline-danger btn-sm"
    @click="() => onDeleteConta()"
  >
    <i class="ion-trash-a" /> Remover Conta
  </button>
</template>
