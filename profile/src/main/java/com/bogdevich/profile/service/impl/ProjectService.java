package com.bogdevich.profile.service.impl;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.repository.ProfileRepository;
import com.bogdevich.profile.repository.ProjectRepository;
import com.bogdevich.profile.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public ProjectService(
            ProjectRepository projectRepository,
            @Qualifier("profileRepository") ProfileRepository profileRepository) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Page<Profile>> findProfilesByProjectId(long id, Pageable pageable) {
        Page<Profile> profilePage = null;
        try {
            profilePage = profileRepository.findProfilesByProjectId(id, pageable);
        } catch (Exception ex) {
            logger.error(
                    String.format(
                            "Exception while searching profiles – page = %s, size = %s",
                            pageable.getPageNumber(),
                            pageable.getPageSize()),
                    ex);
        }
        return Optional.ofNullable(profilePage);
    }

    @Override
    public Optional<Project> save(Project project) {
        return null;
    }

    @Override
    public Optional<Project> findOne(Long id) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public Optional<Page<Project>> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Project> update(Project project) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
