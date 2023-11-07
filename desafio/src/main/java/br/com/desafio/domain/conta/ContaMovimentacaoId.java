package br.com.desafio.domain.conta;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContaMovimentacaoId implements Serializable{
    private UUID movimentacaoId;
    private UUID contaId;
    
    @Builder
	public ContaMovimentacaoId(UUID movimentacaoId, UUID contaId) {
		this.movimentacaoId = movimentacaoId;
		this.contaId = contaId;
	}
    
}
