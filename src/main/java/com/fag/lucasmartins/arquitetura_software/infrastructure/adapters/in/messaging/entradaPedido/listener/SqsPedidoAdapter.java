package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.dto.SqsOrderEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.mapper.SqsOrderEventMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsPedidoAdapter {

    private final PedidoServicePort pedidoServicePort;
    private final SqsOrderEventMapper mapper;

    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort,
                            SqsOrderEventMapper mapper) {
        this.pedidoServicePort = pedidoServicePort;
        this.mapper = mapper;
    }

    @SqsListener("${queue.order-events}")
    public void listen(SqsOrderEventDTO dto) {

        System.out.println("Mensagem recebida: " + dto.getCustomerId());

        PedidoBO criado = process(dto);

        System.out.println("Pedido criado com sucesso: " + criado.getId());
    }

    private PedidoBO process(SqsOrderEventDTO dto) {

        PedidoBO pedidoBO = mapper.toBO(dto);

        return pedidoServicePort.criarPedido(pedidoBO);
    }
}