package com.fag.lucasmartins.arquitetura_software.application.services;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.PedidoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.PessoaRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PedidoService implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    private final PessoaRepositoryPort pessoaRepositoryPort;

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public PedidoService(PedidoRepositoryPort pedidoRepositoryPort, PessoaRepositoryPort pessoaRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.pessoaRepositoryPort = pessoaRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    @Transactional
    public PedidoBO criarPedido(PedidoBO pedidoBO) {
        pedidoBO.validarCamposObrigatorios();

        final PessoaBO pessoaBO = pessoaRepositoryPort.encontrarPorId(pedidoBO.getPessoa().getId());
        pedidoBO.setPessoa(pessoaBO);
        verificarProdutos(pedidoBO);

        pedidoBO.validarCep();
        pedidoBO.normalizarCep();
        pedidoBO.calcularValorTotal();

        return pedidoRepositoryPort.salvar(pedidoBO);
    }

    private void verificarProdutos(PedidoBO pedidoBO) {
        final List<Integer> listaIdsProduto = pedidoBO.getItens().stream()
                .map(PedidoProdutoBO::getProduto)
                .map(ProdutoBO::getId)
                .collect(Collectors.toList());

        final List<ProdutoBO> produtosEncontrados = produtoRepositoryPort.encontrarPorIds(listaIdsProduto);
        final Map<Integer, ProdutoBO> mapaDeProdutos = produtosEncontrados.stream()
                .collect(Collectors.toMap(ProdutoBO::getId, produto -> produto));
        pedidoBO.getItens()
                .forEach(pedidoProdutoBO -> prepararProdutos(pedidoProdutoBO, mapaDeProdutos));

        produtoRepositoryPort.salvarTodos(produtosEncontrados);
    }

    private static void prepararProdutos(PedidoProdutoBO pedidoProdutoBO, Map<Integer, ProdutoBO> mapaDeProdutos) {
        pedidoProdutoBO.validar();
        final Integer produtoId = pedidoProdutoBO.getProduto().getId();
        final ProdutoBO produtoBO = mapaDeProdutos.get(produtoId);
        if (produtoBO == null) {
            throw new DomainException("Produto com ID " + produtoId + " não foi encontrado no catálogo.");
        }
        produtoBO.validarEstoqueDisponivel(pedidoProdutoBO.getQuantidade());
        produtoBO.diminuirEstoque(pedidoProdutoBO.getQuantidade());

        pedidoProdutoBO.setProduto(produtoBO);
        pedidoProdutoBO.calcularSubtotal();
    }

}
