package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.http.dto;

public class DespachoRequestDTO {
    private String idProduto;
    private String nomeProduto;
    private int quantidade;

    public DespachoRequestDTO() {}

    public DespachoRequestDTO(String idProduto, String nomeProduto, int quantidade) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
