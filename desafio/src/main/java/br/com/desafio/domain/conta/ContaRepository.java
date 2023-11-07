package br.com.desafio.domain.conta;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.domain.correntista.Correntista;

import java.util.List;




public interface ContaRepository extends JpaRepository<Conta, UUID> {

    boolean existsByConta(String conta);
    
    Set<Conta> findByCorrentista(Correntista correntista);
    
    List<Conta> findByIdAndAgenciaAndConta(UUID id, String agencia, String conta);

    List<Conta> findByAgenciaAndConta(String agencia, String conta);
    
}