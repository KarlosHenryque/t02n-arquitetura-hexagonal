package com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;

public interface PedidoRepositoryPort {

    PedidoBO salvar(PedidoBO pedidoBO);
}
