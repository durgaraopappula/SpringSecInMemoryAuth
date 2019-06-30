package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecuirityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("Ajay").password(pwdEncoder.encode("ajay")).authorities("ADMIN","EMP");
		auth.inMemoryAuthentication().withUser("raju").password(pwdEncoder.encode("raju")).authorities("EMP");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/emp").hasAuthority("EMP")
		.antMatchers("/admin").hasAnyAuthority("ADMIN","EMP")
		.anyRequest().authenticated()


		.and()
		.formLogin()
		.defaultSuccessUrl("/common",true)


		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

		/*.and()
		.exceptionHandling()
		.accessDeniedPage("/denied");*/


	}

}
