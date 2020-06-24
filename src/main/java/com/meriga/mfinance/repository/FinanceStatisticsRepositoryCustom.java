package com.meriga.mfinance.repository;

import com.meriga.mfinance.dto.AverageExpensesDto;
import com.meriga.mfinance.dto.ExpenseStatisticsDto;
import com.meriga.mfinance.dto.PlanningPercentagesDto;

import java.sql.Date;
import java.util.List;

public interface FinanceStatisticsRepositoryCustom {

    List<ExpenseStatisticsDto> getExpenseStatistics(Date date);

    List<AverageExpensesDto> getAverageExpensesOverPlanningsOnLastMonths(int numberOfNumbers);

    List<PlanningPercentagesDto> getPlanningSpentPercentagesForCurrentMonth();
}
