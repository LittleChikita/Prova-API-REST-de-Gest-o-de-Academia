package com.josiasjuniorsantos.AcademiaApi.Repository;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByAluno(Aluno aluno);

    List<Pagamento> findByAlunoAndStatusPagamento(Aluno aluno, Pagamento.StatusPagamento statusPagamento);
}
