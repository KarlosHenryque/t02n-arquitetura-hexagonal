package com.fag.lucasmartins.arquitetura_software.application.services;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.EstoqueServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.commands.AdicionarEstoqueCommand;
import com.fag.lucasmartins.arquitetura_software.core.domain.commands.DiminuirEstoqueCommand;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService implements EstoqueServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public EstoqueService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public void adicinarEstoque(AdicionarEstoqueCommand command) {
        final ProdutoBO produtoBO = produtoRepositoryPort.encontrarPorId(command.getProdutoId());

        produtoBO.adicionarEstoque(command.getQuantidade());

        produtoRepositoryPort.salvar(produtoBO);
    }

    @Override
    public void diminuirEstoque(DiminuirEstoqueCommand command) {
        final ProdutoBO produtoBO = produtoRepositoryPort.encontrarPorId(command.getProdutoId());

        produtoBO.diminuirEstoque(command.getQuantidade());

        produtoRepositoryPort.salvar(produtoBO);
    }
}
