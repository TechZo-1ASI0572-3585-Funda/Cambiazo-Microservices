package com.cambiazo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.cambiazo.user", "com.cambiazo.security"})
public class CambiazoUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambiazoUserApplication.class, args);
	}

}
