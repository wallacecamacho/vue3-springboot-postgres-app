package br.com.desafio.domain.conta;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaMovimentacaoRepository extends JpaRepository<ContaMovimentacao, UUID> {

	
}