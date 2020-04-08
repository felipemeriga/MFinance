package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Planning;
import com.meriga.mfinance.domain.QPlanning;
import com.meriga.mfinance.service.PlanningService;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

@RestController
@RequestMapping("/api/plannings")
public class PlanningController extends AbstractController<Planning, Long, PlanningService> {


    private final Logger log = LoggerFactory.getLogger(PlanningController.class);

    @Autowired
    private PlanningService planningService;

    protected PlanningController(PlanningService service) {
        super(service);
    }

    /**+
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * @param planning
     */
    @PostMapping
    @Override
    public ResponseEntity<Planning> save(@Valid @RequestBody Planning planning) {
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
}
