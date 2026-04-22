package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.EstoqueServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.commands.AdicionarEstoqueCommand;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.dto.EntradaEstoqueDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.mapper.EntradaEstoqueDTOMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EntradaEstoqueSQSAdapter {

    private static final Logger log = LoggerFactory.getLogger(EntradaEstoqueSQSAdapter.class);

    private final EstoqueServicePort estoqueServicePort;

    public EntradaEstoqueSQSAdapter(EstoqueServicePort estoqueServicePort) {
        this.estoqueServicePort = estoqueServicePort;
    }

    @SqsListener(value = "${aws.sqs.queue.entrada-estoque}")
    public void receberMensagem(EntradaEstoqueDTO evento) {
        try {
            log.info("Evento de entrada de estoque recebido para o produto {}", evento.getProdutoId());

            final AdicionarEstoqueCommand command = EntradaEstoqueDTOMapper.toCommand(evento);
            estoqueServicePort.adicinarEstoque(command);

            log.info("Entrada de estoque processada para o produto {}", evento.getProdutoId());
        } catch (Exception e) {
            log.error("Erro ao processar o evento de saido do estoque para o produto {}", evento.getProdutoId(), e);
            throw e;
        }
    }
}
