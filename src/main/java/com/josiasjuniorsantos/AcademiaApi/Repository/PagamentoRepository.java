package com.josiasjuniorsantos.AcademiaApi.Repository;

import com.josiasjuniorsantos.AcademiaApi.Model.Aluno;
import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByAlunoId(Long alunoId);
    List<Pagamento> findByAluno_PlanoIsNullAndAluno_AtivoFalse();
    List<Pagamento> findByCobrancaId(Long cobrancaId);
}
