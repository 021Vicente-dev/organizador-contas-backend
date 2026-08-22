package com.organizador.financas.repository;

import com.organizador.financas.model.HistoricoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoPagamentoRepository extends JpaRepository<HistoricoPagamento, Long> {
}
