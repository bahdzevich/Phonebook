package com.bogdevich.profile.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
    Optional<T> save(T t) throws Exception;
    Optional<T> findOne(Long id) throws Exception;
    List<T> findAll() throws Exception;
    Optional<T> update(T t) throws Exception;
    void delete(T t) throws Exception;
}
