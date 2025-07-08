package com.cambiazo.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.cambiazo.exchange", "com.cambiazo.security"})
public class CambiazoExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambiazoExchangeApplication.class, args);
	}

}
