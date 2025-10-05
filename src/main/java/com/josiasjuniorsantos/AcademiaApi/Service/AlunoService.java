package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import com.josiasjuniorsantos.AcademiaApi.Repository.AlunoRepository;
import com.josiasjuniorsantos.AcademiaApi.Repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PagamentoRepository pagamentoRepository;

    public AlunoService(AlunoRepository alunoRepository, PagamentoRepository pagamentoRepository) {
        this.alunoRepository = alunoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @Transactional
    public Aluno cadastrarAluno(String nome, String email, String cpf, Date dataNascimento, Plano plano) {
        boolean existe = alunoRepository.existsByCpf(cpf);
        if (existe) {
            throw new IllegalStateException("CPF já cadastrado.");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setCpf(cpf);
        aluno.setDataNascimento(dataNascimento);
        aluno.setPlano(plano);
        aluno.setAtivo(true);

        return alunoRepository.save(aluno);
    }

    @Transactional
    public Pagamento gerarPagamentoParaAluno(Long alunoId, String formaPagamento){
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno nao encontrado"));
        if (aluno.getPlano() == null){
            throw new RuntimeException("Aluno não possui plano");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(aluno);
        pagamento.setValorPagamento(aluno.getPlano().getValorMensal());
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setFormaPagamento(Pagamento.FormaPagamento.valueOf(formaPagamento));

        aluno.getPagamentos().add(pagamento);
        return pagamentoRepository.save(pagamento);
    }


}
