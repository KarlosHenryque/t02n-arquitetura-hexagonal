package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.commands.AdicionarEstoqueCommand;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.dto.EntradaEstoqueDTO;

public class EntradaEstoqueDTOMapper {

    public static AdicionarEstoqueCommand toCommand(EntradaEstoqueDTO entradaEstoqueDTO) {
        return new AdicionarEstoqueCommand(
                entradaEstoqueDTO.getProdutoId(),
                entradaEstoqueDTO.getQuantidadeAdicionada()
        );
    }
}
