package br.com.desafio.domain.conta;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.desafio.domain.movimentacao.Movimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "conta_movimentacao")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContaMovimentacao {
    @EmbeddedId
    private ContaMovimentacaoId id;
    
    @MapsId("contaId")
    @JoinColumn(name = "conta_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta conta;
    
    @MapsId("movimentacaoId")
    @JoinColumn(name = "movimentacao_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movimentacao movimentacao;
    
    @CreatedDate
    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
	public ContaMovimentacao(UUID movimentacaoId, UUID contaId, Movimentacao movimentacao, Conta conta) {
		this.id = new ContaMovimentacaoId(movimentacaoId, contaId);
		this.conta = conta;
		this.movimentacao = movimentacao;
	}
    
	@Override
	public int hashCode() {
		return Objects.hash(conta, id, movimentacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaMovimentacao other = (ContaMovimentacao) obj;
		return Objects.equals(conta, other.conta) && Objects.equals(id, other.id)
				&& Objects.equals(movimentacao, other.movimentacao);
	}

    
}
