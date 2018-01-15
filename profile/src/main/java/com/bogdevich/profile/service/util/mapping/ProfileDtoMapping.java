package com.bogdevich.profile.service.util.mapping;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * {@link org.modelmapper.ModelMapper} configuration for mapping
 * {@link Profile} and {@link ProfileResponseDTO}
 *
 * @author Eugene Bogdevich
 */
@Component
public class ProfileDtoMapping extends PropertyMapConfigurerSupport<Profile, ProfileResponseDTO> {

    @Override
    public PropertyMap<Profile, ProfileResponseDTO> mapping() {
        return new PropertyMap<Profile, ProfileResponseDTO>() {
            @Override
            protected void configure() {
                //ToDo: PropertyMap mapping implementation for profile
                throw new UnsupportedOperationException();
            }
        };
    }
}
