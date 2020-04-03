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
 * Service class for managing users.
 */
@Service
@Transactional
public class PlanningService {

    private final Logger log = LoggerFactory.getLogger(PlanningService.class);

    @Autowired
    private PlanningRepository planningRepository;

    public Page<Planning> getAllCategories(Pageable pageable) {
        return planningRepository.findAll(pageable);
    }

    public Optional<Planning> get(Long id) {
        return planningRepository.findById(id);
    }

    public Optional<Planning> getByPredicate(Predicate predicate) {
        return planningRepository.findOne(predicate);
    }

    public Planning save(Planning planning) { ;
        return planningRepository.save(planning);
    }

    public void delete(Planning planning) {
        planningRepository.delete(planning);
    }

}
