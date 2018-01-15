package com.bogdevich.profile.controller.mapper;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileRequestMapper {
    ProfileRequestDTO profileToDto(Profile profile);
    Profile dtoToProfile(ProfileRequestDTO profileRequestDTO);
}
