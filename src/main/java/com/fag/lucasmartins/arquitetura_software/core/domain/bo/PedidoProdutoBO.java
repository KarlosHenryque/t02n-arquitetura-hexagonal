package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

public class PedidoProdutoBO {

    private Integer id;

    private int quantidade;

    private double subtotal;

    private ProdutoBO produto;

    public void validar() {
        if (produto == null) {
            throw new DomainException("Produto é obrigatório no item do pedido");
        }
        if (quantidade <= 0) {
            throw new DomainException("Quantidade do item deve ser pelo menos 1");
        }
    }

    public void calcularSubtotal() {
        this.subtotal = this.produto.getPreco() * this.quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public ProdutoBO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoBO produto) {
        this.produto = produto;
    }
}
