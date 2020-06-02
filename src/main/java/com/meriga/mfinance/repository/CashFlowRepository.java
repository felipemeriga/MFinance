package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.CashFlow;
import com.meriga.mfinance.domain.CashFlow;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring Data JPA repository for the {@link CashFlow} entity.
 */
public interface CashFlowRepository extends CommonRepository<CashFlow, Long> {
}

