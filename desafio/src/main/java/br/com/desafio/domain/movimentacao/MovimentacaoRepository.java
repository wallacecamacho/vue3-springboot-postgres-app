package br.com.desafio.domain.movimentacao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository  extends JpaRepository<Movimentacao, UUID> {

}
