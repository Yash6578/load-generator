package com.accenture.loadgenerator.service;


import com.accenture.contractlibrary.models.Profile;

import java.util.List;

public interface ProfileService {
    Profile generateRandomProfile();

    List<Profile> generateRandomProfiles(Long n);
}
