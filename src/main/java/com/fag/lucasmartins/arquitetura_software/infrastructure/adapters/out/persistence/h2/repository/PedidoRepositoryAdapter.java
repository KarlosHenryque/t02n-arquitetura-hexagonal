package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.repository;

import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.PedidoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PedidoEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa.PedidoJpaRepository;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper.PedidoMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository pedidoJpaRepository;

    public PedidoRepositoryAdapter(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    @Override
    public PedidoBO salvar(PedidoBO pedidoBO) {
        final PedidoEntity entity = PedidoMapper.toEntity(pedidoBO);

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        final PedidoEntity pedidoSalvo = pedidoJpaRepository.save(entity);
        return PedidoMapper.toBO(pedidoSalvo);
    }
}
