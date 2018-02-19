package com.bogdevich.profile.service.impl;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.repository.ProfileRepository;
import com.bogdevich.profile.repository.ProjectRepository;
import com.bogdevich.profile.security.ISecurityService;
import com.bogdevich.profile.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.bogdevich.profile.service.util.ServiceUtils.copyProperties;
import static com.bogdevich.profile.service.util.ServiceUtils.requireNonNull;

@Service
public class ProjectService implements IProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final ISecurityService securityService;

    @Autowired
    public ProjectService(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            ISecurityService securityService) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.securityService = securityService;
    }

    @Override
    public Optional<Page<Profile>> findProfilesByProjectId(Pageable pageable, Long id) {
        requireNonNull(pageable, "Page request is null");
        requireNonNull(id, "Project id is null");
        Page<Profile> profilePage = null;
        try {
            profilePage = profileRepository.findProfilesByProjectId(id, pageable);
        } catch (SQLException ex) {
            LOGGER.error(String.format(
                    "Exception while searching profiles – page = %s, size = %s",
                    pageable.getPageNumber(), pageable.getPageSize()
            ), ex);
        }
        return Optional.ofNullable(profilePage);
    }

    @Override
    public Project save(Project project) {
        requireNonNull(project, "Project is null");
        securityService.checkPermission();
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findOne(Long id) {
        requireNonNull(id, "Project id is null");
        return Optional.ofNullable(projectRepository.findOne(id));
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Page<Project>> findAll(Pageable pageable) {
        requireNonNull(pageable, "Page request is null");
        return Optional.ofNullable(projectRepository.findAll(pageable));
    }

    @Override
    @Transactional
    public Optional<Project> update(final Project project, final Long id) {
        requireNonNull(project, "Project is null");
        requireNonNull(id, "Project id is null");
        securityService.checkPermission();
        return Optional
                .ofNullable(projectRepository.findOne(id))
                .map(current -> {
                    /*It works cause the method update() is @Transactional*/
                    copyProperties(current, project, "id", "profiles");
                    return current;
                });
    }

    @Override
    public Optional<Project> delete(Long id) {
        requireNonNull(id, "Project id is null");
        securityService.checkPermission();
        Project project = projectRepository.findOne(id);
        if (project != null) {
            projectRepository.delete(id);
        }
        return Optional.ofNullable(project);
    }

    @Override
    public boolean exists(Project project) {
        requireNonNull(project, "Project is null");
        return projectRepository.exists(Example.of(project));
    }
}
