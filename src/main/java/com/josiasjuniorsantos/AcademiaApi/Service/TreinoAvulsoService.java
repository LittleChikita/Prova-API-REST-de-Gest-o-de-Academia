package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Repository.CobrancaRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TreinoAvulsoService {

    private final PagamentoRepository pagamentoRepository;
    private final CobrancaRepository cobrancaRepository;

    @Value("${treino.avulso.valor}")
    private BigDecimal valorTreinoAvulso;

    public TreinoAvulsoService(PagamentoRepository pagamentoRepository, CobrancaRepository cobrancaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.cobrancaRepository = cobrancaRepository;
    }

    @Transactional
    public Pagamento registrarTreinoAvulso(String nome, String email, Pagamento.FormaPagamento formaPagamento) {
        Aluno alunoTemp = new Aluno();
        alunoTemp.setNome(nome);
        alunoTemp.setEmail(email);
        alunoTemp.setAtivo(false);
        alunoTemp.setPlano(null);

        Cobranca cobranca = new Cobranca();
        cobranca.setAluno(alunoTemp);
        cobranca.setValor(valorTreinoAvulso);
        cobranca.setDataVencimento(LocalDate.now());
        cobranca.setStatus(Cobranca.StatusCobranca.PAGA);

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(alunoTemp);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setCobranca(cobranca);

        cobranca.setPagamento(pagamento);

        cobrancaRepository.save(cobranca);

        return pagamento;
    }

    @Transactional
    public void vincularTreinoAvulsoAAlunoCadastrado(Aluno aluno, Pagamento treinoAvulso) {
        treinoAvulso.setAluno(aluno);
        treinoAvulso.getCobranca().setAluno(aluno);

        aluno.getPagamentos().add(treinoAvulso);

        pagamentoRepository.save(treinoAvulso);
    }

    @Transactional
    public List<Pagamento> listarTreinosAvulsos() {
        return pagamentoRepository.findByAluno_PlanoIsNullAndAluno_AtivoFalse();
    }

}
