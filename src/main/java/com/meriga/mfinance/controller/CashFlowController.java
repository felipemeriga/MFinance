package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.CashFlow;
import com.meriga.mfinance.domain.QCashFlow;
import com.meriga.mfinance.domain.QPlanning;
import com.meriga.mfinance.service.CashFlowService;
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
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;


@RestController
@RequestMapping("/api/cash-flow")
public class CashFlowController extends AbstractController<CashFlow,Long, CashFlowService> {


    private final Logger log = LoggerFactory.getLogger(CashFlowController.class);

    @Autowired
    private CashFlowService cashFlowService;

    @Autowired
    private CurrentSession currentSession;

    private Predicate getSessionPredicate() {
        QCashFlow qCashFlow = QCashFlow.cashFlow;
        return qCashFlow.user.id.eq(currentSession.getCurrentUser());
    }

    protected CashFlowController(CashFlowService service) {
        super(service);
    }

    @Override
    public ResponseEntity<CashFlow> save(CashFlow entity) {
        entity.setUser(currentSession.getCurrentUserEntity());
        return super.save(entity);
    }

    @Override
    public ResponseEntity<Page<CashFlow>> list(@Nullable String search, Pageable pageable) {
        if (!ObjectUtils.isEmpty(search)) {
            QCashFlow qCashFlow = QCashFlow.cashFlow;
            Predicate predicate = qCashFlow.name.containsIgnoreCase(search)
                .and(getSessionPredicate())
                .and(getSessionPredicate());
            final Page<CashFlow> page = service.getAll(predicate, pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        final Page<CashFlow> page = service.getAll(getSessionPredicate(), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * {@code GET } : get all entities with an specific.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body.
     */
    @GetMapping(value = "monthly")
    public ResponseEntity<Page<CashFlow>> listWithDate(@Nullable @PathParam("date") Date date,
                                                       @Nullable @PathParam("search") String search,
                                                       Pageable pageable) {
        QCashFlow qCashFlow = QCashFlow.cashFlow;
        YearMonth yearMonth = YearMonth.of( date.toLocalDate().getYear(), date.toLocalDate().getMonth());
        LocalDate firstOfMonth = yearMonth.atDay( 1 );
        LocalDate last = yearMonth.atEndOfMonth();

        Predicate predicate = qCashFlow.date.between(java.sql.Date.valueOf(firstOfMonth), java.sql.Date.valueOf(last))
            .and((qCashFlow.category.name.contains(search == null ? "" : search)))
            .and(getSessionPredicate());

        final Page<CashFlow> page = service.getAll(predicate, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
