package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.dto.SqsOrderEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.mapper.SqsOrderEventMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SqsPedidoAdapter {

    private final PedidoServicePort pedidoServicePort;
    private static final Logger log = LoggerFactory.getLogger(SqsPedidoAdapter.class);
    private final SqsOrderEventMapper mapper;
    private final ObjectMapper objectMapper;

    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort,
                            SqsOrderEventMapper mapper,
                            ObjectMapper objectMapper) {
        this.pedidoServicePort = pedidoServicePort;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @SqsListener("${queue.order-events}")
    public void listen(SqsOrderEventDTO dto) {
        try {
            log.info("Mensagem recebida: {}", dto.getCustomerId());

            PedidoBO pedidoBO = mapper.toBO(dto);

            var criado = pedidoServicePort.criarPedido(pedidoBO);

            log.info("Pedido criado com sucesso: {}", criado);

        } catch (Exception e) {
            log.error("Erro ao processar mensagem", e);
            throw e;
        }
    }
}