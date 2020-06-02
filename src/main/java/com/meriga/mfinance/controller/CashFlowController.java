package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.CashFlow;
import com.meriga.mfinance.service.CashFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cash-flow")
public class CashFlowController extends AbstractController<CashFlow,Long, CashFlowService> {


    private final Logger log = LoggerFactory.getLogger(CashFlowController.class);

    @Autowired
    private CashFlowService cashFlowService;

    protected CashFlowController(CashFlowService service) {
        super(service);
    }
}
