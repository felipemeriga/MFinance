package com.meriga.mfinance.service;


import com.meriga.mfinance.dto.AverageExpensesDto;
import com.meriga.mfinance.dto.ExpenseStatisticsDto;
import com.meriga.mfinance.dto.PlanningPercentagesDto;
import com.meriga.mfinance.repository.FinanceStatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class FinanceStatisticsService {

    private final Logger log = LoggerFactory.getLogger(FinanceStatisticsService.class);


    @Autowired
    private FinanceStatisticsRepository financeStatisticsRepository;

    public List<ExpenseStatisticsDto> getExpensesStatistics(Date date) {
        return this.financeStatisticsRepository.getExpenseStatistics(date);
    }


    public List<AverageExpensesDto> getAverageExpensesOverPlanningsOnLastMonths(int numberOfMonths) {
        return this.financeStatisticsRepository.getAverageExpensesOverPlanningsOnLastMonths(numberOfMonths);
    }

    public List<PlanningPercentagesDto> getPlanningSpentPercentagesForCurrentMonth() {
        return this.financeStatisticsRepository.getPlanningSpentPercentagesForCurrentMonth();
    }

}
