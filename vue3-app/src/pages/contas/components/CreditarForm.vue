<script lang="ts" setup>
import { useAsyncState } from '@vueuse/core'
import type { RouteLocationNormalizedLoaded } from 'vue-router'
import api from '@/api'

const errors = ref({})
const router = useRouter()
const route = useRoute() as RouteLocationNormalizedLoaded
const isContaCreate = route.name === 'ContasCreate'
const formStore = ref({
  id: '' + route.params.id,
  agencia: '',
  conta: '',
  valor: 0.0,
  transacao: 'CRED'
})
async function getConta() {
  const { conta } = await api.getConta(route.params.id as string)

  formStore.value.agencia = conta.agencia
  formStore.value.conta = conta.conta
}
function createCredito() {
  return api.creditarValorConta({
   credito: Object.assign({}, formStore.value),
  })
}

const { isLoading, execute: onSubmit } = useAsyncState(
  async () => {
    return (createCredito())
      .then(({ credito }) => {
        router.push(`/contas/${route.params.id}`)
      })
      .catch((error) => {
        errors.value = error.errors || {}
      })
  },
  null,
  {
    immediate: false,
  },
)

onMounted(() => {
  if (!isContaCreate)
  getConta()
})
</script>

<template>
  <div class="editor-page">
    <div class="container page">
      <div class="row">
        <div class="col-md-10 offset-md-1 col-xs-12">
          <error-messages :errors="errors" />
          <form autocomplete="on" @submit.prevent="() => onSubmit()">
            <fieldset>
              <fieldset class="form-group">
                <input
                  v-model="formStore.valor" required type="number" name="valor" class="form-control"
                  placeholder="Valor"
                >
              </fieldset>

              <button type="submit" :disabled="isLoading" class="btn btn-lg pull-xs-right btn-primary">
                {{ isContaCreate ? 'Criar Conta' : 'Creditar' }}
              </button>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
