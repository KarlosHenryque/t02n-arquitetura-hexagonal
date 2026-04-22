package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;


public class PessoaBO {

    private Integer id;

    private String nomeCompleto;

    private String cpf;

    private LocalDate dataNascimento;

    private String email;

    private String telefone;

    public PessoaBO() {
    }

    public PessoaBO(Integer id, String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
    }

    public static PessoaBO of(String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        return new PessoaBO(null, nomeCompleto, cpf, dataNascimento, email, telefone);
    }

    public void validarCamposObrigatorios() {
        if (isBlank(nomeCompleto)) {
            throw new DomainException("Nome completo é obrigatório");
        }
        if (isBlank(cpf)) {
            throw new DomainException("CPF é obrigatório");
        }
        if (dataNascimento == null) {
            throw new DomainException("Data de nascimento é obrigatória");
        }
        if (isBlank(email)) {
            throw new DomainException("E-mail é obrigatório");
        }
        if (isBlank(telefone)) {
            throw new DomainException("Telefone é obrigatório");
        }
        this.cpf = cpf.replaceAll("[^0-9]", "");
        this.telefone = telefone.replaceAll("[^0-9]", "");
    }

    public void validarEmail() {
        if (!email.contains("@")) {
            throw new DomainException("E-mail inválido");
        }
    }

    public void validarTelefone() {
        if (this.telefone.length() != 11) {
            throw new DomainException("Telefone deve conter 11 dígitos");
        }
    }

    public void validarCpf() {
        if (this.cpf.length() != 11) {
            throw new DomainException("CPF deve conter 11 dígitos");
        }
    }

    public void validarMaioridade() {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 18) {
            throw new DomainException("Idade mínima de 18 anos não atendida");
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
