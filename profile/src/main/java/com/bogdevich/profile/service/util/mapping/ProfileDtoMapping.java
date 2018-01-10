package com.bogdevich.profile.service.util.mapping;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.ProfileDTO;
import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * {@link org.modelmapper.ModelMapper} configuration for mapping
 * {@link Profile} and {@link ProfileDTO}
 *
 * @author Eugene Bogdevich
 */
@Component
public class ProfileDtoMapping extends PropertyMapConfigurerSupport<Profile, ProfileDTO> {

    @Override
    public PropertyMap<Profile, ProfileDTO> mapping() {
        return new PropertyMap<Profile, ProfileDTO>() {
            @Override
            protected void configure() {
                //ToDo: PropertyMap mapping implementation for profile
                throw new UnsupportedOperationException();
            }
        };
    }
}
