package br.com.desafio.domain.correntista;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrentistaRepository extends JpaRepository<Correntista, UUID> {

    Optional<Correntista> findById(UUID id);
}
