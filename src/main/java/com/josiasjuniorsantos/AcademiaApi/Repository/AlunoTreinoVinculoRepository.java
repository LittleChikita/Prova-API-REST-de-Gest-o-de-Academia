package com.josiasjuniorsantos.AcademiaApi.Repository;

import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoId;
import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoVinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoTreinoVinculoRepository extends JpaRepository<AlunoTreinoVinculo, AlunoTreinoId> {

    List<AlunoTreinoVinculo> findByAlunoId(Long alunoId);
    List<AlunoTreinoVinculo> findByTreinoId(Long treinoId);
    boolean existsByAlunoIdAndTreinoId(Long alunoId, Long treinoId);
}
