package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http;

import com.fag.lucasmartins.arquitetura_software.application.ports.out.http.TransportadoraNotificacaoPort;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.client.TransportadoraFeignClient;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.dto.DespachoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.dto.DespachoResponseDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.exceptions.TransportadoraClientException;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransportadoraNotificacaoAdapter implements TransportadoraNotificacaoPort {

    private final TransportadoraFeignClient transportadoraClient;

    public TransportadoraNotificacaoAdapter(TransportadoraFeignClient transportadoraClient) {
        this.transportadoraClient = transportadoraClient;
    }

    @Override
    public DespachoResponseDTO notificarItem(Integer produtoId, String nomeProduto, int quantidade) {
        try {
            final DespachoRequestDTO request = new DespachoRequestDTO(String.valueOf(produtoId), nomeProduto, quantidade);

            final ResponseEntity<DespachoResponseDTO> response = transportadoraClient.despachar(request);

            return response.getBody();
        } catch (FeignException e) {
            throw new TransportadoraClientException("Ocorreu um erro ao notificar a transportadora: " + e.getMessage());
        }
    }
}
