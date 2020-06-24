package com.meriga.mfinance.repository;

import com.meriga.mfinance.dto.AverageExpenses;
import com.meriga.mfinance.dto.ExpenseStatisticsDto;

import java.sql.Date;
import java.util.List;

public interface FinanceStatisticsRepositoryCustom {

    List<ExpenseStatisticsDto> getExpenseStatistics(Date date);

    List<AverageExpenses> getAverageExpensesOverPlanningsOnLastMonths(int numberOfNumbers);
}
