package com.bogdevich.profile.service.impl;

import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.service.IRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Override
    public Optional<Role> save(Role role) {
        return null;
    }

    @Override
    public Optional<Role> findOne(Long id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Optional<Page<Role>> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Role> update(Role role) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
