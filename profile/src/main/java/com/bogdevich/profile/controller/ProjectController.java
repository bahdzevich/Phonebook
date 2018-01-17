package com.bogdevich.profile.controller;

import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.mapper.ProfileResponseMapper;
import com.bogdevich.profile.controller.mapper.ProjectResponseMapper;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.entity.dto.response.ProfilesListDTO;
import com.bogdevich.profile.entity.dto.response.ProjectResponseDTO;
import com.bogdevich.profile.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/phonebook/v1/api/projects",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ProjectController {

    private final Integer DEFAULT_PAGE = 1;
    private final Integer DEFAULT_SIZE = 20;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IProjectService projectService;
    private final ProfileResponseMapper profileResponseMapper;
    private final ProjectResponseMapper projectResponseMapper;

    @Autowired
    public ProjectController(
            IProjectService projectService,
            ProfileResponseMapper profileResponseMapper,
            ProjectResponseMapper projectResponseMapper) {
        this.projectService = projectService;
        this.profileResponseMapper = profileResponseMapper;
        this.projectResponseMapper = projectResponseMapper;
    }

    @GetMapping
    public ResponseEntity<ProjectResponseDTO> getProjects() {
        return null;
    }

    @GetMapping(value = "/{id}/profiles")
    public ResponseEntity<ProfilesListDTO> getProfilesByProjectId(
            @PathVariable Long id,
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        final Integer pageNumber = (page == null || page < 1) ? DEFAULT_PAGE : page;
        final Integer pageSize = (size == null || size < 1) ? DEFAULT_SIZE : size;
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
        Page<ProfileResponseDTO> profilePage = projectService.findProfilesByProjectId(id, pageRequest)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("Can't find profiles for the searching params; project ID =%s, page = %s, size = %s.", id, pageNumber, pageSize)
                )).map(profileResponseMapper::profileToDto);
        ProfilesListDTO profilesListDTO = new ProfilesListDTO();
        profilesListDTO.setItems(profilePage.getTotalElements());
        profilesListDTO.setPages(profilePage.getTotalPages());
        profilesListDTO.setProfiles(profilePage.getContent());
        return new ResponseEntity<>(profilesListDTO, HttpStatus.OK);
    }
}
