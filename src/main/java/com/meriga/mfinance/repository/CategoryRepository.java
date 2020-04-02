package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.Category;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring Data JPA repository for the {@link Category} entity.
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, QuerydslPredicateExecutor<Category> {
}

