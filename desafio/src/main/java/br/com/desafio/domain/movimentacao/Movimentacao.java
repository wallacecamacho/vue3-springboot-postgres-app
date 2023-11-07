package br.com.desafio.domain.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.desafio.domain.conta.ContaMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "movimentacao")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movimentacao {
	
    @Id
    @Column(name = "movimentacao_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(length = 5, nullable = false, unique = false)
    private String conta;	

    @Column(length = 5, nullable = false, unique = false)
    private String agencia;
    
    @Column(name="tipo_transacao", length = 4, nullable = false, unique = false)
    private String tipoTransacao;
    
    @Digits(integer=5, fraction=2)
    @Column(name = "valor")
    private BigDecimal valor;
    
    @CreatedDate
    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @OneToMany(mappedBy = "movimentacao")
    Set<ContaMovimentacao> contaMovimentacao = new HashSet<>();


	@Builder
	public Movimentacao(String conta, String agencia, String tipoTransacao, BigDecimal valor) {
		this.conta = conta;
		this.agencia =  agencia;
		this.tipoTransacao = tipoTransacao;
		this.valor = valor;
	}


	@Override
	public int hashCode() {
		return Objects.hash(agencia, conta, createdAt, id, tipoTransacao);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(conta, other.conta)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(tipoTransacao, other.tipoTransacao);
	}

    
    
}
