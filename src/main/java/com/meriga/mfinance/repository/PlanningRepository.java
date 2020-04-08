package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.Planning;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring Data JPA repository for the {@link Planning} entity.
 */
public interface PlanningRepository extends CommonRepository<Planning, Long> {
}

