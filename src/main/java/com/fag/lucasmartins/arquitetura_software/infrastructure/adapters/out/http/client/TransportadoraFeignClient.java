package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.client;

import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.dto.DespachoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.dto.DespachoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "api-transportadora",
        url = "https://7ejzesj5n0.execute-api.us-east-1.amazonaws.com"
)
public interface TransportadoraFeignClient {

    @PostMapping(value = "/despachos", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DespachoResponseDTO> despachar(@RequestBody DespachoRequestDTO request);
}
