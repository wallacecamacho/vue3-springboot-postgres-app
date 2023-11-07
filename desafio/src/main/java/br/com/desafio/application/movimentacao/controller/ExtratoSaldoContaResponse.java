package br.com.desafio.application.movimentacao.controller;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.desafio.domain.conta.ExtratoContaMovimentacaoVO;

@JsonRootName("extrato")
public record ExtratoSaldoContaResponse(ExtratoContaMovimentacaoVO extrato) {
	public ExtratoSaldoContaResponse {}

}
