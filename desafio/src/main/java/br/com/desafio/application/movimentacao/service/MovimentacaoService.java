package br.com.desafio.application.movimentacao.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import br.com.desafio.application.movimentacao.controller.CreateCreditoContaRequest;
import br.com.desafio.application.movimentacao.controller.CreateTransferenciaContaRequest;
import br.com.desafio.domain.conta.Conta;
import br.com.desafio.domain.conta.ContaMovimentacao;
import br.com.desafio.domain.conta.ContaMovimentacaoRepository;
import br.com.desafio.domain.conta.ContaMovimentacaoVO;
import br.com.desafio.domain.conta.ContaRepository;
import br.com.desafio.domain.conta.ExtratoContaMovimentacaoVO;
import br.com.desafio.domain.conta.SaldoVO;
import br.com.desafio.domain.movimentacao.Movimentacao;
import br.com.desafio.domain.movimentacao.MovimentacaoRepository;
import br.com.desafio.domain.user.User;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    private final ContaRepository contaRepository;
    private final ContaMovimentacaoRepository contaMovimentacaoRepository;

    @Transactional
    public SaldoVO saldo(User me, UUID id) {
        if (!contaRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta `%s` not exists.".formatted(id));
        }
        return null;
    }

    @Transactional
    public ContaMovimentacaoVO creditarValor(User me, CreateCreditoContaRequest request) {
        if (!contaRepository.existsById(request.id())) {
            throw new IllegalArgumentException("Conta `%s` not exists.".formatted(request.conta()));
        }

        List<Conta> contaList =
                contaRepository.findByIdAndAgenciaAndConta(request.id(), request.agencia(), request.conta());

        Conta conta = contaList.stream().findFirst().get();

        Movimentacao movimentacao = Movimentacao.builder()
                .agencia(request.agencia())
                .conta(request.conta())
                .valor(request.valor())
                .tipoTransacao(request.transacao())
                .build();

        movimentacao = movimentacaoRepository.save(movimentacao);

        ContaMovimentacao contaMovimentacao = ContaMovimentacao.builder()
                .conta(conta)
                .movimentacao(movimentacao)
                .build();
        contaMovimentacao.getId().setContaId(conta.getId());
        contaMovimentacao.getId().setMovimentacaoId(movimentacao.getId());

        movimentacao.getContaMovimentacao().add(contaMovimentacao);
        contaMovimentacao = contaMovimentacaoRepository.save(contaMovimentacao);
        return new ContaMovimentacaoVO(contaMovimentacao);
    }

    @Transactional
    public ContaMovimentacaoVO transferirValor(User me, CreateTransferenciaContaRequest request) {
        if (!contaRepository.existsById(request.id())) {
            throw new IllegalArgumentException("Conta `%s` not exists.".formatted(request.conta()));
        }

        List<BigDecimal> cred = new ArrayList<BigDecimal>();
        List<BigDecimal> deb = new ArrayList<BigDecimal>();
        Conta contaOrigem = contaRepository.getReferenceById(request.id());

        Set<ContaMovimentacao> movOrigemCred = contaOrigem.getContaMovimentacao().stream()
                .filter(contaMovimentacao ->
                        contaMovimentacao.getMovimentacao().getTipoTransacao().equals("CRED"))
                .collect(Collectors.toSet());

        Set<ContaMovimentacao> movOrigemDeb = contaOrigem.getContaMovimentacao().stream()
                .filter(contaMovimentacao ->
                        contaMovimentacao.getMovimentacao().getTipoTransacao().equals("TRAN"))
                .collect(Collectors.toSet());

        movOrigemCred.forEach(arg0 -> {
            cred.add(arg0.getMovimentacao().getValor());
        });
        movOrigemDeb.forEach(arg0 -> {
            deb.add(arg0.getMovimentacao().getValor());
        });

        BigDecimal transacoesCredito = cred.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal transacoesDebito = deb.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoConta = transacoesCredito.subtract(transacoesDebito);
        BigDecimal saldoNovoOrigem = new BigDecimal(0);
        if (saldoConta.compareTo(BigDecimal.ZERO) > 0 && saldoConta.compareTo(request.valor()) > 0) {
            saldoNovoOrigem = saldoConta.subtract(request.valor());
        }

        List<Conta> contaList = contaRepository.findByAgenciaAndConta(request.agencia(), request.conta());

        // DESTINO
        Conta contaDestino = contaList.stream().findFirst().get();

        Movimentacao movimentacao = Movimentacao.builder()
                .agencia(request.agencia())
                .conta(request.conta())
                .valor(request.valor())
                .tipoTransacao("CRED")
                .build();

        movimentacao = movimentacaoRepository.save(movimentacao);

        ContaMovimentacao contaMovimentacao = ContaMovimentacao.builder()
                .conta(contaDestino)
                .movimentacao(movimentacao)
                .build();
        contaMovimentacao.getId().setContaId(contaDestino.getId());
        contaMovimentacao.getId().setMovimentacaoId(movimentacao.getId());

        movimentacao.getContaMovimentacao().add(contaMovimentacao);
        contaMovimentacao = contaMovimentacaoRepository.save(contaMovimentacao);

        // ORIGEM
        Movimentacao movimentacaoOrigem = Movimentacao.builder()
                .agencia(contaOrigem.getAgencia())
                .conta(contaOrigem.getConta())
                .valor(saldoNovoOrigem)
                .tipoTransacao("TRAN")
                .build();

        movimentacaoOrigem = movimentacaoRepository.save(movimentacaoOrigem);

        ContaMovimentacao contaMovimentacaoOrigem = ContaMovimentacao.builder()
                .conta(contaOrigem)
                .movimentacao(movimentacaoOrigem)
                .build();
        contaMovimentacaoOrigem.getId().setContaId(contaOrigem.getId());
        contaMovimentacaoOrigem.getId().setMovimentacaoId(movimentacaoOrigem.getId());

        movimentacaoOrigem.getContaMovimentacao().add(contaMovimentacaoOrigem);
        contaMovimentacaoOrigem = contaMovimentacaoRepository.save(contaMovimentacaoOrigem);

        return new ContaMovimentacaoVO(contaMovimentacao);
    }

    @Transactional
    public ExtratoContaMovimentacaoVO extratoSaldo(User me, UUID id) {
        if (!contaRepository.existsById(id)) {
            throw new IllegalArgumentException("Conta `%s` not exists.".formatted(id));
        }

        List<BigDecimal> cred = new ArrayList<BigDecimal>();
        List<BigDecimal> deb = new ArrayList<BigDecimal>();
        Conta contaOrigem = contaRepository.getReferenceById(id);

        Set<ContaMovimentacaoVO> extr = contaOrigem.getContaMovimentacao().stream()
                .map(contaMovimentacao -> new ContaMovimentacaoVO(contaMovimentacao))
                .collect(Collectors.toSet());

        Set<ContaMovimentacao> movOrigemCred = contaOrigem.getContaMovimentacao().stream()
                .filter(contaMovimentacao ->
                        contaMovimentacao.getMovimentacao().getTipoTransacao().equals("CRED"))
                .collect(Collectors.toSet());

        Set<ContaMovimentacao> movOrigemDeb = contaOrigem.getContaMovimentacao().stream()
                .filter(contaMovimentacao ->
                        contaMovimentacao.getMovimentacao().getTipoTransacao().equals("TRAN"))
                .collect(Collectors.toSet());

        movOrigemCred.forEach(arg0 -> {
            cred.add(arg0.getMovimentacao().getValor());
        });
        movOrigemDeb.forEach(arg0 -> {
            deb.add(arg0.getMovimentacao().getValor());
        });

        BigDecimal transacoesCredito = cred.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal transacoesDebito = deb.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoConta = transacoesCredito.subtract(transacoesDebito);

        return new ExtratoContaMovimentacaoVO(extr, saldoConta);
    }

    private BigDecimal calculaSaldo(Conta conta) {
        return null;
    }
}
