package com.ford.devsecops.helloworld.security;

import com.ford.cloudnative.base.app.web.exception.handler.ExceptionHandlerConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ExceptionHandlerConfiguration.class)
@AutoConfigureWebTestClient(timeout = "30000")
class WebSecurityConfigurationTest {

	@Autowired
	private WebTestClient webClient;

	/***********************************************************************************************
	 * ENDPOINTS: Swagger
	 ***********************************************************************************************/

	@Test
	void should_allowSpringdocEndpoints_withoutAuthentication() {
		webClient.get().uri("/swagger-ui/index.html").exchange().expectStatus().is2xxSuccessful();
		webClient.get().uri("/v3/api-docs").exchange().expectStatus().is2xxSuccessful();
	}

	/***********************************************************************************************
	 * ENDPOINTS: Other
	 ***********************************************************************************************/

	@Test
	void should_notAllowOtherEndpoints_withoutAuthentication() {
		webClient.get().uri("/other-does-not-exist").exchange().expectStatus().is4xxClientError();
	}
}
