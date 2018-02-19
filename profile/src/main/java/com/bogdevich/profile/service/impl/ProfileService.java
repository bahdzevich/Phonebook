package com.bogdevich.profile.service.impl;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.repository.ProfileRepository;
import com.bogdevich.profile.repository.ProjectRepository;
import com.bogdevich.profile.repository.RoleRepository;
import com.bogdevich.profile.security.ISecurityService;
import com.bogdevich.profile.service.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bogdevich.profile.service.util.ServiceUtils.copyProperties;
import static com.bogdevich.profile.service.util.ServiceUtils.requireNonNull;

/**
 * Profile impl class.
 *
 * @author Eugene Bogdevich
 */
@Service
public class ProfileService implements IProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileService.class);

    private final ProfileRepository profileRepository;
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final ISecurityService securityService;

    @Autowired
    public ProfileService(
            ProfileRepository profileRepository,
            ProjectRepository projectRepository,
            RoleRepository roleRepository,
            ISecurityService securityService
    ) {
        this.profileRepository = profileRepository;
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.securityService = securityService;
    }

    //ToDo: Chose what service is responsible of user creation.
    @Override
    public Profile save(final Profile profile) {
        throw new UnsupportedOperationException("Bad invocation");
    }

    @Override
    public Optional<Profile> findOne(Long id) {
        requireNonNull(id, "Profile id is null");
        return Optional
                .ofNullable(profileRepository.findOne(id));
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Page<Profile>> findAll(final Pageable pageable) {
        requireNonNull(pageable, "Page request is null");
        return Optional
                .ofNullable(profileRepository.findAll(pageable));
    }

    @Override
    @Transactional
    public Optional<Profile> update(final Profile profile, final Long id) {
        requireNonNull(profile, "Profile is null");
        requireNonNull(id, "Profile id is null");
        securityService.checkPermission(id);
        return Optional
                .ofNullable(profileRepository.findOne(id))
                .map(current -> {
                    copyProperties(current, profile, "id", "projects", "roles", "password");
                    return current;
                });
    }

    @Override
    public Optional<Profile> delete(Long id) {
        requireNonNull(id, "Profile id is null");
        securityService.checkPermission(id);
        Profile profile = profileRepository.findOne(id);
        if (profile != null) {
            profileRepository.delete(id);
        }
        return Optional.ofNullable(profile);
    }

    @Override
    @Transactional
    public Optional<Profile> addProjectToProfile(final Long profileId, final Long projectId) {
        requireNonNull(profileId, "Profile id is null");
        requireNonNull(projectId, "Project id is null");
        securityService.checkPermission();
        Profile profile = profileRepository.findOne(profileId);
        Project project = projectRepository.findOne(projectId);
        if (profile != null && project != null) {
            profile.getProjects().add(project);
        }
        return Optional.ofNullable(profile);
    }

    @Override
    @Transactional
    public Optional<Profile> addRoleToProfile(Long profileId, Long roleId) {
        requireNonNull(profileId, "Profile id is null");
        requireNonNull(roleId, "Role id is null");
        securityService.checkPermission();
        Profile profile = profileRepository.findOne(profileId);
        Role role = roleRepository.findOne(roleId);
        if (profile != null && role != null) {
            profile.getRoles().add(role);
        }
        return Optional.ofNullable(profile);
    }

    @Override
    @Transactional
    public Optional<Profile> removeProjectFromProfile(Long profileId, Long projectId) {
        requireNonNull(profileId, "Profile id is null");
        requireNonNull(projectId, "Project id is null");
        securityService.checkPermission();
        Profile profile = profileRepository.findOne(profileId);
        Project project = projectRepository.findOne(projectId);
        if (profile != null && project != null) {
            profile.getProjects().remove(project);
        }
        return Optional.ofNullable(profile);
    }

    @Override
    @Transactional
    public Optional<Profile> removeRoleFromProfile(Long profileId, Long roleId) {
        requireNonNull(profileId, "Profile id is null");
        requireNonNull(roleId, "Role id is null");
        securityService.checkPermission();
        Profile profile = profileRepository.findOne(profileId);
        Role role = roleRepository.findOne(roleId);
        if (profile != null && role != null) {
            profile.getRoles().remove(role);
        }
        return Optional.ofNullable(profile);
    }

    @Override
    public boolean exists(Profile profile) {
        requireNonNull(profile, "Profile is null");
        return profileRepository.exists(Example.of(profile));
    }
}
