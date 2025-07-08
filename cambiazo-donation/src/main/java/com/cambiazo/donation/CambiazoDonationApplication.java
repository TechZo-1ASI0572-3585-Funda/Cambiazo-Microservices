package com.cambiazo.donation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.cambiazo.donation", "com.cambiazo.security"})
public class CambiazoDonationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambiazoDonationApplication.class, args);
	}

}
