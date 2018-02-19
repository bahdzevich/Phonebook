package com.bogdevich.profile.controller.util.mapper;

import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.entity.dto.response.RoleResponseDTO;
import org.mapstruct.Mapper;

/**
 * Role mapper.
 *
 * @author Eugene Bogdevich
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    //RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleResponseDTO roleToDto (Role role);

    Role dtoToRole (RoleResponseDTO roleResponseDTO);
}
