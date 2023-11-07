package br.com.desafio.application.movimentacao.controller;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("credito")
public record CreateCreditoContaRequest(
        UUID id, @NotEmpty String agencia, @NotEmpty String conta, BigDecimal valor, String transacao) {
    public CreateCreditoContaRequest {}
}
