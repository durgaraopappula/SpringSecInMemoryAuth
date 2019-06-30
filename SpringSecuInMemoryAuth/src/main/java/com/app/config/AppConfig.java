package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.app")
@Import(SecuirityConfig.class)
public class AppConfig {
	@Autowired
	private Environment env;
	@Bean
	public InternalResourceViewResolver ivr() {

		InternalResourceViewResolver ir=new  InternalResourceViewResolver();
		ir.setPrefix("/WEB-INF/views/");
		ir.setSuffix(".jsp");
		System.out.println(this);
		return ir;
	}


	@Bean
	public BCryptPasswordEncoder pwd() {
		return new BCryptPasswordEncoder();
	}



}
