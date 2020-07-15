package com.meriga.mfinance.service;


import com.meriga.mfinance.domain.Planning;
import com.meriga.mfinance.repository.PlanningRepository;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Service class for managing plannings.
 */
@Service
@Transactional
public class PlanningService extends AbstractService<Planning, Long, PlanningRepository> {

    private final Logger log = LoggerFactory.getLogger(PlanningService.class);

    @PersistenceContext
    EntityManager em;

    @Autowired
    private PlanningRepository planningRepository;

    // The plan is to pass to abstract class, and user predicate , already with the user session
    // through super
    public PlanningService(PlanningRepository repository) {
        super(repository);
    }

    public Iterable<Planning> test(Predicate predicate) {
        return planningRepository.findAll(predicate);
    }

}
