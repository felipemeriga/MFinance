package com.meriga.mfinance.service;

import com.meriga.mfinance.domain.AbstractEntity;
import com.meriga.mfinance.repository.CommonRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, T, R extends CommonRepository<E, T>>
    implements CommonService<E,T> {
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public Page<E> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<E> getAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Optional<E> get(T id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> getByPredicate(Predicate predicate) {
        return repository.findOne(predicate);
    }

    @Override
    public void delete(E e) {
        repository.delete(e);
    }

}
