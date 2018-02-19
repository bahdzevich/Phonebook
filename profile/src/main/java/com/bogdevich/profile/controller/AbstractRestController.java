package com.bogdevich.profile.controller;

import com.bogdevich.profile.controller.util.mapper.ProfileMapper;
import com.bogdevich.profile.controller.util.mapper.ProjectMapper;
import com.bogdevich.profile.entity.dto.response.ProfileResponseDTO;
import com.bogdevich.profile.entity.dto.response.ProfilesListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@PropertySources({
        @PropertySource("classpath:security.properties"),
        @PropertySource("classpath:controller.properties")
})
public abstract class AbstractRestController {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractRestController.class);

    @Value("${principal-header-filter.header.principal-id}")
    protected String principalIdHeader;
    @Value("${principal-header-filter.header.principal-name}")
    protected String principalNameHeader;
    @Value("${principal-header-filter.header.principal-authorities}")
    protected String principalAuthoritiesHeader;

    @Value("${controller.paging.default-page}")
    protected Integer DEFAULT_PAGE;
    @Value("${controller.paging.default-size}")
    protected Integer DEFAULT_SIZE;

    protected final ProfileMapper profileMapper;
    protected final ProjectMapper projectMapper;

    @Autowired
    public AbstractRestController(
            ProfileMapper profileMapper,
            ProjectMapper projectMapper) {
        this.profileMapper = profileMapper;
        this.projectMapper = projectMapper;
    }

    protected ProfilesListDTO createProfileListDto(Page<ProfileResponseDTO> profilesPage) {
        ProfilesListDTO profilesListDTO = new ProfilesListDTO();
        profilesListDTO.setItems(profilesPage.getTotalElements());
        profilesListDTO.setPages(profilesPage.getTotalPages());
        profilesListDTO.setProfiles(profilesPage.getContent());
        return profilesListDTO;
    }

    protected <T> T checkParameter(T current, @NotNull T def, Predicate<T> predicate) {
        return Optional
                .ofNullable(current)
                .filter(predicate)
                .orElse(def);
    }
}
