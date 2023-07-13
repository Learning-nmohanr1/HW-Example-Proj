package com.ford.devsecops.helloworld.security;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {


	/*****************************************************************************************************************
	 * Other - Basic/Public
	 *****************************************************************************************************************/

	@Configuration
	public static class HttpSecurityConfiguration {

		@Bean
		@Order(30)
		public SecurityFilterChain httpSecurityFilterChain(HttpSecurity http) throws Exception {
			http
					.csrf(AbstractHttpConfigurer::disable)
					.authorizeHttpRequests(authorizeHttpRequest -> {
						authorizeHttpRequest.requestMatchers(
								"/",
								"/csrf",
								"/error",
								"/favicon.ico",
								"/health/**",
								"/swagger-ui/**", "/swagger-*",
								"/swagger-resources/**", "/webjars/**", "/v3/api-docs", "/v3/api-docs/**",
								"/api/**"
						).permitAll();
						authorizeHttpRequest.requestMatchers(EndpointRequest.to("info", "health", "refresh")).permitAll();
						authorizeHttpRequest.anyRequest().authenticated();
					})
					.sessionManagement(sessionManagement ->
							sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.httpBasic(withDefaults());

			return http.build();
		}
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(
			SecurityProperties properties, ObjectProvider<PasswordEncoder> passwordEncoder) {
		return new UserDetailsServiceAutoConfiguration().inMemoryUserDetailsManager(properties, passwordEncoder);
	}
}
