package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.exceptions;

public class RepositorioException extends RuntimeException {

    public RepositorioException(String message) {
        super(message);
    }
}
