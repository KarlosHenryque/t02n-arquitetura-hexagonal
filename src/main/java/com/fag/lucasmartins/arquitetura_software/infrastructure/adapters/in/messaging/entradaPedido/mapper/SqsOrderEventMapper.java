package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.*;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.dto.SqsOrderEventDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SqsOrderEventMapper {

    public PedidoBO toBO(SqsOrderEventDTO dto) {

        PedidoBO pedidoBO = new PedidoBO();
        pedidoBO.setCep(dto.getZipCode());

        PessoaBO pessoaBO = new PessoaBO();
        pessoaBO.setId(dto.getCustomerId());
        pedidoBO.setPessoa(pessoaBO);

        List<PedidoProdutoBO> itens =
                dto.getOrderItems() == null
                        ? List.of()
                        : dto.getOrderItems()
                          .stream()
                          .map(this::toItemBO)
                          .collect(Collectors.toList());

        pedidoBO.setItens(itens);

        return pedidoBO;
    }

    private PedidoProdutoBO toItemBO(SqsOrderEventDTO.SqsOrderItemDTO itemDTO) {

        PedidoProdutoBO itemBO = new PedidoProdutoBO();

        ProdutoBO produtoBO = new ProdutoBO();
        produtoBO.setId(itemDTO.getSku());

        itemBO.setProduto(produtoBO);
        itemBO.setQuantidade(itemDTO.getAmount());

        return itemBO;
    }
}