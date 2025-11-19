package com.formaciondbi.springboot.app.chat;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean("clientRest")
	@LoadBalanced
	public RestTemplate registrarClienteTemplate() {
		return new RestTemplate();
	}
	
	@Bean("mensajeRest")
	@LoadBalanced
	public RestTemplate registrarMensajeTemplate() {
		return new RestTemplate();
	}
}
