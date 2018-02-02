package com.bogdevich.profile.controller.util.mapper;

import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.dto.response.ProjectResponseDTO;
import org.mapstruct.Mapper;

/**
 * Project mapper.
 *
 * @author Eugene Bogdevich
 */
@Mapper(componentModel = "spring")
public interface ProjectResponseMapper {

    //ProjectResponseMapper INSTANCE = Mappers.getMapper(ProjectResponseMapper.class);

    ProjectResponseDTO projectToDto(Project project);

    Project dtoToProject(ProjectResponseDTO projectResponseDTO);
}
