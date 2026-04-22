package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto;

import java.util.List;
import java.util.UUID;

public class PedidoDTO {

    private UUID id;

    private PessoaDTO pessoa;

    private String cep;

    private List<PedidoProdutoDTO> itens;

    private Double valorTotal;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<PedidoProdutoDTO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoProdutoDTO> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
