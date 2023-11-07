package br.com.desafio.application.conta.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import br.com.desafio.application.conta.service.ContaService;
import br.com.desafio.domain.conta.ContaVO;
import br.com.desafio.domain.user.User;

@RestController
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @PostMapping("/api/contas")
    public SingleContaRecord createConta(User me, @RequestBody CreateContaRequest request) {
        ContaVO contaVO = contaService.createConta(me, request);
        return new SingleContaRecord(contaVO);
    }

    @GetMapping("/api/contas")
    public MultipleContasRecord getContas(User me) {
        List<ContaVO> contas = contaService.getContas(me);
        return new MultipleContasRecord(contas);
    }

    @GetMapping("/api/contas/{id}")
    public SingleContaRecord getSingleContas(User me, @PathVariable UUID id) {
        ContaVO conta = contaService.getSingleConta(me, id);
        return new SingleContaRecord(conta);
    }

    @PutMapping("/api/contas/{id}")
    public SingleContaRecord updateConta(User me, @PathVariable UUID id, @RequestBody CreateContaRequest request) {
        ContaVO contaVO = contaService.updateConta(me, id, request);
        return new SingleContaRecord(contaVO);
    }

    @DeleteMapping("/api/contas/{id}")
    public void deleteConta(User me, @PathVariable UUID id) {
        contaService.deleteConta(me, id);
    }
}
