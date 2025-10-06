package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;
import com.josiasjuniorsantos.AcademiaApi.Repository.PagamentoRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.CobrancaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final CobrancaRepository cobrancaRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, CobrancaRepository cobrancaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.cobrancaRepository = cobrancaRepository;
    }

    @Transactional
    public Pagamento registrarPagamento(Long cobrancaId, Pagamento.FormaPagamento formaPagamento) {
        Cobranca cobranca = cobrancaRepository.findById(cobrancaId)
                .orElseThrow(() -> new IllegalArgumentException("Cobrança não encontrada"));

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(cobranca.getAluno());
        pagamento.setCobranca(cobranca);
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setDataPagamento(LocalDateTime.now());

        if (LocalDateTime.now().isAfter(cobranca.getDataVencimento().atStartOfDay())) {
            cobranca.setStatus(Cobranca.StatusCobranca.ATRASADA);
        } else {
            cobranca.setStatus(Cobranca.StatusCobranca.PAGA);
        }

        cobranca.setPagamento(pagamento);

        return pagamentoRepository.save(pagamento);
    }

    @Transactional
    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    @Transactional
    public List<Pagamento> listarPagamentosDoAluno(Long alunoId) {
        return pagamentoRepository.findByAlunoId(alunoId);
    }

    @Transactional
    public Pagamento consultarPagamento(Long pagamentoId) {
        return pagamentoRepository.findById(pagamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
    }
}
