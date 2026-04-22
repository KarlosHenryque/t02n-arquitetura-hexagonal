package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.handlers;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.ErrorDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.exceptions.RepositorioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorDTO> handleDomainException(DomainException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO body = new ErrorDTO(ex.getMessage(), LocalDateTime.now(), status.value());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(RepositorioException.class)
    public ResponseEntity<ErrorDTO> handleRepositorioException(RepositorioException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO body = new ErrorDTO(ex.getMessage(), LocalDateTime.now(), status.value());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDTO body = new ErrorDTO("Erro interno no servidor: " + ex.getMessage(), LocalDateTime.now(), status.value());
        return ResponseEntity.status(status).body(body);
    }

}
