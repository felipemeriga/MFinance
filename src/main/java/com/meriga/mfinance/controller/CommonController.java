package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.AbstractEntity;
import com.meriga.mfinance.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CommonController<E extends AbstractEntity, T> {
    @PostMapping
    ResponseEntity<E> save(@RequestBody E entity);

    @GetMapping
    ResponseEntity<Page<E>> list(Pageable pageable);

    @GetMapping("{id}")
    ResponseEntity<E> get(@PathVariable("id") T id);

    @PutMapping("{id}")
    ResponseEntity<E> update(@Valid @RequestBody E e, @PathVariable("id") T id);

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") T id);
}
