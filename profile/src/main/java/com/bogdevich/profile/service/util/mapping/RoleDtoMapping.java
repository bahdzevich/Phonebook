package com.bogdevich.profile.service.util.mapping;

import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.entity.dto.RoleDTO;
import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * {@link org.modelmapper.ModelMapper} configuration for mapping
 * {@link Role} and {@link RoleDTO}
 *
 * @author Eugene Bogdevich
 */
@Component
public class RoleDtoMapping extends PropertyMapConfigurerSupport<Role, RoleDTO> {

    @Override
    public PropertyMap<Role, RoleDTO> mapping() {
        return new PropertyMap<Role, RoleDTO>() {
            @Override
            protected void configure() {
                //ToDo: PropertyMap mapping implementation for Role
                throw new UnsupportedOperationException();
            }
        };
    }
}
