package br.com.desafio.application.user.controller;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public record LoginRequest(String email, String password) {}
