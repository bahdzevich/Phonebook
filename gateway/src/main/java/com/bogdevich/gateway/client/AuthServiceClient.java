package com.bogdevich.gateway.client;

import feign.HeaderMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phonebook/api/uaa/users/current/info"
    )
    ResponseEntity<Map<String, String>> getPrincipalInfo(@RequestHeader ("Authorization") String token);
}
