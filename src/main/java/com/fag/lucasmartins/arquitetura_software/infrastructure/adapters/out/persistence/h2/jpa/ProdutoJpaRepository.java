package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa;


import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByIdIn(List<Integer> ids);
}
