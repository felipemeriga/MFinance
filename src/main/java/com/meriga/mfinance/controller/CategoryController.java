package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.domain.QCategory;
import com.meriga.mfinance.exception.ConstraintViolationException;
import com.meriga.mfinance.service.CategoryService;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;


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

        final Page<Category> page = categoryService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**+
     *
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @GetMapping("{id}")
    public ResponseEntity<Category> get(@PathVariable("id") Long id) {
        Category category = categoryService.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**+
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * @param category
     */
    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
        QCategory qCategory = QCategory.category;

        Predicate predicate = qCategory.name.eq(category.getName());
        Optional<Category> categoryOptional = categoryService.getByPredicate(predicate);
        if (categoryOptional.isPresent())
            throw new EntityExistsException("The category with the current name, " + category.getName() + " already" +
                "exists");
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    /***
     *
     * @param category
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @PutMapping("{id}")
    public ResponseEntity<Category> update(@Valid @RequestBody Category category, @PathVariable("id") Long id) {
        Optional<Category> optionalCategory = categoryService.get(id);
        if (!optionalCategory.isPresent()) {
            throw new EntityNotFoundException("The category with the current id, " + category.getId() + " doesn't" +
                "exists");
        }
        category.setId(id);
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    /***
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        Category category = categoryService.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        try {
            categoryService.delete(category);
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException | e instanceof DataIntegrityViolationException) {
                    throw new ConstraintViolationException("Violating database constraints for this category");
            } else {
                log.info("Exception caught during operator deletion: {}", e.getMessage());
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }
    }


}
