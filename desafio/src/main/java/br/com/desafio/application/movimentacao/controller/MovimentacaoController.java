package br.com.desafio.application.movimentacao.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import br.com.desafio.application.movimentacao.service.MovimentacaoService;
import br.com.desafio.domain.conta.ContaMovimentacaoVO;
import br.com.desafio.domain.conta.ExtratoContaMovimentacaoVO;
import br.com.desafio.domain.conta.SaldoVO;
import br.com.desafio.domain.user.User;

@RestController
@RequiredArgsConstructor
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;

    @PostMapping("/api/movimentacao/credito")
    public SingleTransferenciaContaRecord createCreditoValor(User me, @RequestBody CreateCreditoContaRequest request) {
        ContaMovimentacaoVO contaMovimentacaoVO = movimentacaoService.creditarValor(me, request);
        return new SingleTransferenciaContaRecord(contaMovimentacaoVO);
    }

    @PostMapping("/api/movimentacao/debito")
    public SingleTransferenciaContaRecord createTransferenciaValor(
            User me, @RequestBody CreateTransferenciaContaRequest request) {
        ContaMovimentacaoVO contaMovimentacaoVO = movimentacaoService.transferirValor(me, request);
        return new SingleTransferenciaContaRecord(contaMovimentacaoVO);
    }

    @PostMapping("/api/movimentacao/saldo")
    public SaldoContaRecord movimentacaoSaldo(User me, @PathVariable UUID id) {
        SaldoVO saldo = movimentacaoService.saldo(me, id);
        return new SaldoContaRecord(saldo);
    }

    @GetMapping("/api/movimentacao/extrato/{id}")
    public ExtratoSaldoContaResponse movimentacaoExtrato(User me, @PathVariable UUID id) {
        ExtratoContaMovimentacaoVO extratoSaldo = movimentacaoService.extratoSaldo(me, id);
        return new ExtratoSaldoContaResponse(extratoSaldo);
    }
}
