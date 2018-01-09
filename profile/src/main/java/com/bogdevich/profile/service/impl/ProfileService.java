package com.bogdevich.profile.service.impl;

import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.dto.ProfileDTO;
import com.bogdevich.profile.repository.ProfileRepository;
import com.bogdevich.profile.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Profile service class.
 *
 * @author Eugene Bogdevich
 */
@Service
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(
            @Qualifier(value = "profileRepository") ProfileRepository profileRepository
    ) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<ProfileDTO> save(ProfileDTO profileDTO) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public Optional<ProfileDTO> findOne(Long id) throws Exception {
        Optional<ProfileDTO> dtoOptional;
        Profile profile = Optional.ofNullable(profileRepository.findOne(id))
                .orElseThrow(DataNotFoundException::new);
    }

    @Override
    public List<ProfileDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public Optional<ProfileDTO> update(ProfileDTO profileDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(ProfileDTO profileDTO) throws Exception {

    }
}
