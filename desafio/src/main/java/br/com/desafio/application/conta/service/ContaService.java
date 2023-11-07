package br.com.desafio.application.conta.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.application.conta.controller.CreateContaRequest;
import br.com.desafio.domain.conta.Conta;
import br.com.desafio.domain.conta.ContaRepository;
import br.com.desafio.domain.conta.ContaVO;
import br.com.desafio.domain.user.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContaService {
	private final ContaRepository contaRepository;

	@Transactional
	public ContaVO createConta(User me, CreateContaRequest request) {
		if (contaRepository.existsByConta(request.conta())) {
			throw new IllegalArgumentException("Conta `%s` is already in use.".formatted(request.conta()));
		}

		Conta contav = Conta.builder()
				.agencia(request.agencia())
				.conta(request.conta())
				.correntista(me.getCorrentista())
				.build();

		contav = contaRepository.save(contav);
		return new ContaVO(contav);
	}
	

	@Transactional
	public ContaVO updateConta(User me, UUID id, CreateContaRequest request) {
		if (contaRepository.existsByConta(request.agencia())) {
			throw new IllegalArgumentException("Conta `%s` is already in use.".formatted(request.conta()));
		}
		
		Conta conta = contaRepository.getReferenceById(id);
		conta.setAgencia(request.agencia());
		conta.setConta(request.conta());

		conta = contaRepository.save(conta);
		return new ContaVO(conta);
	}
	
    @Transactional
    public void deleteConta(User me, UUID idConta) {
        Conta conta = contaRepository
                .findById(idConta)
                .orElseThrow(() -> new NoSuchElementException("Conta not found by id: `%d`".formatted(idConta)));

        contaRepository.delete(conta);
    }

	@Transactional(readOnly = true)
	public List<ContaVO> getContas(User me) {
		Set<Conta> contas = me.getCorrentista().getContas();
		return contas.stream()
                .map(conta -> new ContaVO(conta.getId(), conta.getAgencia(), conta.getConta(), conta.getCreatedAt()))
                .toList();
	}

    @Transactional(readOnly = true)
    public ContaVO getSingleConta(User me, UUID id) {
        Conta conta = findById(id);
        return new ContaVO(conta);
    }
    
    private Conta findById(UUID id) {
        return contaRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Conta not found by slug: `%s`".formatted(id)));
    }

}
