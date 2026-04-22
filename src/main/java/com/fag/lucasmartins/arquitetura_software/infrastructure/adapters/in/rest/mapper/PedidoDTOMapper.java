package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PedidoDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PedidoProdutoDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaDTO;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTOMapper {

    private PedidoDTOMapper() {
    }

    public static PedidoBO toBo(PedidoDTO dto) {
        final PedidoBO bo = new PedidoBO();
        final PessoaBO pessoaBO = PessoaDTOMapper.toBo(dto.getPessoa());

        bo.setId(dto.getId());
        bo.setPessoa(pessoaBO);
        bo.setCep(dto.getCep());
        final List<PedidoProdutoBO> itens = new ArrayList<>();
        for (PedidoProdutoDTO itemDTO : dto.getItens()) {
            final PedidoProdutoBO itemBO = PedidoItemDTOMapper.toBO(itemDTO);
            itens.add(itemBO);
        }
        bo.setItens(itens);

        return bo;
    }

    public static PedidoDTO toDto(PedidoBO bo) {
        final PedidoDTO dto = new PedidoDTO();
        final PessoaDTO pessoaDTO = PessoaDTOMapper.toDto(bo.getPessoa());

        dto.setId(bo.getId());
        dto.setPessoa(pessoaDTO);
        dto.setCep(bo.getCep());
        dto.setValorTotal(bo.getValorTotal());
        final List<PedidoProdutoDTO> itensDTO = new ArrayList<>();
        for (PedidoProdutoBO itemBO : bo.getItens()) {
            final PedidoProdutoDTO itemDTO = PedidoItemDTOMapper.toDTO(itemBO);
            itensDTO.add(itemDTO);
        }
        dto.setItens(itensDTO);
        return dto;
    }
}
