package com.bogdevich.profile.service.util.converter;

import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.entity.dto.RoleDTO;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

/**
 * {@link org.modelmapper.ModelMapper} configuration
 * for converting {@link Role} to {@link RoleDTO}
 *
 * @author Eugene Bogdevich
 */
public class RoleDtoConverter extends ConverterConfigurerSupport<Role, RoleDTO> {
    @Override
    protected Converter<Role, RoleDTO> converter() {
        return new AbstractConverter<Role, RoleDTO>() {
            @Override
            protected RoleDTO convert(Role role) {
                //ToDO: AbstractConverter convert implementation for Project
                throw new UnsupportedOperationException();
            }
        };
    }
}