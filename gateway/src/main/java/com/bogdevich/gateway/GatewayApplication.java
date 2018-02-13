package com.bogdevich.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableResourceServer
@EnableFeignClients
public class GatewayApplication {//extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.requestMatchers()
//				.and()
//				.authorizeRequests()
//				.antMatchers("/phonebook/api/profiles/csv/**").hasRole("ADMIN")
//				.antMatchers("/phonebook/api/profiles/**").authenticated()
//				.antMatchers("/phonebook/api/news/**").authenticated();
//	}
}
