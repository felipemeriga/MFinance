package com.meriga.mfinance.service;


import com.meriga.mfinance.domain.Planning;
import com.meriga.mfinance.repository.PlanningRepository;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Service class for managing plannings.
 */
@Service
@Transactional
public class PlanningService extends AbstractService<Planning, Long, PlanningRepository> {

    private final Logger log = LoggerFactory.getLogger(PlanningService.class);

    @Autowired
    private PlanningRepository planningRepository;

    public PlanningService(PlanningRepository repository) {
        super(repository);
    }

}
