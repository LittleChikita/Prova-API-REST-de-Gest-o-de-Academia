package com.josiasjuniorsantos.AcademiaApi.Repository;

import com.josiasjuniorsantos.AcademiaApi.Model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
