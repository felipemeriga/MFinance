package com.meriga.mfinance.service;

import com.meriga.mfinance.domain.AbstractEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommonService<E extends AbstractEntity, T> {

    E save (E entity);

    Page<E> getAll(Pageable pageable);

    Optional<E> get(T id);

    Optional<E> getByPredicate(Predicate predicate);

    void delete(E e);
}
