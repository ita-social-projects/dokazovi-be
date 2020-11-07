package com.softserveinc.dokazovi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.softserveinc.dokazovi.controller.EndPoints.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// TODO: add other pages to antMatchers() from EndPoints;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", REGISTRATION).permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage(LOGIN)
				.permitAll()
				.and()
				.logout()
				.permitAll();
	}

}
