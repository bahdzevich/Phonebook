package com.bogdevich.profile.controller;

import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.exception.InternalServiceException;
import com.bogdevich.profile.controller.mapper.ProfileRequestMapper;
import com.bogdevich.profile.controller.mapper.ProfileResponseMapper;
import com.bogdevich.profile.controller.validator.ProfileRequestDtoValidator;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.service.IProfileService;
import com.bogdevich.profile.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rest controller for profile endpoint.
 *
 * @author Eugene Bogdevich
 */
@RestController
@RequestMapping(
        value = "/phonebook/v1/api/profiles",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IProfileService profileService;
    private final IRoleService roleService;
    private final ProfileResponseMapper profileResponseMapper;
    private final ProfileRequestMapper profileRequestMapper;
    private final ProfileRequestDtoValidator profileRequestDtoValidator;

    @InitBinder("profileRequestDTO")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(profileRequestDtoValidator);
    }

    @Autowired
    public ProfileController(
            IProfileService profileService,
            IRoleService roleService,
            ProfileResponseMapper profileResponseMapper,
            ProfileRequestMapper profileRequestMapper,
            ProfileRequestDtoValidator profileRequestDtoValidator
    ) {
        this.profileService = profileService;
        this.roleService = roleService;
        this.profileResponseMapper = profileResponseMapper;
        this.profileRequestMapper = profileRequestMapper;
        this.profileRequestDtoValidator = profileRequestDtoValidator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        Profile profile = profileService
                .findOne(id)
                .orElseThrow(() -> new DataNotFoundException("Profile is not found."));
        ProfileResponseDTO profileResponseDTO = profileResponseMapper.profileToDto(profile);
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getProfiles() {
        List<ProfileResponseDTO> profileResponseDTOList = profileService
                .findAll().stream()
                .map(profileResponseMapper::profileToDto).collect(Collectors.toList());
        return profileResponseDTOList.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(profileResponseDTOList, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO profileRequestDTO) {
        //ToDo: Delete logger.
        logger.debug(String.format("Create profile: %s", profileRequestDTO));
        Profile profile = profileRequestMapper.dtoToProfile(profileRequestDTO);
        Profile savedProfile = profileService
                .save(profile)
                .orElseThrow(() -> new InternalServiceException(String.format("Can not save profile: %s.", profileRequestDTO)));
        ProfileResponseDTO savedProfileResponseDTO = profileResponseMapper.profileToDto(savedProfile);
        return new ResponseEntity<>(savedProfileResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        //ToDo: Delete logger.
        logger.debug(String.format("Update profile: %s; ID: %s", profileRequestDTO, id));
        Profile profile = profileRequestMapper.dtoToProfile(profileRequestDTO);
        profile.setId(id);
        Profile updatedProfile = profileService
                .save(profile)
                .orElseThrow(() -> new InternalServiceException(String.format("Can not update profile: %s.", profileRequestDTO)));
        ProfileResponseDTO profileResponseDTO = profileResponseMapper.profileToDto(updatedProfile);
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity deleteProfile(@PathVariable Long id) {
        //ToDo: Delete logger.
        logger.debug(String.format("Delete profile: ID = %s", id));
        boolean profileDeleted = profileService.delete(id);
        return profileDeleted ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

/*    @GetMapping("/{query}")
    public ResponseEntity<List<ProfileResponseDTO>> findProfileByQuery(@PathVariable String query) {
        //ToDo: opportunity to find profiles by custom select query.
        throw new UnsupportedOperationException();
    }*/
}
