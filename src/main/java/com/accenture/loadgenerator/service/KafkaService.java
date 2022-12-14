package com.accenture.loadgenerator.service;

import com.accenture.contractlibrary.models.Profile;

import java.util.List;

public interface KafkaService {
    boolean send(Profile profile);

    boolean send(List<Profile> profileList);
}
