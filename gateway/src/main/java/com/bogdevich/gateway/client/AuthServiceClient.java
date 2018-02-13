package com.bogdevich.gateway.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "auth")
public interface AuthServiceClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phonebook/uaa/api/users/current/info"
    )
    ResponseEntity<Map<String, String>> getPrincipalInfo(@RequestHeader ("Authorization") String token);
}
