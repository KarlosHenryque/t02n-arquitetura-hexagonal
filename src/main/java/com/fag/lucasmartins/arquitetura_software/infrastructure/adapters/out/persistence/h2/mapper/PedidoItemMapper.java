package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PedidoProdutoEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.ProdutoEntity;

public class PedidoItemMapper {

    private PedidoItemMapper() {
    }

    public static PedidoProdutoBO toBO(PedidoProdutoEntity entity) {
        final PedidoProdutoBO itemBO = new PedidoProdutoBO();
        final ProdutoBO produtoBO = ProdutoMapper.toBO(entity.getProduto());

        itemBO.setId(entity.getId());
        itemBO.setProduto(produtoBO);
        itemBO.setQuantidade(entity.getQuantidade());
        itemBO.setSubtotal(entity.getSubtotal());

        return itemBO;
    }

    public static PedidoProdutoEntity toEntity(PedidoProdutoBO bo) {
        final PedidoProdutoEntity entity = new PedidoProdutoEntity();
        final ProdutoEntity produtoEntity = ProdutoMapper.toEntity(bo.getProduto());

        entity.setId(bo.getId());
        entity.setProduto(produtoEntity);
        entity.setQuantidade(bo.getQuantidade());
        entity.setSubtotal(bo.getSubtotal());
        return entity;
    }
}
