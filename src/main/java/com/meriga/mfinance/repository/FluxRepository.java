package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.Flux;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring Data JPA repository for the {@link Flux} entity.
 */
public interface FluxRepository extends PagingAndSortingRepository<Flux, Long>, QuerydslPredicateExecutor<Flux> {
}

