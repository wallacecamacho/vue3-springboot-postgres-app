package br.com.desafio.application.movimentacao.controller;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.validation.constraints.NotEmpty;

@JsonRootName("extratoSaldo")
public record ExtratoSaldoContaRequest(
		UUID id,
		@NotEmpty String agencia, 
		@NotEmpty String conta) {

}
