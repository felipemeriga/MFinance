package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.Flux;
import com.meriga.mfinance.exception.ConstraintViolationException;
import com.meriga.mfinance.service.FluxService;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@RestController
@RequestMapping("/api/flux")
public class FluxController {


    private final Logger log = LoggerFactory.getLogger(FluxController.class);

    @Autowired
    private FluxService fluxService;

    /**
     * {@code GET } : get all fluxes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all fluxes.
     */
    @GetMapping
    public ResponseEntity<Page<Flux>> list(Pageable pageable) {
        final Page<Flux> page = fluxService.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**+
     *
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @GetMapping("{id}")
    public ResponseEntity<Flux> get(@PathVariable("id") Long id) {
        Flux flux = fluxService.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Flux not found"));
        return new ResponseEntity<>(flux, HttpStatus.OK);
    }

    /**+
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * @param flux
     */
    @PostMapping
    public ResponseEntity<Flux> create(@Valid @RequestBody Flux flux) {
        return new ResponseEntity<>(fluxService.save(flux), HttpStatus.OK);
    }

    /***
     *
     * @param flux
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @PutMapping("{id}")
    public ResponseEntity<Flux> update(@Valid @RequestBody Flux flux, @PathVariable("id") Long id) {
        Optional<Flux> optionalFlux = fluxService.get(id);
        if (!optionalFlux.isPresent()) {
            throw new EntityNotFoundException("The flux with the current id, " + flux.getId() + " doesn't" +
                "exists");
        }
        flux.setId(id);
        return new ResponseEntity<>(fluxService.save(flux), HttpStatus.OK);
    }

    /***
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        Flux flux = fluxService.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Flux not found"));
        try {
            fluxService.delete(flux);
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException | e instanceof DataIntegrityViolationException) {
                    throw new ConstraintViolationException("Violating database constraints for this flux");
            } else {
                log.info("Exception caught during operator deletion: {}", e.getMessage());
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }
    }


}
