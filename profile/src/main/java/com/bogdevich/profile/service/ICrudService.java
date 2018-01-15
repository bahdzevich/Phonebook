package com.bogdevich.profile.service;

import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.service.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Crud service interface.
 *
 * @param <T> data transfer object.
 * @author Eugene Bogdevich
 */
public interface ICrudService<T> {

    Optional<T> save(T t);

    Optional<T> findOne(Long id);

    List<T> findAll();

    Optional<Page<T>> findAll(Pageable pageable);

    Optional<T> update(T t);

    boolean delete(Long id);
}
