package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.domain.Planning;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.QueryHint;
import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link Planning} entity.
 */
public interface PlanningRepository extends CommonRepository<Planning, Long> {


    Iterable<Planning> findAll(Predicate predicate);
}

