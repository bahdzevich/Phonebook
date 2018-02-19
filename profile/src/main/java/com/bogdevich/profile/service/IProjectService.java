package com.bogdevich.profile.service;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProjectService extends ICrudService<Project> {

    /**
     * Find profiles list by project id.
     *
     * @return {@link List} of {@link Profile}
     */
    Optional<Page<Profile>> findProfilesByProjectId(Pageable pageable, Long id);
}
