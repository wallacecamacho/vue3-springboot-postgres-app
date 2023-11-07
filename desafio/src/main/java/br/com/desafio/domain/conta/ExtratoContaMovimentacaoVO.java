package br.com.desafio.domain.conta;

import java.math.BigDecimal;
import java.util.Set;

public record ExtratoContaMovimentacaoVO(Set<ContaMovimentacaoVO> movimentacoes, BigDecimal saldo) {}
