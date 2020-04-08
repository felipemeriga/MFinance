package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Flux;
import com.meriga.mfinance.service.FluxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/flux")
public class FluxController extends AbstractController<Flux,Long, FluxService> {


    private final Logger log = LoggerFactory.getLogger(FluxController.class);

    @Autowired
    private FluxService fluxService;

    protected FluxController(FluxService service) {
        super(service);
    }
}
