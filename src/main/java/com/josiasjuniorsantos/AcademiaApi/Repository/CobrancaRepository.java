package com.josiasjuniorsantos.AcademiaApi.Repository;

import com.josiasjuniorsantos.AcademiaApi.Model.Cobranca;
import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {

    List<Cobranca> findByAluno(Aluno aluno);

    List<Cobranca> findByAlunoId(Long alunoId);

    List<Cobranca> findByAlunoAndStatus(Aluno aluno, Cobranca.StatusCobranca status);
}
