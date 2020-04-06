package com.meriga.mfinance.service;


import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing categories.
 */
@Service
@Transactional
public class CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> get(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getByPredicate(Predicate predicate) {
        return categoryRepository.findOne(predicate);
    }

    public Category save(Category category) { ;
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

}
