package com.meriga.mfinance.service;


import com.meriga.mfinance.domain.CashFlow;
import com.meriga.mfinance.repository.CashFlowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing flux.
 */
@Service
@Transactional
public class CashFlowService extends AbstractService<CashFlow, Long, CashFlowRepository>{

    private final Logger log = LoggerFactory.getLogger(CashFlowService.class);

    @Autowired
    private CashFlowRepository cashFlowRepository;

    public CashFlowService(CashFlowRepository repository) {
        super(repository);
    }
}
