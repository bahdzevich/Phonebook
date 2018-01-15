package com.bogdevich.profile.controller.validator;

import com.bogdevich.profile.controller.mapper.ProfileRequestMapper;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfileRequestDtoValidator implements Validator {

    private final String EMAIL_REGEX = "^([A-Za-z0-9][._]?)+[A-Za-z0-9]@([A-Za-z0-9])+((\\.)?[A-Za-z0-9]){2}\\.[a-z]{2,3}$";
    private final String NAME_REGEX = "^([A-Z][a-z]*)(['-][A-Z][a-z]*)*$";
    private final String SKYPE_REGEX = "^([A-Za-z\\d]+)([-. ][A-Za-z\\d]+)*$";
    private final String PHONE_REGEX = "^(\\+375(25|29|33|44)\\d{7})$";

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

/*        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "profile.empty.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "profile.empty.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "profile.empty.lastname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skype", "profile.empty.skype");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "profile.empty.phone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "room", "profile.empty.room");*/

        ProfileRequestDTO profileRequestDTO = (ProfileRequestDTO) target;
        Profile profile = profileRequestMapper.dtoToProfile(profileRequestDTO);
        if (profileRepository.exists(Example.of(profile))) {
            errors.reject("profile.exists");
        }
    }
}
