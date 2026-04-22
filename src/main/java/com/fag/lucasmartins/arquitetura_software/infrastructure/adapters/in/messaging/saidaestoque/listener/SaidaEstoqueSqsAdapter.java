package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.EstoqueServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.commands.DiminuirEstoqueCommand;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.dto.SaidaEstoqueEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.mapper.SaidaEstoqueDTOMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SaidaEstoqueSqsAdapter {

    private static final Logger log = LoggerFactory.getLogger(SaidaEstoqueSqsAdapter.class);

    private final EstoqueServicePort estoqueServicePort;

    public SaidaEstoqueSqsAdapter(EstoqueServicePort estoqueServicePort) {
        this.estoqueServicePort = estoqueServicePort;
    }

    @SqsListener(value = "${aws.sqs.queue.saida-estoque}")
    public void receberMensagem(SaidaEstoqueEventDTO evento) {
        try {
            log.info("Evento de saida de estoque recebido para o produto {}", evento.getProdutoId());

            final DiminuirEstoqueCommand command = SaidaEstoqueDTOMapper.toCommand(evento);
            estoqueServicePort.diminuirEstoque(command);

            log.info("Saida de estoque processada para o produto {}", evento.getProdutoId());
        } catch (Exception e) {
            log.error("Erro ao processar o evento de saido do estoque para o produto {}", evento.getProdutoId(), e);
            throw e;
        }
    }
}
