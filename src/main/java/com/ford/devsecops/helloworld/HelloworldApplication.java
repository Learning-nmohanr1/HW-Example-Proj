package com.ford.devsecops.helloworld;

import com.ford.cloudnative.annotations.EnableFordSecurityTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
@EnableFordSecurityTools
public class HelloworldApplication {

	public static void main(String[] args) {
		Hooks.enableAutomaticContextPropagation();
		SpringApplication.run(HelloworldApplication.class, args);
	}
}
