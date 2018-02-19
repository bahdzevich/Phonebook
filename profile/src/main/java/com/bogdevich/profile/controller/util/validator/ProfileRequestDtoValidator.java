package com.bogdevich.profile.controller.util.validator;

import com.bogdevich.profile.controller.util.mapper.ProfileMapper;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.repository.ProfileRepository;
import com.bogdevich.profile.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileRequestDtoValidator implements Validator {

    private IProfileService profileService;
    private ProfileMapper profileMapper;

    @Autowired
    public ProfileRequestDtoValidator(
            IProfileService profileService,
            ProfileMapper profileMapper
    ) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProfileRequestDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileRequestDTO profileRequestDTO = (ProfileRequestDTO) target;
        Profile profile = profileMapper.dtoToProfile(profileRequestDTO);
        if (profileService.exists(profile)) {
            errors.reject("Profile already exists");
        }
    }
}
