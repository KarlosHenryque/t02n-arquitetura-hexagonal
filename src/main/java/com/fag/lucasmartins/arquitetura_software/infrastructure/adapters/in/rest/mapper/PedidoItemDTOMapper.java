package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PedidoProdutoDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.ProdutoDTO;

public class PedidoItemDTOMapper {

    private PedidoItemDTOMapper() {
    }

    public static PedidoProdutoBO toBO(PedidoProdutoDTO dto) {
        final PedidoProdutoBO bo = new PedidoProdutoBO();
        final ProdutoBO produtoBO = ProdutoDTOMapper.toBo(dto.getProduto());

        bo.setId(dto.getId());
        bo.setProduto(produtoBO);
        bo.setQuantidade(dto.getQuantidade());

        return bo;
    }

    public static PedidoProdutoDTO toDTO(PedidoProdutoBO itemBO) {
        final PedidoProdutoDTO dto = new PedidoProdutoDTO();
        final ProdutoDTO produtoDTO = ProdutoDTOMapper.toDto(itemBO.getProduto());

        dto.setId(itemBO.getId());
        dto.setProduto(produtoDTO);
        dto.setQuantidade(itemBO.getQuantidade());
        dto.setSubtotal(itemBO.getSubtotal());

        return dto;
    }
}
