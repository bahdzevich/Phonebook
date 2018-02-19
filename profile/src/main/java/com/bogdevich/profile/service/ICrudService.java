package com.bogdevich.profile.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Crud impl interface.
 *
 * @param <T> data transfer object.
 * @author Eugene Bogdevich
 */
public interface ICrudService<T> {

    T save(T t);

    Optional<T> findOne(Long id);

    List<T> findAll();

    Optional<Page<T>> findAll(Pageable pageable);

    Optional<T> update(T t, Long id);

    Optional<T> delete(Long id);

    boolean exists(T t);
}
