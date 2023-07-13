package com.ford.devsecops.helloworld.acceptance.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ford.cloudnative.base.test.acceptance.AcceptanceTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.ford.devsecops.helloworld.TestUtil.*;
import static com.ford.devsecops.helloworld.TestUtil.getSensitiveActuatorEndpoints;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActuatorsSecurityAcceptanceTest {

	WebClient webClient;

	@BeforeEach
	void setup() {
        webClient = AcceptanceTestUtil.webClientBuilder().build();
	}

    @Test
    void testActuatorHealthEndpointIsAccessible() {
        ResponseEntity<String> response = get(webClient, "/actuator/health", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testActuatorRefreshEndpointIsAccessible() {
        ResponseEntity<String> response = post(webClient, "/actuator/refresh", new ObjectMapper(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testActuatorInfoEndpointIsAccessible() {
        ResponseEntity<String> response = get(webClient, "/actuator/info", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testSensitiveActuatorEndpointsNotAccessible() {
        for (String sensitiveEndpoint : getSensitiveActuatorEndpoints()) {
            assertThrows(WebClientResponseException.Unauthorized.class, () ->
                    get(webClient, sensitiveEndpoint, String.class));
        }
    }
}
