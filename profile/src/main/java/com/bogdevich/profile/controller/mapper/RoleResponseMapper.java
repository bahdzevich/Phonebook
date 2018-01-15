package com.bogdevich.profile.controller.mapper;

import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.entity.dto.response.RoleResponseDTO;
import org.mapstruct.Mapper;

/**
 * Role mapper.
 *
 * @author Eugene Bogdevich
 */
@Mapper(componentModel = "spring")
public interface RoleResponseMapper {

    //RoleResponseMapper INSTANCE = Mappers.getMapper(RoleResponseMapper.class);

    RoleResponseDTO roleToDto (Role role);

    Role dtoToRole (RoleResponseDTO roleResponseDTO);
}
