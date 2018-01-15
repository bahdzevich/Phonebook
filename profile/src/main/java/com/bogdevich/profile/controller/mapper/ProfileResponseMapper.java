package com.bogdevich.profile.controller.mapper;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import org.mapstruct.Mapper;

/**
 * {@link ProfileResponseDTO} mapper.
 *
 * @author Eugene Bogdevich
 */
@Mapper(componentModel = "spring")
public interface ProfileResponseMapper {

    ProfileResponseDTO profileToDto(Profile profile);
    Profile dtoToProfile(ProfileResponseDTO profileResponseDTO);
}
