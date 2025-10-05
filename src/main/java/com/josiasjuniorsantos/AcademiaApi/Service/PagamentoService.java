package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Repository.AlunoRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AlunoRepository alunoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, AlunoRepository alunoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Pagamento salvarPagamento(Long alunoId, Pagamento.FormaPagamento formaPagamento, LocalDateTime dataVencimento) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(()-> new IllegalArgumentException("Aluno não encontrado"));

        if(aluno.getPlano() == null){
            throw new IllegalStateException("Aluno não possui plano associado.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(aluno);
        pagamento.setValorPagamento(aluno.getPlano().getValorMensal());
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setFormaPagamento(formaPagamento);

        if (pagamento.getDataPagamento().isAfter(dataVencimento)) {
            pagamento.setStatusPagamento(Pagamento.StatusPagamento.ATRASADO);
        } else {
            pagamento.setStatusPagamento(Pagamento.StatusPagamento.PAGO);
        }

        aluno.getPagamentos().add(pagamento);
        return pagamentoRepository.save(pagamento);
    }
}
