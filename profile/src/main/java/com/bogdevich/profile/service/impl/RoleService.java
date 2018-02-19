package com.bogdevich.profile.service.impl;

import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.repository.RoleRepository;
import com.bogdevich.profile.security.ISecurityService;
import com.bogdevich.profile.service.IRoleService;
import com.bogdevich.profile.service.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bogdevich.profile.service.util.ServiceUtils.requireNonNull;

@Service
public class RoleService implements IRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;
    private final ISecurityService securityService;

    @Autowired
    public RoleService(
            RoleRepository roleRepository,
            ISecurityService securityService) {
        this.roleRepository = roleRepository;
        this.securityService = securityService;
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Optional<Role> findOne(Long id) {
        requireNonNull(id, "Role id is null");
        securityService.checkPermission();
        return Optional.ofNullable(roleRepository.findOne(id));
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
    public Optional<Role> update(Role role, Long id) {
        return null;
    }

    @Override
    public Optional<Role> delete(Long id) {
        return null;
    }

    @Override
    public boolean exists(Role role) {
        return false;
    }
}
