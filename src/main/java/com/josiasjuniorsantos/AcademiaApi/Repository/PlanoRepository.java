package com.josiasjuniorsantos.AcademiaApi.Repository;

import com.josiasjuniorsantos.AcademiaApi.Model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {
}
