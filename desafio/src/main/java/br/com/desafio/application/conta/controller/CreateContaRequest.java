package br.com.desafio.application.conta.controller;

import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("conta")
public record CreateContaRequest(@NotEmpty String agencia, @NotEmpty String conta) {
    public CreateContaRequest {
        if (agencia == null) agencia = "";
        if (conta == null) conta = "";
    }
}
