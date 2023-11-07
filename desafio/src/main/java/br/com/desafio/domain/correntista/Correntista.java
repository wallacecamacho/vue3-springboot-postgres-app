package br.com.desafio.domain.correntista;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import br.com.desafio.domain.conta.Conta;
import br.com.desafio.domain.user.User;

@Entity
@Getter
@Builder
@Table(name = "correntista")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Correntista {

    @Id
    @Column(name = "correntista_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 11, nullable = false, unique = false)
    private String cpf;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "correntista")
    private Set<Conta> contas = new HashSet<>();

    @Builder
    public Correntista(UUID id, String cpf, User user) {
        super();
        this.id = id;
        this.cpf = cpf;
        this.contas = new HashSet<>();
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contas, cpf, id, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Correntista other = (Correntista) obj;
        return Objects.equals(contas, other.contas)
                && Objects.equals(cpf, other.cpf)
                && Objects.equals(id, other.id)
                && Objects.equals(user, other.user);
    }
}
