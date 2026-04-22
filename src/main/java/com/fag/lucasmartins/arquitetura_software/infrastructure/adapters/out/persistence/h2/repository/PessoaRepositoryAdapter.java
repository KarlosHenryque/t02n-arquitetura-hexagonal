package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.repository;

import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.PessoaRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PessoaEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.exceptions.RepositorioException;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa.PessoaJpaRepository;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper.PessoaMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PessoaRepositoryAdapter implements PessoaRepositoryPort {

    private final PessoaJpaRepository pessoaJpaRepository;

    public PessoaRepositoryAdapter(PessoaJpaRepository pessoaJpaRepository) {
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    @Override
    public PessoaBO salvar(PessoaBO pessoaBO) {
        PessoaEntity entity = PessoaMapper.toEntity(pessoaBO);

        pessoaJpaRepository.save(entity);

        return PessoaMapper.toBO(entity);
    }

    @Override
    public PessoaBO encontrarPorId(Integer id) {
        final Optional<PessoaEntity> pessoa = pessoaJpaRepository.findById(id);
        if (pessoa.isEmpty()) {
            throw new RepositorioException("Pessoa não encontrada.");
        }
        return PessoaMapper.toBO(pessoa.get());
    }
}
