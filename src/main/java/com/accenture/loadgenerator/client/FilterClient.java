package com.accenture.loadgenerator.client;

import com.accenture.contractlibrary.models.Profile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Client-Filter", url = "http://localhost:8080/")
public interface FilterClient {
    @PostMapping("/profile/filter")
    ResponseEntity<String> filter(@RequestBody Profile profile);
}
