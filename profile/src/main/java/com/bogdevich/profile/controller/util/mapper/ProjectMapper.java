package com.bogdevich.profile.controller.util.mapper;

import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.dto.request.ProjectRequestDTO;
import com.bogdevich.profile.entity.dto.response.ProjectResponseDTO;
import org.mapstruct.Mapper;

/**
 * Project mapper.
 *
 * @author Eugene Bogdevich
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectResponseDTO projectToDto(Project project);
    Project dtoToProject(ProjectRequestDTO projectRequestDTO);
}
