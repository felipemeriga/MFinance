package com.meriga.mfinance.controller;

import com.meriga.mfinance.dto.AverageExpensesDto;
import com.meriga.mfinance.dto.ExpenseStatisticsDto;
import com.meriga.mfinance.dto.PlanningPercentagesDto;
import com.meriga.mfinance.repository.FinanceStatisticsRepository;
import com.meriga.mfinance.service.FinanceStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class FinanceStatisticsController {

    private final Logger log = LoggerFactory.getLogger(FinanceStatisticsController.class);

    @Autowired
    private FinanceStatisticsService financeStatisticsService;


    @GetMapping(value = "expenses")
    public ResponseEntity<List<ExpenseStatisticsDto>> getExpensesStatistics(@PathParam("date") Date date) {
        return new ResponseEntity<>(financeStatisticsService.getExpensesStatistics(date), HttpStatus.OK);
    }

    @GetMapping(value = "last-months/{number}")
    public ResponseEntity<List<AverageExpensesDto>> getAverageExpensesOverPlanningsOnLastMonths(@PathVariable("number") int id) {
        return new ResponseEntity<>(financeStatisticsService.getAverageExpensesOverPlanningsOnLastMonths(id), HttpStatus.OK);
    }

    @GetMapping(value = "percentages")
    public ResponseEntity<List<PlanningPercentagesDto>> getPlanningSpentPercentagesForCurrentMonth() {
        return new ResponseEntity<>(financeStatisticsService.getPlanningSpentPercentagesForCurrentMonth(), HttpStatus.OK);
    }

}
