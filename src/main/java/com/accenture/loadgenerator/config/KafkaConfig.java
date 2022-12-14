package com.accenture.loadgenerator.config;

import com.accenture.contractlibrary.models.Profile;
import com.accenture.loadgenerator.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;


@Configuration
@AllArgsConstructor
public class KafkaConfig {

    final ProfileService profileService;
    /**
    These function is invoked by a default polling mechanism which is provided by the framework.
     It calls the supplier every second.
     */
    @Bean
    public Supplier<Profile> generateOnKafkaTopicIndividual() {
        return () -> {
            Profile profile = profileService.generateRandomProfile();
            profile.setType("individual");
            return profile;
        };
    }
    @Bean
    public Supplier<Profile> generateOnKafkaTopicBusiness() {
        return () -> {
            Profile profile = profileService.generateRandomProfile();
            profile.setType("business");
            return profile;
        };
    }
}
