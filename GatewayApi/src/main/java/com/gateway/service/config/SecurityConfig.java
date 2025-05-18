package com.gateway.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity // b/c Spring Gateway dependency uses web flux dependency so we use web flux security 
public class SecurityConfig {
//we are using new technique so we have to make a bean, and that bean is exactly 'security web filter chain'(for flux)
	//and normally(security filter chain)
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity
		.authorizeExchange()
		.anyExchange()
		.authenticated()
		.and()
		.oauth2Login()
		.and()
		.oauth2ResourceServer()
		.jwt();
		
		return httpSecurity.build();
		
		//security configuration is done now we have to make login url for that we make 
		//a controller for the login purpose
		
	}
	
}
