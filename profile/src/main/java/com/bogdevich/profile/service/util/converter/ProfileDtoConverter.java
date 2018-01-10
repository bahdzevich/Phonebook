package com.bogdevich.profile.service.util.converter;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.ProfileDTO;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

/**
 * {@link org.modelmapper.ModelMapper} configuration
 * for converting {@link Profile} to {@link ProfileDTO}
 *
 * @author Eugene Bogdevich
 */
public class ProfileDtoConverter extends ConverterConfigurerSupport<Profile, ProfileDTO>{
    @Override
    protected Converter<Profile, ProfileDTO> converter() {
        return new AbstractConverter<Profile, ProfileDTO>() {
            @Override
            protected ProfileDTO convert(Profile profile) {
                //ToDO: AbstractConverter convert implementation for Profile
                throw new UnsupportedOperationException();
            }
        };
    }
}
