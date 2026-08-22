package com.organizador.financas.repository;

import com.organizador.financas.model.GastoAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoAdicionalRepository extends JpaRepository<GastoAdicional, Long> {
}
