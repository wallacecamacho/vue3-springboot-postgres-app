package br.com.desafio.application.conta.controller;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.validation.constraints.NotEmpty;



@JsonRootName("conta")
public record CreateContaRequest(@NotEmpty String agencia, @NotEmpty String conta) {
    public CreateContaRequest {
        if (agencia == null) agencia = "";
        if (conta == null) conta = "";
    }
}