package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PedidoBO {

    private UUID id;

    private PessoaBO pessoa;

    private String cep;

    private List<PedidoProdutoBO> itens = new ArrayList<>();

    private double valorTotal;

    public void validarCamposObrigatorios() {
        if (this.pessoa == null) {
            throw new DomainException("Pessoa é obrigatória no pedido");
        }
        if (this.cep == null || this.cep.trim().isEmpty()) {
            throw new DomainException("CEP de entrega é obrigatório");
        }
        if (this.itens == null || this.itens.isEmpty()) {
            throw new DomainException("Pedido deve possuir ao menos um item");
        }
    }

    public void normalizarCep() {
        if (this.cep != null) {
            this.cep = this.cep.replaceAll("[^0-9]", "");
        }
    }

    public void validarCep() {
        if (this.cep == null || this.cep.length() != 8) {
            throw new DomainException("CEP deve conter 8 dígitos");
        }
    }

    public void calcularValorTotal() {
        double total = 0.0;
        for (PedidoProdutoBO item : itens) {
            total += item.getSubtotal();
        }
        this.valorTotal = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PessoaBO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaBO pessoa) {
        this.pessoa = pessoa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<PedidoProdutoBO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoProdutoBO> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
