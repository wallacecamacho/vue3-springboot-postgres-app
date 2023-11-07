package br.com.desafio.application.movimentacao.controller;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("extratoSaldo")
public record ExtratoSaldoContaRequest(UUID id, @NotEmpty String agencia, @NotEmpty String conta) {}
