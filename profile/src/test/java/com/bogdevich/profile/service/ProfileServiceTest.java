package com.bogdevich.profile.service;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.repository.ProfileRepository;
import com.bogdevich.profile.service.impl.ProfileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceTest {

    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Before
    public void setup() {
        profileService = new ProfileService(profileRepository);
    }

    @Test
    public void testFindAll() {
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile());
        profileList.add(new Profile());
        profileList.add(new Profile());
        Mockito.when(profileRepository.findAll()).thenReturn(profileList);
        List<Profile> resultList = profileService.findAll();
        Assert.assertEquals(3, resultList.size());
    }

}
