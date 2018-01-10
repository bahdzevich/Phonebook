package com.bogdevich.profile.service.util.converter;

import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.dto.ProjectDTO;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

/**
 * {@link org.modelmapper.ModelMapper} configuration
 * for converting {@link Project} to {@link ProjectDTO}
 *
 * @author Eugene Bogdevich
 */
public class ProjectDtoConverter extends ConverterConfigurerSupport<Project, ProjectDTO> {
    @Override
    protected Converter<Project, ProjectDTO> converter() {
        return new AbstractConverter<Project, ProjectDTO>() {
            @Override
            protected ProjectDTO convert(Project project) {
                //ToDO: AbstractConverter convert implementation for Project
                throw new UnsupportedOperationException();
            }
        };
    }
}