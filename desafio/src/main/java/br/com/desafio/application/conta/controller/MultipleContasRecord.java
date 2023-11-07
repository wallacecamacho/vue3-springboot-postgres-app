package br.com.desafio.application.conta.controller;

import java.util.List;

import br.com.desafio.domain.conta.ContaVO;

public record MultipleContasRecord(ContaVO[] contas) {
    public MultipleContasRecord(List<ContaVO> contas) {
        this(contas.toArray(ContaVO[]::new));
    }
}
