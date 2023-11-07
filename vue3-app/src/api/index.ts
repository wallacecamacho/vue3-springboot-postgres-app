import request from '@/utils/request'
import type {
  Author,
  UpdateConta,
  User,
  UserInfo,
  Conta,
  CreateConta,
  ContaListProps,
  ValorConta,
  TransferirValorConta,
  CreditarValorConta,
  ExtratoSaldo,
  Transacao,
} from '@/types'

function login(params: { user: User }): Promise<{ user: UserInfo }> {
  return request({
    url: '/api/users/login',
    method: 'post',
    data: params,
  })
}

function register(params: { user: User }): Promise<{ user: UserInfo }> {
  return request({
    url: '/api/users',
    method: 'post',
    data: params,
  })
}




function getUserInfo(): Promise<{ user: UserInfo }> {
  return request({
    url: '/api/user',
  })
}

function updateUser(params: { user: UserInfo }): Promise<{ user: UserInfo }> {
  return request({
    url: '/api/user',
    method: 'put',
    data: params,
  })
}



function createConta(params: { conta: CreateConta }): Promise<{ conta: Conta }> {
  return request({
    url: '/api/contas',
    method: 'post',
    data: params,
  })
}

function transferirValorConta(params: { debito: TransferirValorConta }): Promise<{ debito: ValorConta }> {
  return request({
    url: '/api/movimentacao/debito',
    method: 'post',
    data: params,
  })
}

function creditarValorConta(params: { credito: CreditarValorConta }): Promise<{ credito: ValorConta }> {
  return request({
    url: '/api/movimentacao/credito',
    method: 'post',
    data: params,
  })
}



function updateConta(params: {
  conta: UpdateConta
  id: string
}): Promise<{ conta: Conta }> {
  return request({
    url: `/api/contas/${params.id}`,
    method: 'put',
    data: { conta: params.conta },
  })
}



function getConta(id: string): Promise<{ conta: Conta }> {
  return request({
    url: `/api/contas/${id}`,
  })
}


function getExtratoSaldo(id: string): Promise<{ extrato: ExtratoSaldo }> {
  return request({
    url: `/api/movimentacao/extrato/${id}`,
  })
}

function getExtSaldo(id: string): Promise<{ movimentacoes: Transacao[], saldo: string }> {
  return request({
    url: `/api/movimentacao/extrato/${id}`,
  })
}


function getContas(params: ContaListProps): Promise<{ contas: Conta[] }> {
  return request({
    url: `/api/contas`,
  })
}


function deleteConta(id: string): Promise<void> {
  return request({
    url: `/api/contas/${id}`,
    method: 'delete',
  })
}


function getPofile(username: string): Promise<{ profile: Author }> {
  return request({
    url: `/api/profiles/${username}`,
  })
}

export default {
  register,
  getUserInfo,
  login,
  updateUser,
  getPofile,
  createConta,
  updateConta,
  getContas,
  getConta,
  deleteConta,
  transferirValorConta,
  creditarValorConta,
  getExtSaldo,
  getExtratoSaldo
}
