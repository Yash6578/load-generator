package com.accenture.loadgenerator.rest;

import com.accenture.loadgenerator.bean.Profile;
import com.accenture.loadgenerator.client.FilterClient;
import com.accenture.loadgenerator.service.KafkaService;
import com.accenture.loadgenerator.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProducerController {
    final KafkaService kafkaService;
    final FilterClient filterClient;
    final ProfileService profileService;

    @PostMapping("/kafka")
    ResponseEntity<String> produceDataToKafka(@RequestBody Profile profile) {
        if(kafkaService.send(profile))
            return ResponseEntity.ok("Profile send to kafka " + profile);
        return ResponseEntity.badRequest().body("Something went wrong not able to sent message to kafka");
    }

    @PostMapping("/rest")
    ResponseEntity<String> produceDataToClientFilterApi(@RequestBody Profile profile) {
        return filterClient.filter(profile);
    }

    @GetMapping("/generate/random/kafka/{n}")
    ResponseEntity<String> produceRandomProfileToKafka(@PathVariable Integer n) {
        List<Profile> profileList = profileService.generateRandomProfiles(n);
        boolean status = kafkaService.send(profileList);

        if(status)
            return ResponseEntity.ok("Profiles Sent to Kafka " + profileList);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

    @GetMapping("/generate/random/rest/{n}")
    ResponseEntity<String> produceRandomProfileToClientFilterApi(@PathVariable Integer n) {
        List<Profile> profileList = profileService.generateRandomProfiles(n);

        profileList.forEach(filterClient::filter);

        return ResponseEntity.ok("Profiles sent to Client-Filter " + profileList);
    }
}
