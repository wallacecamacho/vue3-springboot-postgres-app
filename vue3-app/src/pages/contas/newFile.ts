import type { RouteLocationNormalizedLoaded } from 'vue-router';
import { useAsyncState } from '@vueuse/core';
import api from '@/api';
import { formatDate } from '@/utils';
import type { Conta, ExtratoSaldo } from '@/types';
import { useUserStore } from '@/stores/useUserStore';
import { __VLS_TypePropsToRuntimeProps } from './[[id]].vue.js';

export default (() => {
const __VLS_setup = async () => {
const route = useRoute() as RouteLocationNormalizedLoaded;
const store = useUserStore();
const conta = ref<Conta>({
id: '',
agencia: '',
conta: '',
createdAt: ''
});

const movimentacoes = ref<ExtratoSaldo>({
movimentacoes: []
});


const extSaldo = ref<ExtSaldo>({
movimentacoes: [],
saldo: ''
});

const isAuthor = computed(() => {
return store.userInfo?.username;
});
async function getActive() {
const res = await api.getConta(route.params.id as string);
conta.value = res.conta;
}

const props = defineProps<{ remoteParams: ExtratoSaldo; }>();
//defineProps({ transacao: { type: Object as PropType<Transacao>, required: true } })
const {
state, error, isLoading, execute: getExtSaldo, } = useAsyncState(
async () => {

return await api.getExtSaldo(route.params.id as string);
},
{
extrato
}
);

watch(
() => props.remoteParams,
() => {
getExtSaldo();
}
);

onMounted(() => {
getActive();
});
const __VLS_publicComponent = (await import('vue')).defineComponent({
props: ({} as __VLS_TypePropsToRuntimeProps<{ remoteParams: ExtratoSaldo; }>),
setup() {
return {};
},
});

const __VLS_componentsOption = {};

let __VLS_name!: '[[id]]';
function __VLS_template() {
let __VLS_ctx!: InstanceType<import('./__VLS_types.js').PickNotAny<typeof __VLS_publicComponent, new () => {}>> & InstanceType<import('./__VLS_types.js').PickNotAny<typeof __VLS_internalComponent, new () => {}>> & {};
/* Components */
let __VLS_localComponents!: NonNullable<typeof __VLS_internalComponent extends { components: infer C; } ? C : {}> & typeof __VLS_componentsOption & typeof __VLS_ctx;
let __VLS_otherComponents!: typeof __VLS_localComponents & import('./__VLS_types.js').GlobalComponents;
let __VLS_own!: import('./__VLS_types.js').SelfComponent<typeof __VLS_name, typeof __VLS_internalComponent & typeof __VLS_publicComponent & (new () => { $slots: typeof __VLS_slots; }) >;
let __VLS_components!: typeof __VLS_otherComponents & Omit<typeof __VLS_own, keyof typeof __VLS_otherComponents>;
/* Style Scoped */
type __VLS_StyleScopedClasses = {};
let __VLS_styleScopedClasses!: __VLS_StyleScopedClasses | keyof __VLS_StyleScopedClasses | (keyof __VLS_StyleScopedClasses)[];
/* CSS variable injection */
/* CSS variable injection end */
let __VLS_templateComponents!: {} &
import('./__VLS_types.js').WithComponent<'EditContaButton', typeof __VLS_components, 'EditContaButton', 'editContaButton', 'edit-conta-button'> &
import('./__VLS_types.js').WithComponent<'DeleteContaButton', typeof __VLS_components, 'DeleteContaButton', 'deleteContaButton', 'delete-conta-button'> &
import('./__VLS_types.js').WithComponent<'CreditaContaButton', typeof __VLS_components, 'CreditaContaButton', 'creditaContaButton', 'credita-conta-button'> &
import('./__VLS_types.js').WithComponent<'TransfereContaButton', typeof __VLS_components, 'TransfereContaButton', 'transfereContaButton', 'transfere-conta-button'> &
import('./__VLS_types.js').WithComponent<'RouterLink', typeof __VLS_components, 'RouterLink', 'routerLink', 'router-link'>;
__VLS_components.EditContaButton; __VLS_components.EditContaButton; __VLS_components.editContaButton; __VLS_components.editContaButton; __VLS_components['edit-conta-button']; __VLS_components['edit-conta-button'];
// @ts-ignore
[EditContaButton, EditContaButton,];
__VLS_components.DeleteContaButton; __VLS_components.DeleteContaButton; __VLS_components.deleteContaButton; __VLS_components.deleteContaButton; __VLS_components['delete-conta-button']; __VLS_components['delete-conta-button'];
// @ts-ignore
[DeleteContaButton, DeleteContaButton,];
__VLS_components.CreditaContaButton; __VLS_components.CreditaContaButton; __VLS_components.creditaContaButton; __VLS_components.creditaContaButton; __VLS_components['credita-conta-button']; __VLS_components['credita-conta-button'];
// @ts-ignore
[CreditaContaButton, CreditaContaButton,];
__VLS_components.TransfereContaButton; __VLS_components.TransfereContaButton; __VLS_components.transfereContaButton; __VLS_components.transfereContaButton; __VLS_components['transfere-conta-button']; __VLS_components['transfere-conta-button'];
// @ts-ignore
[TransfereContaButton, TransfereContaButton,];
__VLS_components.RouterLink; __VLS_components.RouterLink; __VLS_components.RouterLink; __VLS_components.RouterLink; __VLS_components.routerLink; __VLS_components.routerLink; __VLS_components.routerLink; __VLS_components.routerLink; __VLS_components['router-link']; __VLS_components['router-link']; __VLS_components['router-link']; __VLS_components['router-link'];
// @ts-ignore
[RouterLink, RouterLink, RouterLink, RouterLink,];
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-page"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("banner"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("container"), };
{
({} as JSX.IntrinsicElements).h1;
({} as JSX.IntrinsicElements).h1;
(__VLS_x as JSX.IntrinsicElements)['h1'] = {};
(__VLS_ctx.conta.conta);
// @ts-ignore
[conta,];
}
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-meta"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("info"), };
{
({} as JSX.IntrinsicElements).span;
({} as JSX.IntrinsicElements).span;
(__VLS_x as JSX.IntrinsicElements)['span'] = { class: ("date"), };
(__VLS_ctx.formatDate(__VLS_ctx.conta.createdAt));
// @ts-ignore
[formatDate, conta,];
}
}
if (__VLS_ctx.isAuthor) {
// @ts-ignore
[isAuthor,];
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.EditContaButton>) = { to: ((`/contas/editor/${__VLS_ctx.conta.id}`)), };
// @ts-ignore
[conta,];
}
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.DeleteContaButton>) = { id: ((__VLS_ctx.conta.id)), };
// @ts-ignore
[conta,];
}
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.CreditaContaButton>) = { to: ((`/contas/creditar/${__VLS_ctx.conta.id}`)), };
// @ts-ignore
[conta,];
}
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.TransfereContaButton>) = { to: ((`/contas/transferir/${__VLS_ctx.conta.id}`)), };
// @ts-ignore
[conta,];
}
}
}
}
}
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("container page"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("row article-content"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("col-xs-12"), };
{
({} as JSX.IntrinsicElements).p;
({} as JSX.IntrinsicElements).p;
(__VLS_x as JSX.IntrinsicElements)['p'] = {};
(__VLS_ctx.conta.agencia);
// @ts-ignore
[conta,];
(__VLS_ctx.conta.conta);
// @ts-ignore
[conta,];
}
{
({} as JSX.IntrinsicElements).p;
({} as JSX.IntrinsicElements).p;
(__VLS_x as JSX.IntrinsicElements)['p'] = {};
(await import('./__VLS_types.js')).directiveFunction(__VLS_ctx.vHtml)(__VLS_ctx.conta.id);
// @ts-ignore
[conta,];
}
}
}
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("row article-content"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("col-xs-12"), };
if (__VLS_ctx.error) {
// @ts-ignore
[error,];
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-preview"), };
}
}
else if (__VLS_ctx.isLoading) {
// @ts-ignore
[isLoading,];
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-preview"), };
}
}
else if (__VLS_ctx.state.extrato.movimentacoes.length === 0) {
// @ts-ignore
[state,];
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-preview"), };
}
}
else {
for (const [transacao, index] of (await import('./__VLS_types.js')).getVForSourceType(__VLS_ctx.state.extrato.movimentacoes)) {
// @ts-ignore
[state,];
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-list"), key: ((index)), transacao: ((transacao)), };
{
({} as JSX.IntrinsicElements).p;
({} as JSX.IntrinsicElements).p;
(__VLS_x as JSX.IntrinsicElements)['p'] = {};
(transacao);
}
}
}
}
}
}
{
({} as JSX.IntrinsicElements).hr;
(__VLS_x as JSX.IntrinsicElements)['hr'] = {};
}
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-actions"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("article-meta"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("info"), };
{
({} as JSX.IntrinsicElements).span;
({} as JSX.IntrinsicElements).span;
(__VLS_x as JSX.IntrinsicElements)['span'] = { class: ("date"), };
(__VLS_ctx.formatDate(__VLS_ctx.conta.createdAt));
// @ts-ignore
[formatDate, conta,];
}
}
if (__VLS_ctx.isAuthor) {
// @ts-ignore
[isAuthor,];
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.EditContaButton>) = { to: ((`/contas/editor/${__VLS_ctx.conta.id}`)), };
// @ts-ignore
[conta,];
}
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.DeleteContaButton>) = { id: ((__VLS_ctx.conta.id)), };
// @ts-ignore
[conta,];
}
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.CreditaContaButton>) = { to: ((`/contas/creditar/${__VLS_ctx.conta.id}`)), };
// @ts-ignore
[conta,];
}
{
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.TransfereContaButton>) = { to: ((`/contas/transferir/${__VLS_ctx.conta.id}`)), };
// @ts-ignore
[conta,];
}
}
}
}
if (!__VLS_ctx.store.isLoggedIn) {
// @ts-ignore
[store,];
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("row"), };
{
({} as JSX.IntrinsicElements).div;
({} as JSX.IntrinsicElements).div;
(__VLS_x as JSX.IntrinsicElements)['div'] = { class: ("col-xs-12 col-md-8 offset-md-2"), };
{
({} as JSX.IntrinsicElements).p;
({} as JSX.IntrinsicElements).p;
(__VLS_x as JSX.IntrinsicElements)['p'] = {};
{
__VLS_templateComponents.RouterLink;
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.RouterLink>) = { to: ("/login"), };
}
{
__VLS_templateComponents.RouterLink;
(__VLS_x as import('./__VLS_types.js').ComponentProps<typeof __VLS_templateComponents.RouterLink>) = { to: ("/register"), };
}
}
}
}
}
}
}
if (typeof __VLS_styleScopedClasses === 'object' && !Array.isArray(__VLS_styleScopedClasses)) {
}
declare var __VLS_slots: {};
return __VLS_slots;
}
const __VLS_internalComponent = (await import('vue')).defineComponent({
setup() {
return {
formatDate: formatDate,
store: store,
conta: conta,
isAuthor: isAuthor,
state: state,
error: error,
isLoading: isLoading,
};
},
});
return {} as typeof __VLS_publicComponent;
};
return {} as typeof __VLS_setup extends () => Promise<infer T> ? T : never;
})({} as any);
