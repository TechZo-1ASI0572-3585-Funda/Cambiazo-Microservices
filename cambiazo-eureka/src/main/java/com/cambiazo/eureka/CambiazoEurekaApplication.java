package com.cambiazo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CambiazoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambiazoEurekaApplication.class, args);
	}

}
