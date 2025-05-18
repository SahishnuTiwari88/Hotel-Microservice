package com.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MicroservicesApplication {

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
	//removed from main app and placed in configuration file
	
	public static void main(String[] args) {
		
		SpringApplication.run(MicroservicesApplication.class, args);
	}

}
