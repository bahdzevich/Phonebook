package com.bogdevich.profile.entity.dto;

import java.util.Set;

/**
 * POJO class for {@link com.bogdevich.profile.entity.domain.Project}
 *
 * @author Eugene Bogdevich
 */
public class ProjectDTO {

    private Long id;
    private String name;
    private Set<ProfileDTO> profiles;

}
