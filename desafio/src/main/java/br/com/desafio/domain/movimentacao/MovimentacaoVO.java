package br.com.desafio.domain.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MovimentacaoVO(UUID id, String tipoTransacao, BigDecimal valor, LocalDateTime createdAt) {
    public MovimentacaoVO(Movimentacao movimentacao) {
        this(
                movimentacao.getId(),
                movimentacao.getTipoTransacao(),
                movimentacao.getValor(),
                movimentacao.getCreatedAt());
    }
}
