package br.com.desafio.domain.conta;

import java.math.BigDecimal;
import java.util.UUID;

public record SaldoVO(UUID id, String agencia, String conta, BigDecimal saldo) {
    public SaldoVO {}
}
