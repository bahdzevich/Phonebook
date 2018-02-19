package com.bogdevich.profile.controller.util.validator;

import com.bogdevich.profile.controller.util.mapper.ProjectMapper;
import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.dto.request.ProjectRequestDTO;
import com.bogdevich.profile.repository.ProjectRepository;
import com.bogdevich.profile.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProjectRequestDtoValidator implements Validator {

    private final IProjectService projectService;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectRequestDtoValidator(
            IProjectService projectService,
            ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProjectRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProjectRequestDTO projectRequestDTO = (ProjectRequestDTO) target;
        Project project = projectMapper.dtoToProject(projectRequestDTO);
        if (projectService.exists(project)) {
            errors.reject("Project already exists");
        }
    }
}
