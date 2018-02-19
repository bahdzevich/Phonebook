package com.bogdevich.profile.controller;

import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.util.mapper.ProfileMapper;
import com.bogdevich.profile.controller.util.mapper.ProjectMapper;
import com.bogdevich.profile.controller.util.validator.ProjectRequestDtoValidator;
import com.bogdevich.profile.entity.domain.Project;
import com.bogdevich.profile.entity.dto.request.ProjectRequestDTO;
import com.bogdevich.profile.entity.dto.response.ProfilesListDTO;
import com.bogdevich.profile.entity.dto.response.ProjectResponseDTO;
import com.bogdevich.profile.service.IProjectService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/projects",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ProjectRestController extends AbstractRestController{

    private final IProjectService projectService;
    private final ProjectRequestDtoValidator projectRequestDtoValidator;

    public ProjectRestController(
            ProfileMapper profileMapper,
            ProjectMapper projectMapper,
            IProjectService projectService,
            ProjectRequestDtoValidator projectRequestDtoValidator) {
        super(profileMapper, projectMapper);
        this.projectService = projectService;
        this.projectRequestDtoValidator = projectRequestDtoValidator;
    }

    @InitBinder("projectRequestDTO")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(projectRequestDtoValidator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProjectResponseDTO> createProject(
            @Valid @RequestBody ProjectRequestDTO projectRequestDTO) {
        Project project = projectMapper.dtoToProject(projectRequestDTO);
        ProjectResponseDTO projectResponseDTO = projectMapper
                .projectToDto(projectService.save(project));
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getProjects(
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        PageRequest pageRequest = new PageRequest(
                this.checkParameter(page, DEFAULT_PAGE, page1 -> (page1 != null && page1 >= 0)),
                this.checkParameter(size, DEFAULT_SIZE, size1 -> (size1 != null && size1 >= 1)));
        List<ProjectResponseDTO> projectResponseDTOList = projectService
                .findAll(pageRequest)
                .map(projects -> projects.map(projectMapper::projectToDto))
                .map(Slice::getContent)
                .orElseThrow(() -> new DataNotFoundException("Projects are not found"));
        return new ResponseEntity<>(projectResponseDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id:[0-9]+}/profiles")
    public ResponseEntity<ProfilesListDTO> getProfilesByProjectId(
            @PathVariable Long id,
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        PageRequest pageRequest = new PageRequest(
                this.checkParameter(page, DEFAULT_PAGE, page1 -> (page1 != null && page1 >= 0)),
                this.checkParameter(size, DEFAULT_SIZE, size1 -> (size1 != null && size1 >= 1)));
        ProfilesListDTO profilesListDTO = projectService
                .findProfilesByProjectId(pageRequest, id)
                .map(profiles -> profiles.map(profileMapper::profileToDto))
                .map(this::createProfileListDto)
                .orElseThrow(() -> new DataNotFoundException("Profiles are not found"));
        return new ResponseEntity<>(profilesListDTO, HttpStatus.OK);
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @RequestBody @Valid ProjectRequestDTO projectRequestDTO,
            @PathVariable Long id) {
        Project project = projectMapper.dtoToProject(projectRequestDTO);
        ProjectResponseDTO projectResponseDTO = projectService
                .update(project, id)
                .map(projectMapper::projectToDto)
                .orElseThrow(() -> new DataNotFoundException("Project is not found"));
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<ProjectResponseDTO> deleteProject(
            @PathVariable Long id) {
        ProjectResponseDTO projectResponseDTO = projectService
                .delete(id)
                .map(projectMapper::projectToDto)
                .orElseThrow(() -> new DataNotFoundException("Project is not found"));
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.OK);
    }

}
