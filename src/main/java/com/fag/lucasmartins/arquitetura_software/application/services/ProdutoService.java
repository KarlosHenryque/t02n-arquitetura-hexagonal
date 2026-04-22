package com.fag.lucasmartins.arquitetura_software.application.services;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.ProdutoServicePort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public ProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public ProdutoBO salvar(ProdutoBO produtoBO) {
        produtoBO.validarPrecoProdutoPremium();
        produtoBO.calcularPrecoFinalPorEstoqueBaixo();

        return produtoRepositoryPort.salvar(produtoBO);
    }
}
