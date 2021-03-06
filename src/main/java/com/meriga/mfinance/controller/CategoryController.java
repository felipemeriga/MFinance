package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.domain.QCategory;
import com.meriga.mfinance.service.CategoryService;
import com.meriga.mfinance.utils.CurrentSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends AbstractController<Category, Long, CategoryService>{


    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CurrentSession currentSession;

    private Predicate getSessionPredicate() {
        QCategory qCategory = QCategory.category;
        return qCategory.user.id.eq(currentSession.getCurrentUser());
    }


    protected CategoryController(CategoryService service) {
        super(service);
    }

    /**
     * {@code GET } : get all entities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body.
     */
    @GetMapping
    @Override
    public ResponseEntity<Page<Category>> list(@Nullable @PathParam("search") String search, Pageable pageable) {
        if (!ObjectUtils.isEmpty(search)) {
            QCategory qCategory = QCategory.category;
            Predicate predicate = qCategory.name.containsIgnoreCase(search)
                .and(getSessionPredicate());
            final Page<Category> page = service.getAll(predicate, pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        final Page<Category> page = service.getAll(getSessionPredicate(), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
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
        Predicate predicate = qCategory.name.eq(category.getName())
            .and(getSessionPredicate());
        Optional<Category> categoryOptional = categoryService.getByPredicate(predicate);
        category.setUser(currentSession.getCurrentUserEntity());
        if (categoryOptional.isPresent())
            throw new EntityExistsException("The category with the current name, " + category.getName() + " already" +
                "exists");
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }
}
