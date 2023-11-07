package br.com.desafio.domain.conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ContaMovimentacaoVO(
        UUID id, String agencia, String conta, BigDecimal valor, String transacao, LocalDateTime createdAt) {
    public ContaMovimentacaoVO(ContaMovimentacao contaMovimentacao) {
        this(
                contaMovimentacao.getMovimentacao().getId(),
                contaMovimentacao.getMovimentacao().getAgencia(),
                contaMovimentacao.getMovimentacao().getConta(),
                contaMovimentacao.getMovimentacao().getValor(),
                contaMovimentacao.getMovimentacao().getTipoTransacao(),
                contaMovimentacao.getMovimentacao().getCreatedAt());
    }
}
