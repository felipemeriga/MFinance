package com.meriga.mfinance.service;


import com.meriga.mfinance.domain.Flux;
import com.meriga.mfinance.repository.FluxRepository;
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
 * Service class for managing flux.
 */
@Service
@Transactional
public class FluxService {

    private final Logger log = LoggerFactory.getLogger(FluxService.class);

    @Autowired
    private FluxRepository fluxRepository;

    public Page<Flux> getAllCategories(Pageable pageable) {
        return fluxRepository.findAll(pageable);
    }

    public Optional<Flux> get(Long id) {
        return fluxRepository.findById(id);
    }

    public Optional<Flux> getByPredicate(Predicate predicate) {
        return fluxRepository.findOne(predicate);
    }

    public Flux save(Flux flux) { ;
        return fluxRepository.save(flux);
    }

    public void delete(Flux flux) {
        fluxRepository.delete(flux);
    }

}
