package com.bogdevich.profile.controller;

import com.bogdevich.profile.context.SecurityContextHolder;
import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.exception.InternalServiceException;
import com.bogdevich.profile.controller.exception.PermissionException;
import com.bogdevich.profile.controller.util.mapper.ProfileRequestMapper;
import com.bogdevich.profile.controller.util.mapper.ProfileResponseMapper;
import com.bogdevich.profile.controller.util.validator.ProfileRequestDtoValidator;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.entity.dto.response.ProfilesListDTO;
import com.bogdevich.profile.service.IProfileService;
import com.bogdevich.profile.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private final ProfileRequestMapper profileRequestMapper;
    private final ProfileRequestDtoValidator profileRequestDtoValidator;

    @InitBinder("profileRequestDTO")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(profileRequestDtoValidator);
    }

    @Autowired
    public ProfileRestController(
            ProfileResponseMapper profileResponseMapper,
            IProfileService profileService,
            ProfileRequestMapper profileRequestMapper,
            ProfileRequestDtoValidator profileRequestDtoValidator) {
        super(profileResponseMapper);
        this.profileService = profileService;
        this.profileRequestMapper = profileRequestMapper;
        this.profileRequestDtoValidator = profileRequestDtoValidator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        ProfileResponseDTO profileResponseDTO = profileService
                .findOne(id)
                .map(profileResponseMapper::profileToDto)
                .orElseThrow(() ->
                        new DataNotFoundException(String.format("There is no profile with such id: %s.", id)));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProfilesListDTO> getProfiles(
            @RequestParam(value = "page", required = false)  Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        PageRequest pageRequest = new PageRequest(
                this.checkParameter(page, DEFAULT_PAGE, page1 -> !(page1 == null || page1 < 0)),
                this.checkParameter(size, DEFAULT_SIZE, size1 -> !(size1 == null || size1 < 1)));

        ProfilesListDTO profilesListDTO = profileService
                .findAll(pageRequest)
                .map(profiles -> profiles.map(profileResponseMapper::profileToDto))
                .map(this::createProfileListDto)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                String.format("Can't find profiles: page = \'%s\', size = \'%s\'",
                                        pageRequest.getPageNumber(), pageRequest.getPageSize())));

        return new ResponseEntity<>(profilesListDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> createProfile(@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        LOGGER.info(String.format("POST :: profile: \'%s\'.", profileRequestDTO));
        Profile profile = profileRequestMapper.dtoToProfile(profileRequestDTO);
        ProfileResponseDTO savedProfileResponseDTO = profileService
                .save(profile)
                .map(profileResponseMapper::profileToDto)
                .orElseThrow(() -> new InternalServiceException(String.format("There is a problem with profile saving; Profile: \'%s\'.", profileRequestDTO)));
        return new ResponseEntity<>(savedProfileResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        LOGGER.info(String.format("UPDATE :: profile-id:\'%s\'; profile:\'%s\'.", id, profileRequestDTO));
        checkPermission(id);
        Profile profile = profileRequestMapper.dtoToProfile(profileRequestDTO);
        profile.setId(id);
        ProfileResponseDTO profileResponseDTO = profileService
                .update(profile, id)
                .map(profileResponseMapper::profileToDto)
                .orElseThrow(() -> new InternalServiceException(String.format("Exception while updating profile: %s.", profileRequestDTO)));
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProfile(@PathVariable Long id) {
        LOGGER.info(String.format("DELETE :: profile id:\'%s\'.", id));
        this.checkPermission(id);
        boolean profileDeleted = profileService.delete(id);
        return profileDeleted ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    protected <T> void checkPermission(T t) {
        boolean permitted = SecurityContextHolder
                .getThreadLocalAuthorityList()
                .stream().anyMatch(s -> s.equals("ADMIN"))
                || t.equals(SecurityContextHolder.getThreadLocalPrincipalId());
        if (!permitted) {
            throw new PermissionException("Access denied.");
        }
    }
}
