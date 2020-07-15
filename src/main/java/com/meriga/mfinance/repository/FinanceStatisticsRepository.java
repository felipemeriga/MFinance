package com.meriga.mfinance.repository;

import com.meriga.mfinance.domain.CashFlow;
import org.springframework.data.repository.CrudRepository;

public interface FinanceStatisticsRepository extends FinanceStatisticsRepositoryCustom, CrudRepository<CashFlow, Long> {
}
