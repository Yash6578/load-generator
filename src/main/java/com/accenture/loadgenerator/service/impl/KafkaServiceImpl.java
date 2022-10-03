package com.accenture.loadgenerator.service.impl;

import com.accenture.loadgenerator.bean.Profile;
import com.accenture.loadgenerator.properties.KafkaProperties;
import com.accenture.loadgenerator.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

    final StreamBridge streamBridge;
    final Map<String, String> topics;

    KafkaServiceImpl(StreamBridge streamBridge, KafkaProperties kafkaProperties) {
        this.streamBridge = streamBridge;
        this.topics = kafkaProperties.getTopics();
    }

    @Override
    public boolean send(Profile profile) {
        boolean isSent = false;
        try {
            streamBridge.send(topics.get(profile.getType()), profile);
            isSent = true;
        } catch (Exception e) {
            log.error("Message not sent " + profile + " " + e.getMessage());
        }

        return isSent;
    }

    @Override
    public boolean send(List<Profile> profileList) {
        boolean isSent = false;
        try {
            profileList.forEach(profile -> {
                streamBridge.send(topics.get(profile.getType()), profile);
            });
            isSent = true;
        }
        catch (Exception e) {
            log.error("Messages not sent " + profileList + " " + e.getMessage());
        }
        return isSent;
    }
}
