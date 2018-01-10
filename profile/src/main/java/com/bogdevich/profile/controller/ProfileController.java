package com.bogdevich.profile.controller;

import com.bogdevich.profile.entity.dto.ProfileDTO;
import com.bogdevich.profile.service.IProfileService;
import com.bogdevich.profile.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for profile endpoint.
 *
 * @author Eugene Bogdevich
 */
@RestController
@RequestMapping("/phonebook/api/profiles")
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IProfileService profileService;
    private final IRoleService roleService;

    @Autowired
    public ProfileController(IProfileService profileService, IRoleService roleService) {
        this.profileService = profileService;
        this.roleService = roleService;
    }

    public ResponseEntity<ProfileDTO> getProfileById(Long id);

    public ResponseEntity<List<ProfileDTO>> getProfiles();

    public ResponseEntity<ProfileDTO> createProfile(ProfileDTO profileDTO);

    public ResponseEntity<ProfileDTO> updateProfile(ProfileDTO profileDTO);

    public ResponseEntity<ProfileDTO> deleteProfile(ProfileDTO profileDTO);

    public ResponseEntity<List<ProfileDTO>> findProfileByQuery();
}
