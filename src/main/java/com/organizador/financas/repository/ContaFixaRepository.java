package com.organizador.financas.repository;

import com.organizador.financas.model.ContaFixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaFixaRepository extends JpaRepository<ContaFixa, Long> {
}
