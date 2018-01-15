package com.bogdevich.profile.service.util.converter;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

/**
 * {@link org.modelmapper.ModelMapper} configuration
 * for converting {@link Profile} to {@link ProfileResponseDTO}
 *
 * @author Eugene Bogdevich
 */
public class ProfileDtoConverter extends ConverterConfigurerSupport<Profile, ProfileResponseDTO>{
    @Override
    protected Converter<Profile, ProfileResponseDTO> converter() {
        return new AbstractConverter<Profile, ProfileResponseDTO>() {
            @Override
            protected ProfileResponseDTO convert(Profile profile) {
                //ToDO: AbstractConverter convert implementation for Profile
                throw new UnsupportedOperationException();
            }
        };
    }
}
