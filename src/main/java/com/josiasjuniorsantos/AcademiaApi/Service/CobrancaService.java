package com.josiasjuniorsantos.AcademiaApi.Service;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;
import com.josiasjuniorsantos.AcademiaApi.Repository.CobrancaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CobrancaService {

    private final CobrancaRepository cobrancaRepository;

    public CobrancaService(CobrancaRepository cobrancaRepository) {
        this.cobrancaRepository = cobrancaRepository;
    }

    @Transactional
    public Cobranca gerarCobranca(Aluno aluno) {
        if (aluno.getPlano() == null) {
            throw new IllegalStateException("Aluno não possui plano associado.");
        }

        Cobranca cobranca = new Cobranca();
        cobranca.setAluno(aluno);
        cobranca.setValor(aluno.getPlano().getValorMensal());
        cobranca.setDataVencimento(LocalDate.now().plusDays(30));
        cobranca.setStatus(Cobranca.StatusCobranca.PENDENTE);

        return cobrancaRepository.save(cobranca);
    }

    @Transactional
    public List<Cobranca> listarCobrancasDoAluno(Long alunoId) {
        return cobrancaRepository.findByAlunoId(alunoId);
    }
}
