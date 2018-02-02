package com.bogdevich.profile.controller;

import com.bogdevich.profile.controller.util.mapper.ProfileResponseMapper;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.service.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides with opportunity load/get profiles in CSV format.
 */
@Controller
public class DocumentExportController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IProfileService profileService;
    private final ProfileResponseMapper profileResponseMapper;

    @Autowired
    public DocumentExportController(
            IProfileService profileService,
            ProfileResponseMapper profileResponseMapper) {
        this.profileService = profileService;
        this.profileResponseMapper = profileResponseMapper;
    }

    @RequestMapping( value = "/csv", method = RequestMethod.GET)
    public void exportProfiles(Model model) throws IOException {
        List<ProfileResponseDTO> profileResponseDTOList = profileService
                .findAll().stream()
                .map(profileResponseMapper::profileToDto)
                .collect(Collectors.toList());
        model.addAttribute("profiles", profileResponseDTOList);
    }

}
