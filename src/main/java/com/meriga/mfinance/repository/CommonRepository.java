package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.AbstractEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity, T> extends PagingAndSortingRepository<E, T>, QuerydslPredicateExecutor<E> {
}
