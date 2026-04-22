package com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;

public interface PessoaRepositoryPort {

    PessoaBO salvar(PessoaBO pessoaBO);

    PessoaBO encontrarPorId(Integer id);
}
