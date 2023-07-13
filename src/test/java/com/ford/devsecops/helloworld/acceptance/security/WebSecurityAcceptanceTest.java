package com.ford.devsecops.helloworld.acceptance.security;

import com.ford.cloudnative.base.test.acceptance.AcceptanceTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

import static com.ford.devsecops.helloworld.TestUtil.get;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WebSecurityAcceptanceTest {

	WebClient webClient;

	@BeforeEach
	void setup() {
		webClient = AcceptanceTestUtil.webClientBuilder().build();
	}

	@Test
	void testUnknownEndpoint_isUnauthorized() {
		assertThrows(WebClientResponseException.Unauthorized.class, () ->
				get(webClient, "/" + UUID.randomUUID().toString(), String.class));
	}
}
