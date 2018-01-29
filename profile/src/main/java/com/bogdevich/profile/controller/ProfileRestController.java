package com.bogdevich.profile.controller;

import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.exception.InternalServiceException;
import com.bogdevich.profile.controller.util.mapper.ProfileRequestMapper;
import com.bogdevich.profile.controller.util.mapper.ProfileResponseMapper;
import com.bogdevich.profile.controller.util.validator.ProfileRequestDtoValidator;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.entity.dto.response.ProfilesListDTO;
import com.bogdevich.profile.service.IProfileService;
import com.bogdevich.profile.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ProfileRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Integer DEFAULT_PAGE = 0;
    private final Integer DEFAULT_SIZE = 20;

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
    public ProfileRestController(
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
                .orElseThrow(() -> new DataNotFoundException(String.format("There is no profile with such id: %s.", id)));
        ProfileResponseDTO profileResponseDTO = profileResponseMapper.profileToDto(profile);
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProfilesListDTO> getProfiles(
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        final Integer pageNumber = (page == null || page < 0) ? DEFAULT_PAGE : page;
        final Integer pageSize = (size == null || size < 1) ? DEFAULT_SIZE : size;
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
        Page<ProfileResponseDTO> profilesPage = profileService
                .findAll(pageRequest)
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("Can't find profiles for the searching params; page = %s, size = %s", pageNumber, pageSize)
                )).map(profileResponseMapper::profileToDto);
        ProfilesListDTO profilesListDTO = new ProfilesListDTO();
        profilesListDTO.setItems(profilesPage.getTotalElements());
        profilesListDTO.setPages(profilesPage.getTotalPages());
        profilesListDTO.setProfiles(profilesPage.getContent());
        return new ResponseEntity<>(profilesListDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO profileRequestDTO) {
        //ToDo: Delete logger.
        logger.debug(String.format("Trying to create profile; Profile: \'%s\'.", profileRequestDTO));
        Profile profile = profileRequestMapper.dtoToProfile(profileRequestDTO);
        Profile savedProfile = profileService
                .save(profile)
                .orElseThrow(() -> new InternalServiceException(String.format("There is a problem with profile saving; Profile: \'%s\'.", profileRequestDTO)));
        ProfileResponseDTO savedProfileResponseDTO = profileResponseMapper.profileToDto(savedProfile);
        return new ResponseEntity<>(savedProfileResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        //ToDo: Delete logger.
        logger.debug(String.format("Trying to update profile with id: \'%s\'; Profile: \'%s\'.", id, profileRequestDTO));
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
        logger.debug(String.format("Trying to delete profile with id: \'%s\'.", id));
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
