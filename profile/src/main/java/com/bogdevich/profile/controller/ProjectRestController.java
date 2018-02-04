package com.bogdevich.profile.controller;

import com.bogdevich.profile.context.SecurityContextHolder;
import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.exception.PermissionException;
import com.bogdevich.profile.controller.util.mapper.ProfileResponseMapper;
import com.bogdevich.profile.controller.util.mapper.ProjectResponseMapper;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.entity.dto.response.ProfilesListDTO;
import com.bogdevich.profile.entity.dto.response.ProjectResponseDTO;
import com.bogdevich.profile.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(
        value = "/projects",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ProjectRestController extends AbstractRestController{

    private final IProjectService projectService;
    private final ProjectResponseMapper projectResponseMapper;

    @GetMapping
    public ResponseEntity<ProjectResponseDTO> getProjects() {
        return null;
    }

    public ProjectRestController(
            ProfileResponseMapper profileResponseMapper,
            IProjectService projectService,
            ProjectResponseMapper projectResponseMapper) {
        super(profileResponseMapper);
        this.projectService = projectService;
        this.projectResponseMapper = projectResponseMapper;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<ProjectResponseDTO>> getProjects(
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        PageRequest pageRequest = new PageRequest(
                this.checkParameter(page, DEFAULT_PAGE, page1 -> !(page1 == null || page1 < 0)),
                this.checkParameter(size, DEFAULT_SIZE, size1 -> !(size1 == null || size1 < 1)));

        List<ProjectResponseDTO> projectResponseDTOList = projectService
                .findAll(pageRequest)
                .map(projects -> projects.map(projectResponseMapper::projectToDto))
                .map(Slice::getContent)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                String.format("Can't find projects: page = \'%s\', size = \'%s\'",
                                        pageRequest.getPageNumber(), pageRequest.getPageSize())));
        return new ResponseEntity<>(projectResponseDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/profiles")
    public ResponseEntity<ProfilesListDTO> getProfilesByProjectId(
            @PathVariable Long id,
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        PageRequest pageRequest = new PageRequest(
                this.checkParameter(page, DEFAULT_PAGE, page1 -> !(page1 == null || page1 < 0)),
                this.checkParameter(size, DEFAULT_SIZE, size1 -> !(size1 == null || size1 < 1)));

        ProfilesListDTO profilesListDTO = projectService
                .findProfilesByProjectId(id, pageRequest)
                .map(profiles -> profiles.map(profileResponseMapper::profileToDto))
                .map(this::createProfileListDto)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                String.format("Can't find profiles: project-id = \'%s\', page = \'%s\', size = \'%s\'.",
                                        id, pageRequest.getPageNumber(), pageRequest.getPageSize())));
        return new ResponseEntity<>(profilesListDTO, HttpStatus.OK);
    }

    @Override
    protected <T> void checkPermission(T t) {
        if (!SecurityContextHolder
                .getThreadLocalAuthorityList()
                .contains(this.adminRoleName)) {
            throw new PermissionException("Access denied.");
        }
    }
}
