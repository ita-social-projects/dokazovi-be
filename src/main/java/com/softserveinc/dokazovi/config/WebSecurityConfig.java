package com.softserveinc.dokazovi.config;

import com.softserveinc.dokazovi.security.CustomUserDetailsService;
import com.softserveinc.dokazovi.security.RestAuthenticationEntryPoint;
import com.softserveinc.dokazovi.security.TokenAuthenticationFilter;
import com.softserveinc.dokazovi.security.oauth2.CustomOAuth2UserService;
import com.softserveinc.dokazovi.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.softserveinc.dokazovi.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.softserveinc.dokazovi.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomUserDetailsService customUserDetailsService;


	private CustomOAuth2UserService customOAuth2UserService;


	private OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler;


	private OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler;

	@Autowired
	public WebSecurityConfig(CustomUserDetailsService customUserDetailsService,
			CustomOAuth2UserService customOAuth2UserService,
			OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler,
			OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.customOAuth2UserService = customOAuth2UserService;
		this.oauth2AuthenticationSuccessHandler = oauth2AuthenticationSuccessHandler;
		this.oauth2AuthenticationFailureHandler = oauth2AuthenticationFailureHandler;
	}

	public WebSecurityConfig(boolean disableDefaults, CustomUserDetailsService customUserDetailsService,
			CustomOAuth2UserService customOAuth2UserService,
			OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler,
			OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler) {
		super(disableDefaults);
		this.customUserDetailsService = customUserDetailsService;
		this.customOAuth2UserService = customOAuth2UserService;
		this.oauth2AuthenticationSuccessHandler = oauth2AuthenticationSuccessHandler;
		this.oauth2AuthenticationFailureHandler = oauth2AuthenticationFailureHandler;
	}

	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors()
					.and()
				.sessionManagement()
				.sessionCreationPolicy(STATELESS)
					.and()
				.csrf()
					.disable()
				.formLogin()
					.disable()
				.httpBasic()
					.disable()
				.logout()
					.disable()
				.exceptionHandling()
					.authenticationEntryPoint(new RestAuthenticationEntryPoint())
					.and()
				.authorizeRequests()
					.antMatchers(
						"/error",
						"/favicon.ico",
						"/**/*.png",
						"/**/*.gif",
						"/**/*.svg",
						"/**/*.jpg",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js")
						.permitAll()
				.antMatchers("/v3/api-docs/**", "/configuration/**", "/swagger*/**", "/webjars/**",
						"/auth/**", "/oauth2/**")
					.permitAll()
				.antMatchers("/user/**", "/post/**", "/tag/**", "/direction/**", "/region/**")
					.permitAll()
				.anyRequest()
					.authenticated()
					.and()
				.oauth2Login()
					.authorizationEndpoint()
						.baseUri("/oauth2/authorize")
						.authorizationRequestRepository(cookieAuthorizationRequestRepository())
					.and()
						.redirectionEndpoint()
						.baseUri("/login/oauth2/code/*")
					.and()
						.userInfoEndpoint()
						.userService(customOAuth2UserService)
					.and()
				.successHandler(oauth2AuthenticationSuccessHandler)
				.failureHandler(oauth2AuthenticationFailureHandler);

		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.userDetailsService(customUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}
}
