package com.bogdevich.profile.service.impl;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.repository.ProfileRepository;
import com.bogdevich.profile.service.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Profile impl class.
 *
 * @author Eugene Bogdevich
 */
@Service
public class ProfileService implements IProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileService.class);

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(
            @Qualifier(value = "profileRepository") ProfileRepository profileRepository
    ) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public Optional<Profile> save(Profile profile) {
        Profile savedProfile = null;
        try {
            savedProfile = profileRepository.save(profile);
        } catch (Exception ex) {
            LOGGER.error(String.format("Exception wile saving profile – %s.", profile), ex);
        }
        return Optional.ofNullable(savedProfile);
    }

    @Override
    @Transactional
    public Optional<Profile> findOne(Long id) {
        Profile profile = null;
        try {
            profile = profileRepository.findOne(id);
        } catch (Exception ex) {
            LOGGER.error(String.format("Exception wile searching profile – Profile ID = %s.", id), ex);
        }
        return Optional.ofNullable(profile);
    }

    @Override
    @Transactional
    public List<Profile> findAll() {
        List<Profile> profileList = new LinkedList<>();
        try {
            profileList.addAll(profileRepository.findAll());
        } catch (Exception ex) {
            LOGGER.error("Exception while searching profiles: ", ex);
        }
        return profileList;
    }

    @Override
    @Transactional
    public Optional<Page<Profile>> findAll(Pageable pageable) {
        Page<Profile> profilePage = null;
        try {
            profilePage = profileRepository.findAll(pageable);
        } catch (Exception ex) {
            LOGGER.error(
                    String.format(
                            "Exception while searching profiles – page = %s, size = %s",
                            pageable.getPageNumber(),
                            pageable.getPageSize()),
                    ex);
        }
        return Optional.ofNullable(profilePage);
    }

    @Override
    @Transactional
    public Optional<Profile> update(Profile profile) {
        //ToDo: to implement logic of saving.
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) {
        boolean deleted = false;
        try {
            profileRepository.delete(id);
            deleted = true;
        } catch (Exception ex) {
            LOGGER.error(String.format("Exception while deleting profile – id = %s.", id), ex);
        }
        return deleted;
    }
}
