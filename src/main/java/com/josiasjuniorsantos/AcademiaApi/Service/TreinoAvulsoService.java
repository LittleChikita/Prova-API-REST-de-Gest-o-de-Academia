package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TreinoAvulsoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoService pagamentoService;

    @Value("${treino.avulso.valor}")
    private BigDecimal valorTreinoAvulso;

    public TreinoAvulsoService(PagamentoRepository pagamentoRepository, PagamentoService pagamentoService) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoService = pagamentoService;
    }

    @Transactional
    public Pagamento registrarTreinoAvulso(String nome, String email, Pagamento.FormaPagamento formaPagamento) {

        Aluno alunoTemp = new Aluno();
        alunoTemp.setNome(nome);
        alunoTemp.setEmail(email);
        alunoTemp.setAtivo(false);
        alunoTemp.setPlano(null);

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(alunoTemp);
        pagamento.setValorPagamento(valorTreinoAvulso);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setStatusPagamento(Pagamento.StatusPagamento.PAGO); // assume pagamento à vista

        alunoTemp.getPagamentos().add(pagamento);

        return pagamentoRepository.save(pagamento);
    }


    @Transactional
    public void vincularTreinoAvulsoAAlunoCadastrado(Aluno aluno, Pagamento treinoAvulso) {
        treinoAvulso.setAluno(aluno);
        aluno.getPagamentos().add(treinoAvulso);
        pagamentoRepository.save(treinoAvulso);
    }
}
