package com.bogdevich.profile.controller.util.mapper;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.request.ProfileRequestDTO;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponseDTO profileToDto(Profile profile);
    Profile dtoToProfile(ProfileRequestDTO profileRequestDTO);
}
