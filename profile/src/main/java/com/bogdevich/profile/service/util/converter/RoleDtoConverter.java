package com.bogdevich.profile.service.util.converter;

import com.bogdevich.profile.entity.domain.Role;
import com.bogdevich.profile.entity.dto.response.RoleResponseDTO;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

/**
 * {@link org.modelmapper.ModelMapper} configuration
 * for converting {@link Role} to {@link RoleResponseDTO}
 *
 * @author Eugene Bogdevich
 */
public class RoleDtoConverter extends ConverterConfigurerSupport<Role, RoleResponseDTO> {
    @Override
    protected Converter<Role, RoleResponseDTO> converter() {
        return new AbstractConverter<Role, RoleResponseDTO>() {
            @Override
            protected RoleResponseDTO convert(Role role) {
                //ToDO: AbstractConverter convert implementation for Project
                throw new UnsupportedOperationException();
            }
        };
    }
}