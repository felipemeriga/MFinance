package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.domain.QCategory;
import com.meriga.mfinance.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends AbstractController<Category, Long, CategoryService>{


    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    protected CategoryController(CategoryService service) {
        super(service);
    }

    /**+
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * @param category
     */
    @PostMapping
    @Override
    public ResponseEntity<Category> save(@Valid @RequestBody Category category) {
        QCategory qCategory = QCategory.category;
        Predicate predicate = qCategory.name.eq(category.getName());
        Optional<Category> categoryOptional = categoryService.getByPredicate(predicate);
        if (categoryOptional.isPresent())
            throw new EntityExistsException("The category with the current name, " + category.getName() + " already" +
                "exists");
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }
}
