package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.commands.DiminuirEstoqueCommand;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.dto.EntradaEstoqueDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.dto.SaidaEstoqueEventDTO;

public class SaidaEstoqueDTOMapper {

    public static DiminuirEstoqueCommand toCommand(SaidaEstoqueEventDTO saidaEstoqueEventDTO) {
        return new DiminuirEstoqueCommand(
                saidaEstoqueEventDTO.getProdutoId(),
                saidaEstoqueEventDTO.getQuantidadeAdicionada()
        );
    }
}
