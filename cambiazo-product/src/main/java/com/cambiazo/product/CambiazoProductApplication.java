package com.cambiazo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients(
		basePackages = "com.cambiazo.product.client"
)
@ComponentScan(basePackages = {"com.cambiazo.product", "com.cambiazo.security"})
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
public class CambiazoProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambiazoProductApplication.class, args);
	}

}
