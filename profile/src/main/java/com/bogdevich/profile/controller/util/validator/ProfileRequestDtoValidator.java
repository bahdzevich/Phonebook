package com.bogdevich.profile.controller.util.validator;

import com.bogdevich.profile.controller.util.mapper.ProfileRequestMapper;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileRequestDtoValidator implements Validator {

    @Value("profile.exists")
    private String PROFILE_EXISTS;

    private ProfileRepository profileRepository;
    private ProfileRequestMapper profileRequestMapper;

    @Autowired
    public ProfileRequestDtoValidator(
            @Qualifier("profileRepository") ProfileRepository profileRepository,
            ProfileRequestMapper profileRequestMapper
    ) {
        this.profileRepository = profileRepository;
        this.profileRequestMapper = profileRequestMapper;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProfileRequestDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileRequestDTO profileRequestDTO = (ProfileRequestDTO) target;
        Profile profile = profileRequestMapper.dtoToProfile(profileRequestDTO);
        if (profileRepository.exists(Example.of(profile))) {
            errors.reject(PROFILE_EXISTS);
        }
    }
}
