package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Category;
import com.meriga.mfinance.domain.Planning;
import com.meriga.mfinance.domain.QCategory;
import com.meriga.mfinance.domain.QPlanning;
import com.meriga.mfinance.exception.ConstraintViolationException;
import com.meriga.mfinance.service.PlanningService;
import com.querydsl.core.types.Predicate;
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
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.GregorianCalendar;
import java.util.Optional;

@RestController
@RequestMapping("/api/plannings")
public class PlanningController {


    private final Logger log = LoggerFactory.getLogger(PlanningController.class);

    @Autowired
    private PlanningService planningService;

    /**
     * {@code GET } : get all plannings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all plannings.
     */
    @GetMapping
    public ResponseEntity<Page<Planning>> list(Pageable pageable) {
        final Page<Planning> page = planningService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**+
     *
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @GetMapping("{id}")
    public ResponseEntity<Planning> get(@PathVariable("id") Long id) {
        Planning planning = planningService.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Planning not found"));
        return new ResponseEntity<>(planning, HttpStatus.OK);
    }

    /**+
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * @param planning
     */
    @PostMapping
    public ResponseEntity<Planning> create(@Valid @RequestBody Planning planning) {
        QPlanning qPlanning = QPlanning.planning;

        YearMonth yearMonth = YearMonth.of( planning.getDate().toLocalDate().getYear(), planning.getDate().toLocalDate().getMonth());
        LocalDate firstOfMonth = yearMonth.atDay( 1 );
        LocalDate last = yearMonth.atEndOfMonth();

        Predicate predicate = qPlanning.date.between(java.sql.Date.valueOf(firstOfMonth), java.sql.Date.valueOf(last))
            .and(qPlanning.category.id.eq(planning.getCategory().getId()));

        Optional<Planning> planningOptional = planningService.getByPredicate(predicate);
        if (planningOptional.isPresent())
            throw new EntityExistsException("There is already a planning of that category within the informed month" +
                "please create in another month or update that");

        return new ResponseEntity<>(planningService.save(planning), HttpStatus.OK);
    }

    /***
     *
     * @param planning
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @PutMapping("{id}")
    public ResponseEntity<Planning> update(@Valid @RequestBody Planning planning, @PathVariable("id") Long id) {
        Optional<Planning> optionalPlanning = planningService.get(id);
        if (!optionalPlanning.isPresent()) {
            throw new EntityNotFoundException("The planning with the current id, " + planning.getId() + " doesn't" +
                "exists");
        }
        planning.setId(id);
        return new ResponseEntity<>(planningService.save(planning), HttpStatus.OK);
    }

    /***
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        Planning planning = planningService.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Planning not found"));
        try {
            planningService.delete(planning);
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException | e instanceof DataIntegrityViolationException) {
                    throw new ConstraintViolationException("Violating database constraints for this planning");
            } else {
                log.info("Exception caught during operator deletion: {}", e.getMessage());
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }
    }


}
