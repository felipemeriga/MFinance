package com.meriga.mfinance.controller;

import com.meriga.mfinance.domain.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

public interface CommonController<E extends AbstractEntity, T> {
    @PostMapping
    ResponseEntity<E> save(@RequestBody E entity);

    @GetMapping
    ResponseEntity<Page<E>> list(@Nullable @PathParam("search") String search, Pageable pageable);

    @GetMapping("{id}")
    ResponseEntity<E> get(@PathVariable("id") T id);

    @PutMapping("{id}")
    ResponseEntity<E> update(@Valid @RequestBody E e, @PathVariable("id") T id);

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") T id);

    @DeleteMapping("delete/{ids}")
    ResponseEntity<Void> massiveDelete(@PathVariable List<T> ids);

}
