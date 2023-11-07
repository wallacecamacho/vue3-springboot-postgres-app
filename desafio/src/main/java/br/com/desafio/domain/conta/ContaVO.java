package br.com.desafio.domain.conta;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContaVO(
		UUID id,
		String agencia,
		String conta,
		LocalDateTime createdAt) {
	public ContaVO(Conta contaVO) {
		this(contaVO.getId(),
				contaVO.getAgencia(),
				contaVO.getConta(),
				contaVO.getCreatedAt());
	}
}
