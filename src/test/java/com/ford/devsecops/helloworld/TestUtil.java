package com.ford.devsecops.helloworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;


public class TestUtil {

    public static <R> ResponseEntity<R> get(WebClient webClient, String urlPath, Class<R> clazz) {
        return webClient.get()
                .uri(urlPath)
                .retrieve()
                .toEntity(clazz)
                .block();
    }

    public static <R> ResponseEntity<R> post(WebClient webClient, String urlPath, Object request, Class<R> clazz) {
        return webClient.post()
                .uri(urlPath)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), request.getClass())
                .retrieve()
                .toEntity(clazz)
                .block();
    }

    public static List<String> getSensitiveActuatorEndpoints() {
        List<String> endpoints = new ArrayList<>();
        endpoints.add("/actuator/actuator");
        endpoints.add("/actuator/auditevents");
        endpoints.add("/actuator/autoconfig");
        endpoints.add("/actuator/beans");
        endpoints.add("/actuator/configprops");
        endpoints.add("/actuator/dump");
        endpoints.add("/actuator/env");
        endpoints.add("/actuator/flyway");
        endpoints.add("/actuator/loggers");
        endpoints.add("/actuator/liquibase");
        endpoints.add("/actuator/metrics");
        endpoints.add("/actuator/mappings");
        endpoints.add("/actuator/shutdown");
        endpoints.add("/actuator/trace");
        return endpoints;
    }

    public static ResultActions jsonGet(MockMvc mockMvc, String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonPost(MockMvc mockMvc, ObjectMapper objectMapper, String url, Object entity) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity))
                )
                .andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonPut(MockMvc mockMvc, ObjectMapper objectMapper, String url, Object entity) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity))
                )
                .andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonDelete(MockMvc mockMvc, String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print());
    }

    public static ResultActions jsonDeleteWithObject(MockMvc mockMvc, ObjectMapper objectMapper, String url, Object entity) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entity))
                )
                .andDo(MockMvcResultHandlers.print());
    }
}