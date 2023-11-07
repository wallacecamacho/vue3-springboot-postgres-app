<script lang="ts" setup>
import { useAsyncState } from '@vueuse/core'
import type { RouteLocationNormalizedLoaded } from 'vue-router'
import api from '@/api'

const errors = ref({})
const router = useRouter()
const route = useRoute() as RouteLocationNormalizedLoaded
const isContaCreate = route.name === 'ContasCreate'
const formStore = ref({
  id: '',
  agencia: '',
  conta: '',
})
async function getConta() {
  const { conta } = await api.getConta(route.params.id as string)

  formStore.value.agencia = conta.agencia
  formStore.value.conta = conta.conta
}
function createConta() {
  return api.createConta({
  conta: Object.assign({}, formStore.value),
  })
}

function updateConta() {
  return api.updateConta({
    conta: Object.assign({}, formStore.value),
    id: route.params.id as string,
  })
}

const { isLoading, execute: onSubmit } = useAsyncState(
  async () => {
    return (isContaCreate ? createConta() : updateConta())
      .then(({ conta }) => {
        router.push(`/contas/${conta.id}`)
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
                  v-model="formStore.agencia" required type="text" name="title" placeholder="Agencia"
                  class="form-control form-control-lg"
                >
              </fieldset>
              <fieldset class="form-group">
                <input
                  v-model="formStore.conta" required type="text" name="description" class="form-control"
                  placeholder="Conta"
                >
              </fieldset>

              <button type="submit" :disabled="isLoading" class="btn btn-lg pull-xs-right btn-primary">
                {{ isContaCreate ? 'Criar Conta' : 'Editar' }}
              </button>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
