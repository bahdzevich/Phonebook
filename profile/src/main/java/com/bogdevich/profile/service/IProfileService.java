package com.bogdevich.profile.service;

import com.bogdevich.profile.entity.domain.Profile;

import java.util.Optional;

public interface IProfileService extends ICrudService<Profile>{

    /**
     * Add a project to profile project set
     * @param profileId id of a {@link Profile}
     * @param projectId id of a {@link com.bogdevich.profile.entity.domain.Project}
     * @return {@link Optional<Profile>}
     */
    Optional<Profile> addProjectToProfile(Long profileId, Long projectId);

    /**
     * Add a role to profile role set
     * @param profileId id of a {@link Profile}
     * @param roleId id of a {@link com.bogdevich.profile.entity.domain.Role}
     * @return {@link Optional<Profile>}
     */
    Optional<Profile> addRoleToProfile(Long profileId, Long roleId);

    /**
     * Remove a project from profile project set
     * @param profileId id of a {@link Profile}
     * @param projectId id of a {@link com.bogdevich.profile.entity.domain.Project}
     * @return {@link Optional<Profile>}
     */
    Optional<Profile> removeProjectFromProfile(Long profileId, Long projectId);

    /**
     * Remove a role from profile role set
     * @param profileId id of a {@link Profile}
     * @param roleId id of a {@link com.bogdevich.profile.entity.domain.Role}
     * @return {@link Optional<Profile>}
     */
    Optional<Profile> removeRoleFromProfile(Long profileId, Long roleId);
}
