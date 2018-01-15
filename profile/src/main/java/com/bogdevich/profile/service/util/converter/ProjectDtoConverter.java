package com.bogdevich.profile.service.util.converter;

import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.dto.response.ProjectResponseDTO;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

/**
 * {@link org.modelmapper.ModelMapper} configuration
 * for converting {@link Project} to {@link ProjectResponseDTO}
 *
 * @author Eugene Bogdevich
 */
public class ProjectDtoConverter extends ConverterConfigurerSupport<Project, ProjectResponseDTO> {
    @Override
    protected Converter<Project, ProjectResponseDTO> converter() {
        return new AbstractConverter<Project, ProjectResponseDTO>() {
            @Override
            protected ProjectResponseDTO convert(Project project) {
                //ToDO: AbstractConverter convert implementation for Project
                throw new UnsupportedOperationException();
            }
        };
    }
}