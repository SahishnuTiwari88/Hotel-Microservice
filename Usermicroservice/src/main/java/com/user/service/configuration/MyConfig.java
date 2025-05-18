package com.user.service.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
	
	@Bean
	@LoadBalanced
	//when using load balancing
	//load balance is responsible for distributing loads if there are multiple instances, and it will automatically use
	//service name for working instead of localhost(host) and port number
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
