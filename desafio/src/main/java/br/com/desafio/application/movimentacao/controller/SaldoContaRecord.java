package br.com.desafio.application.movimentacao.controller;

import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.desafio.domain.conta.SaldoVO;

@JsonRootName("saldo")
public record SaldoContaRecord(SaldoVO saldo) {
    public SaldoContaRecord {}
}
