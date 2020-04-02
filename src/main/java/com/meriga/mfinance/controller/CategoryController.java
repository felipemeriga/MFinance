package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.domain.QCategory;
import com.meriga.mfinance.service.CategoryService;
import io.github.jhipster.web.util.PaginationUtil;
import io.searchbox.core.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * {@code GET } : get all categories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all categories.
     */
    @GetMapping
    public ResponseEntity<Page<Category>> list(Pageable pageable) {
        final Page<Category> page = categoryService.getAllCategories(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }


    @PostMapping
    public Category create(@Valid @RequestBody Category category) {
        QCategory qCategory = QCategory.category;

        Predicate predicate = qCategory.name.eq(category.getName());
        Optional<Category> categoryOptional = categoryService.get(predicate);
        if (categoryOptional.isPresent())
            throw new EntityExistsException("The category with the current name, " + category.getName() + " already" +
                "exists");
        return categoryService.save(category);
    }


}
