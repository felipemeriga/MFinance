package com.meriga.mfinance.service;


import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Service class for managing categories.
 */
@Service
@Transactional
public class CategoryService extends AbstractService<Category, Long, CategoryRepository> {

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
