package com.bogdevich.profile.controller;

import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.util.mapper.ProfileMapper;
import com.bogdevich.profile.controller.util.mapper.ProjectMapper;
import com.bogdevich.profile.controller.util.validator.ProfileRequestDtoValidator;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.IdRequestDTO;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.entity.dto.response.ProfilesListDTO;
import com.bogdevich.profile.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest controller for profile endpoint.
 *
 * @author Eugene Bogdevich
 */
@RestController
@RequestMapping(
        value = "/profiles",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@PropertySource("classpath:security.properties")
public class ProfileRestController extends AbstractRestController{

    private final IProfileService profileService;
    private final ProfileRequestDtoValidator profileRequestDtoValidator;

    @Autowired
    public ProfileRestController(
            ProfileMapper profileMapper,
            ProjectMapper projectMapper,
            IProfileService profileService,
            ProfileRequestDtoValidator profileRequestDtoValidator) {
        super(profileMapper, projectMapper);
        this.profileService = profileService;
        this.profileRequestDtoValidator = profileRequestDtoValidator;
    }

    @InitBinder("profileRequestDTO")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(profileRequestDtoValidator);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        ProfileResponseDTO profileResponseDTO = profileService
                .findOne(id)
                .map(profileMapper::profileToDto)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found"));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProfilesListDTO> getProfiles(
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        PageRequest pageRequest = new PageRequest(
                checkParameter(page, DEFAULT_PAGE, page1 -> (page1 != null && page1 >= 0)),
                checkParameter(size, DEFAULT_SIZE, size1 -> (size1 != null && size1 >= 1)));
        ProfilesListDTO profilesListDTO = profileService
                .findAll(pageRequest)
                .map(profiles -> profiles.map(profileMapper::profileToDto))
                .map(this::createProfileListDto)
                .orElseThrow(() -> new DataNotFoundException("Profiles are not found"));
        return new ResponseEntity<>(profilesListDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> createProfile(
            @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        throw new UnsupportedOperationException("Bad request");
    }

    @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Long id,
            @RequestBody @Valid ProfileRequestDTO profileRequestDTO) {
        Profile profile = profileMapper.dtoToProfile(profileRequestDTO);
        ProfileResponseDTO profileResponseDTO = profileService
                .update(profile, id)
                .map(profileMapper::profileToDto)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found"));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @PostMapping(
            value = "/{profileId:[0-9]+}/projects",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> addProjectToProfile(
            @PathVariable Long profileId,
            @RequestBody @Valid IdRequestDTO projectIdDto) {
        ProfileResponseDTO profileResponseDTO = profileService
                .addProjectToProfile(profileId, projectIdDto.getId())
                .map(profileMapper::profileToDto)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found"));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @PostMapping(
            value = "/{profileId:[0-9]+}/roles/",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> addRoleToProfile(
            @PathVariable Long profileId,
            @RequestBody @Valid IdRequestDTO roleIdDto) {
        ProfileResponseDTO profileResponseDTO = profileService
                .addRoleToProfile(profileId, roleIdDto.getId())
                .map(profileMapper::profileToDto)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found"));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{profileId:[0-9]+}/projects/{projectId:[0-9]+}")
    public ResponseEntity<ProfileResponseDTO> removeProjectFromProfile(
            @PathVariable Long profileId,
            @PathVariable Long projectId) {
        ProfileResponseDTO profileResponseDTO = profileService
                .removeProjectFromProfile(profileId, projectId)
                .map(profileMapper::profileToDto)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found"));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{profileId:[0-9]+}/roles/{roleId:[0-9]+}")
    public ResponseEntity<ProfileResponseDTO> removeRoleFromProfile(
            @PathVariable Long profileId,
            @PathVariable Long roleId) {
        ProfileResponseDTO profileResponseDTO = profileService
                .removeRoleFromProfile(profileId, roleId)
                .map(profileMapper::profileToDto)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found"));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id:[0-9]+}")
    public ResponseEntity<ProfileResponseDTO> deleteProfile(@PathVariable Long id) {
        ProfileResponseDTO profileResponseDTO = profileService
                .delete(id)
                .map(profileMapper::profileToDto)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found"));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }
}
