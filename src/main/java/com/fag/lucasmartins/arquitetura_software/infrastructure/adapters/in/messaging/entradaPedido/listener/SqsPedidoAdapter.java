package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    @SqsListener(value = "${queue.order-events}")
    public void listen(String mensagem) {

        System.out.println("Mensagem recebida da fila:");
        System.out.println(mensagem);

        try {
            ObjectMapper mapperJson = new ObjectMapper();
            mapperJson.registerModule(new JavaTimeModule());

            SqsOrderEventDTO dto =
                    mapperJson.readValue(mensagem, SqsOrderEventDTO.class);

            PedidoBO pedidoBO = mapper.toBO(dto);

            PedidoBO criado = pedidoServicePort.criarPedido(pedidoBO);

            System.out.println("Pedido criado com sucesso. ID: " + criado.getId());

        } catch (Exception e) {
            System.out.println("Erro ao processar mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }
}