package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.AbstractEntity;
import com.meriga.mfinance.exception.ConstraintViolationException;
import com.meriga.mfinance.service.CommonService;
import io.github.jhipster.web.util.PaginationUtil;
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
import java.util.List;
import java.util.Optional;

public abstract class AbstractController<E extends AbstractEntity, T, S extends CommonService<E,T>> implements CommonController<E, T> {


    protected final S service;


    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> save(@RequestBody E entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.OK);
    }

    /**
     * {@code GET } : get all entities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body.
     */
    @Override
    public ResponseEntity<Page<E>> list(Pageable pageable) {
        final Page<E> page = service.getAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**+
     *
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @Override
    public ResponseEntity<E> get(@PathVariable("id") T id) {
        E e = service.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    /***
     *
     * @param
     * @param id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     */
    @Override
    public ResponseEntity<E> update(@Valid @RequestBody E e, @PathVariable("id") T id) {
        Optional<E> optionalEntity = service.get(id);
        if (!optionalEntity.isPresent()) {
            throw new EntityNotFoundException("The entity  with the current id, " + id + " doesn't" +
                " exists");
        }
        e.setId(id);
        return new ResponseEntity<>(service.save(e), HttpStatus.OK);
    }

    /***
     *
     * @param id
     */
    @Override
    public void delete(@PathVariable("id") T id) {
        E e = service.get(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        try {
            service.delete(e);
        } catch (Exception x) {
            if (x instanceof SQLIntegrityConstraintViolationException | x instanceof DataIntegrityViolationException) {
                throw new ConstraintViolationException("Violating database constraints for this entity");
            } else {
                throw new RuntimeException("Error: " + x.getMessage());
            }
        }
    }

    /**
     * @param ids
     * @return
     */
    @Override
    public ResponseEntity<Void> massiveDelete(@PathVariable List<T> ids){
        ids.forEach(id-> {
            E e = service.get(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + id + " not found."));
            try {
                service.delete(e);
            } catch (Exception x) {
                if (x instanceof SQLIntegrityConstraintViolationException | x instanceof DataIntegrityViolationException) {
                    throw new ConstraintViolationException("Violating database constraints for this entity");
                } else {
                    throw new RuntimeException("Error: " + x.getMessage());
                }
            }
        });
        return ResponseEntity.ok().build();
    }
}
