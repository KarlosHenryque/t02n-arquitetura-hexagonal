package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.ProdutoDTO;

public class ProdutoDTOMapper {

    private ProdutoDTOMapper() {
    }

    public static ProdutoBO toBo(ProdutoDTO dto) {
        final ProdutoBO bo = new ProdutoBO();
        bo.setId(dto.getId());
        bo.setNome(dto.getNome());
        bo.setEstoque(dto.getEstoque());
        bo.setPreco(dto.getPreco());

        return bo;
    }

    public static ProdutoDTO toDto(ProdutoBO bo) {
        final ProdutoDTO dto = new ProdutoDTO();
        dto.setId(bo.getId());
        dto.setNome(bo.getNome());
        dto.setPreco(bo.getPreco());
        dto.setEstoque(bo.getEstoque());

        return dto;
    }

}
