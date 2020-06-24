package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.CashFlow;
import com.meriga.mfinance.dto.ExpenseStatisticsDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface FinanceStatisticsRepository extends FinanceStatisticsRepositoryCustom, CrudRepository<CashFlow, Long> {
}
