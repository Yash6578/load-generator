package com.accenture.loadgenerator.rest;

import com.accenture.contractlibrary.api.GenerateApi;
import com.accenture.contractlibrary.models.Profile;
import com.accenture.loadgenerator.client.FilterClient;
import com.accenture.loadgenerator.service.KafkaService;
import com.accenture.loadgenerator.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProducerController implements GenerateApi {
    final KafkaService kafkaService;
    final FilterClient filterClient;
    final ProfileService profileService;

    @Override
    public ResponseEntity<String> produceDataToClientFilterApi(@Valid Profile profile) {
        return filterClient.filter(profile);
    }

    @Override
    public ResponseEntity<String> produceDataToKafka(@Valid Profile profile) {
        if(kafkaService.send(profile))
            return ResponseEntity.ok("Profile send to kafka " + profile);
        return ResponseEntity.badRequest().body("Something went wrong not able to sent message to kafka");
    }

    @Override
    public ResponseEntity<String> produceRandomProfileToClientFilterApi(Long n) {
        List<Profile> profileList = profileService.generateRandomProfiles(n);

        profileList.forEach(filterClient::filter);

        return ResponseEntity.ok("Profiles sent to Client-Filter " + profileList);
    }

    @Override
    public ResponseEntity<String> produceRandomProfileToKafka(Long n) {
        List<Profile> profileList = profileService.generateRandomProfiles(n);
        boolean status = kafkaService.send(profileList);

        if(status)
            return ResponseEntity.ok("Profiles Sent to Kafka " + profileList);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }
}
