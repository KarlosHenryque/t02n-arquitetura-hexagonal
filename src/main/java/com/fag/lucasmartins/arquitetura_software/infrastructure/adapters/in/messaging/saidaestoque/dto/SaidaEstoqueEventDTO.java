package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.dto;

public class SaidaEstoqueEventDTO {

    private Integer produtoId;

    private Integer quantidadeAdicionada;

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidadeAdicionada() {
        return quantidadeAdicionada;
    }

    public void setQuantidadeAdicionada(Integer quantidadeAdicionada) {
        this.quantidadeAdicionada = quantidadeAdicionada;
    }
}
