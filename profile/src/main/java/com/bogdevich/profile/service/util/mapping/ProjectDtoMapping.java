package com.bogdevich.profile.service.util.mapping;

import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.dto.response.ProjectResponseDTO;
import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * {@link org.modelmapper.ModelMapper} configuration for mapping
 * {@link Project} and {@link ProjectResponseDTO}.
 *
 * @author Eugene Bogdevich
 */
@Component
public class ProjectDtoMapping extends PropertyMapConfigurerSupport<Project, ProjectResponseDTO> {

    @Override
    public PropertyMap<Project, ProjectResponseDTO> mapping() {
        return new PropertyMap<Project, ProjectResponseDTO>() {
            @Override
            protected void configure() {
                //ToDo: PropertyMap mapping implementation for Project
                throw new UnsupportedOperationException();
            }
        };
    }
}
