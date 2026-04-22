package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.repository;

import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.ProdutoEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.exceptions.RepositorioException;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa.ProdutoJpaRepository;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper.ProdutoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPort {

    private final ProdutoJpaRepository produtoJpaRepository;

    public ProdutoRepositoryAdapter(ProdutoJpaRepository produtoJpaRepository) {
        this.produtoJpaRepository = produtoJpaRepository;
    }

    @Override
    public ProdutoBO salvar(ProdutoBO produtoBO) {
        ProdutoEntity produtoEntity = ProdutoMapper.toEntity(produtoBO);

        produtoJpaRepository.save(produtoEntity);

        return ProdutoMapper.toBO(produtoEntity);
    }

    @Override
    public List<ProdutoBO> salvarTodos(List<ProdutoBO> produtosBO) {
        final List<ProdutoEntity> entities = produtosBO.stream()
                .map(ProdutoMapper::toEntity)
                .collect(Collectors.toList());
        final List<ProdutoEntity> produtoEntities = produtoJpaRepository.saveAll(entities);

        return produtoEntities.stream()
                .map(ProdutoMapper::toBO)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoBO encontrarPorId(Integer id) {
        final Optional<ProdutoEntity> produtoEntity = produtoJpaRepository.findById(id);
        if (produtoEntity.isEmpty()) {
            throw new RepositorioException("Produto não encontrado para o ID: " + id);
        }
        return ProdutoMapper.toBO(produtoEntity.get());
    }

    @Override
    public List<ProdutoBO> encontrarPorIds(List<Integer> listaIdProduto) {
        final List<ProdutoEntity> produtoEntities = produtoJpaRepository.findByIdIn(listaIdProduto);

        return produtoEntities.stream()
                .map(ProdutoMapper::toBO)
                .collect(Collectors.toList());
    }

}
