package com.accenture.loadgenerator.service;

import com.accenture.loadgenerator.bean.Profile;

import java.util.List;

public interface ProfileService {
    Profile generateRandomProfile();

    List<Profile> generateRandomProfiles(Integer n);
}
