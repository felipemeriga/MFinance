package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.Category;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data JPA repository for the {@link Category} entity.
 */
public interface CategoryRepository extends CrudRepository<Category, Long>, QuerydslPredicateExecutor<Category> {
}

