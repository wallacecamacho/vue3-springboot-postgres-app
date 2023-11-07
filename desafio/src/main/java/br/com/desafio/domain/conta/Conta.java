package br.com.desafio.domain.conta;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.desafio.domain.correntista.Correntista;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "conta")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Conta {

    @Id
    @Column(name = "conta_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(length = 5, nullable = false, unique = false)
    private String conta;	

    @Column(length = 5, nullable = false, unique = false)
    private String agencia;
	
    @ManyToOne
    @JoinColumn(name = "correntista_id", nullable = false)
    private Correntista correntista;
    
    @CreatedDate
    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "conta")
    Set<ContaMovimentacao> contaMovimentacao = new HashSet<>();
    
	@Builder
	public Conta(UUID id, String conta, String agencia, Correntista correntista) {
		super();
		this.id = id;
		this.conta = conta;
		this.agencia = agencia;
		this.correntista = correntista;
	}
	
    
	@Override
	public int hashCode() {
		return Objects.hash(agencia, conta, correntista, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(conta, other.conta)
				&& Objects.equals(correntista, other.correntista) && Objects.equals(id, other.id);
	}

    
}
