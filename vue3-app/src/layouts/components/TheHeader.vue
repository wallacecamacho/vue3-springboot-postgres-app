<script lang="ts" setup>
import { useUserStore } from '@/stores/useUserStore'

const store = useUserStore()
const { isLoggedIn, userInfo } = storeToRefs(store)
const navList = computed(() => [
  {
    label: 'Home',
    path: '/',
    show: true,
  },
  {
    label: 'Entrar',
    path: '/login',
    show: !isLoggedIn.value,
  },
  {
    label: 'Cadastrar',
    path: '/register',
    show: !isLoggedIn.value,
  },
  {
    label: 'Contas',
    path: '/contas/create',
    show: isLoggedIn.value,
    icon: 'ion-compose',
  },
  {
    label: 'Informações do perfil',
    path: '/settings',
    show: isLoggedIn.value,
    icon: 'ion-gear-a',
  },
  {
    label: userInfo.value?.username,
    path: `/profile/${store.userInfo?.username}`,
    show: isLoggedIn.value,
    pic: userInfo.value?.image,
  },
])
</script>

<template>
  <nav class="navbar navbar-light">
    <div class="container">
      <router-link class="navbar-brand" to="/">
        Desafio
      </router-link>
      <ul class="nav navbar-nav pull-xs-right">
        <template v-for="{ label, path, icon, pic, show } in navList" :key="label">
          <li v-if="show" class="nav-item">
            <router-link :to="path" class="nav-link" active-class="active">
              <template v-if="icon">
                <i :class="icon" />&nbsp;
              </template>
              <template v-if="pic">
                <img :src="pic" class="user-pic">
              </template>
              {{ label }}
            </router-link>
          </li>
        </template>
      </ul>
    </div>
  </nav>
</template>
