package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.Planning;
import com.querydsl.core.types.Predicate;


/**
 * Spring Data JPA repository for the {@link Planning} entity.
 */
public interface PlanningRepository extends CommonRepository<Planning, Long> {


    Iterable<Planning> findAll(Predicate predicate);
}

