package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Planning;
import com.meriga.mfinance.domain.QPlanning;
import com.meriga.mfinance.exception.PlanningAlreadyExistsWithinMonth;
import com.meriga.mfinance.service.PlanningService;
import com.meriga.mfinance.utils.CurrentSession;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

@RestController
@RequestMapping("/api/plannings")
public class PlanningController extends AbstractController<Planning, Long, PlanningService> {


    private final Logger log = LoggerFactory.getLogger(PlanningController.class);

    @Autowired
    private PlanningService planningService;

    @Autowired
    private CurrentSession currentSession;

    protected PlanningController(PlanningService service) {
        super(service);
    }

    private Predicate getSessionPredicate() {
        QPlanning qPlanning = QPlanning.planning;
        return qPlanning.user.id.eq(currentSession.getCurrentUser());
    }

    @Override
    public ResponseEntity<Planning> save(@Valid @RequestBody Planning entity) {
        entity.setUser(currentSession.getCurrentUserEntity());
        return super.save(entity);
    }


    /**
     * {@code GET } : get all entities with an specific.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body.
     */
    @GetMapping(value = "monthly")
    public ResponseEntity<Page<Planning>> listWithDate(@Nullable @PathParam("date") Date date,
                                                       @Nullable @PathParam("search") String search,
                                                       Pageable pageable) {
        QPlanning qPlanning = QPlanning.planning;
        YearMonth yearMonth = YearMonth.of( date.toLocalDate().getYear(), date.toLocalDate().getMonth());
        LocalDate firstOfMonth = yearMonth.atDay( 1 );
        LocalDate last = yearMonth.atEndOfMonth();

        Predicate predicate = qPlanning.date.between(java.sql.Date.valueOf(firstOfMonth), java.sql.Date.valueOf(last))
            .and((qPlanning.category.name.contains(search == null ? "" : search)))
            .and(getSessionPredicate());

        final Page<Planning> page = service.getAll(predicate, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * {@code GET } : get all entities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body.
     */
    @GetMapping
    @Override
    public ResponseEntity<Page<Planning>> list(@Nullable @PathParam("search") String search, Pageable pageable) {
        if (!ObjectUtils.isEmpty(search)) {
            System.out.println(search);
        }

        final Page<Planning> page = service.getAll(getSessionPredicate(), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "validate/{categoryId}")
    public ResponseEntity<Planning> validateCategoryAlreadyExistsWithinMonth(@PathVariable("categoryId") Long categoryId,
                                                                             @PathParam("date") Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        QPlanning qPlanning = QPlanning.planning;
        YearMonth yearMonth = YearMonth.of(year,month);
        LocalDate firstOfMonth = yearMonth.atDay( 1 );
        LocalDate last = yearMonth.atEndOfMonth();

        Predicate predicate = qPlanning.date.between(java.sql.Date.valueOf(firstOfMonth), java.sql.Date.valueOf(last))
            .and(qPlanning.category.id.eq(categoryId))
            .and(getSessionPredicate());

        Optional<Planning> planningOptional = planningService.getByPredicate(predicate);
        Planning planning = planningOptional.isPresent() ? planningOptional.get() : new Planning();;

        return new ResponseEntity<>(planning, HttpStatus.OK);
    }

    @GetMapping("test/{id}")
    public ResponseEntity<Page<Planning>> test(Pageable pageable, @PathVariable("id") Long id) {
        final Iterable<Planning> page = service.test(getSessionPredicate());

        return null;
    }

    @PutMapping("{id}")
    @Override
    public ResponseEntity<Planning> update(@Valid @RequestBody Planning planning, @PathVariable("id") Long id) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(planning.getDate());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        QPlanning qPlanning = QPlanning.planning;
        YearMonth yearMonth = YearMonth.of(year,month);
        LocalDate firstOfMonth = yearMonth.atDay( 1 );
        LocalDate last = yearMonth.atEndOfMonth();

        Predicate predicate = qPlanning.date.between(java.sql.Date.valueOf(firstOfMonth), java.sql.Date.valueOf(last))
            .and(qPlanning.category.id.eq(planning.getCategory().getId()))
            .and(getSessionPredicate());

        Optional<Planning> planningOptional = planningService.getByPredicate(predicate);
        if(planningOptional.isPresent() && !planningOptional.get().getId().equals(id)) {
            throw new PlanningAlreadyExistsWithinMonth("There is already the selected category within the month");
        }

        planning.setId(id);
        return new ResponseEntity<>(service.save(planning), HttpStatus.OK);
    }
}
