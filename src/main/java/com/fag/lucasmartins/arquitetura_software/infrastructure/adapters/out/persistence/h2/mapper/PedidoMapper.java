package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PedidoEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PedidoProdutoEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PessoaEntity;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    private PedidoMapper() {}

    public static PedidoEntity toEntity(PedidoBO bo) {
        final PedidoEntity entity = new PedidoEntity();
        final PessoaEntity pessoaEntity = PessoaMapper.toEntity(bo.getPessoa());

        entity.setId(bo.getId());
        entity.setPessoa(pessoaEntity);
        entity.setCep(bo.getCep());
        entity.setValorTotal(bo.getValorTotal());

        final List<PedidoProdutoEntity> itemEntities = new ArrayList<>();
        if (bo.getItens() != null) {
            for (PedidoProdutoBO itemBO : bo.getItens()) {
                PedidoProdutoEntity itemEntity = PedidoItemMapper.toEntity(itemBO);
                itemEntity.setPedido(entity);
                itemEntities.add(itemEntity);
            }
        }
        entity.setItens(itemEntities);
        return entity;
    }

    public static PedidoBO toBO(PedidoEntity entity) {
        PedidoBO bo = new PedidoBO();
        PessoaBO pessoaBO = PessoaMapper.toBO(entity.getPessoa());

        bo.setId(entity.getId());
        bo.setPessoa(pessoaBO);
        bo.setCep(entity.getCep());
        bo.setValorTotal(entity.getValorTotal());

        List<PedidoProdutoBO> itens = new ArrayList<>();
        if (entity.getItens() != null) {
            for (PedidoProdutoEntity itemEntity : entity.getItens()) {
                PedidoProdutoBO itemBO = PedidoItemMapper.toBO(itemEntity);
                itens.add(itemBO);
            }
        }
        bo.setItens(itens);
        return bo;
    }
}
