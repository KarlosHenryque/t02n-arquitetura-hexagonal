package com.fag.lucasmartins.arquitetura_software.application.ports.out.http;

import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.dto.DespachoResponseDTO;

public interface TransportadoraNotificacaoPort {

    DespachoResponseDTO notificarItem(Integer produtoId, String nomeProduto, int quantidade);
}
