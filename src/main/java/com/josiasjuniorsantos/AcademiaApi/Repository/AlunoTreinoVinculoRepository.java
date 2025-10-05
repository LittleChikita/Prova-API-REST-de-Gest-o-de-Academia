package com.josiasjuniorsantos.AcademiaApi.Repository;

import com.josiasjuniorsantos.AcademiaApi.Model.AlunoTreinoVinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoTreinoVinculoRepository extends JpaRepository<AlunoTreinoVinculo, Long> {
}
