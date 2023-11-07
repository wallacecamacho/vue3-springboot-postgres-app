
export interface Conta {
  id: string
  agencia: string
  conta: string
  createdAt: string
}

export interface ExtratoSaldo {
  movimentacoes: Transacao[]
  saldo: string
}

export interface Transacao{
  id: string
  agencia: string
  conta: string
  transacao: string
  createdAt: string
}

export interface Author {
  username: string
  image: string
  bio: string
  following: boolean
}


export interface User {
  email?: string
  username: string
  password: string
}

export interface UserInfo {
  email: string
  token?: string
  username: string
  bio: string
  image: string
  password?: string
}


export interface CreateConta {
  agencia: string
  conta: string
}

export interface TransferirValorConta {
  id: string
  agencia: string
  conta: string
  valor: number
}

export interface CreditarValorConta {
  id: string
  agencia: string
  conta: string
  valor: number
  transacao: string
}

export interface ValorConta {
  id: string
  agencia: string
  conta: string
  valor: number
}


export interface UpdateConta {
  id: string
  agencia: string
  conta: string
}


export interface ContaToggleOptions {
  id: string
  agencia: string
  conta: string
  show?: boolean
  icon?: boolean
}


export interface ContaListProps {
  id: string
  agencia: string
  conta: string
  createdAt: string
}


