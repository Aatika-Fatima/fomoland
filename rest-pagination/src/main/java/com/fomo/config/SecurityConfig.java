package com.fomo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInInterceptor;
import org.springframework.social.security.SpringSocialConfigurer;

import com.fomo.rest.client.CustomFilter;
import com.fomo.rest.client.RestAuthenticationEntryPoint;
import com.fomo.rest.controller.facebook.FacebookInterceptor;
import com.fomo.security.social.ReferrerRedirectionAuthenticationSuccessHandler;
import com.fomo.security.social.SocialNetworkingSignInAdapter;
import com.fomo.security.social.SocialNetworkingSignUp;
import com.fomo.security.social.SocialNetworkingUsersDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.fomo.security.social" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private DataSource dataSource;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	SocialNetworkingSignInAdapter signInAdapter;

	@Bean
	UserDetailsService x() {
		return userDetailsService();
	}

	@Bean
	SocialNetworkingUsersDetailsService getSocialNetworkingUserDeta() {
		SocialNetworkingUsersDetailsService c = new SocialNetworkingUsersDetailsService();
		c.setUserDetailsService(x());
		return c;
	}

	@Bean
	@Primary
	public SocialNetworkingSignInAdapter socialNetworkingSignInAdapter() {
		return new SocialNetworkingSignInAdapter(getHttpSessionRequestCache());
	}

	@Bean
	public HttpSessionRequestCache getHttpSessionRequestCache() {
		HttpSessionRequestCache h = new HttpSessionRequestCache();
		h.setCreateSessionAllowed(true);
		return h ;
	}

	@Autowired(required = true)
	SocialNetworkingSignUp signUp;

	@Autowired
	ConnectionFactoryLocator connectionFactoryLocator;

	@Bean
	@Primary
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public UsersConnectionRepository getUsersConnectionRepository() {
		JdbcUsersConnectionRepository j = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator,
				Encryptors.noOpText());
		j.setConnectionSignUp(signUp);
		return j;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/img/**", "/fonts/**");

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("aatika").password("secret").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
	// @formatter:off
		 	 http
		 	 	.requestCache().requestCache(getHttpSessionRequestCache())
		 	 	
		 	 	.and()
		 	    .formLogin()
		 	 	.loginPage("/signin")
				.loginProcessingUrl("/signin/authenticate")
				.failureUrl("/signin?param.error=bad_credentials")
				 .successHandler(new ReferrerRedirectionAuthenticationSuccessHandler())

				.and().logout()
				.logoutUrl("/signout")
				.deleteCookies("JSESSIONID")

				.and()

				.authorizeRequests()
				.antMatchers("/","/admin/**", "/favicon.ico", "/resources/**", "/auth/**", "/signin/**", "/signup/**",
						"/disconnect/facebook", "/polls/**", "/api/api-docs/**", "/api-docs/**", "/contacts")
				.permitAll()
				
				.antMatchers("/rest/v1/**", "/twitter/**", "/web/**").hasRole("USER")
			 
				.and()
				.rememberMe().
			 	 key("rememberMeKey").alwaysRemember(true)
				
				 
				.and()
 				.apply(new SpringSocialConfigurer());
	 
  
//@formatter:on

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource);
	}

	@Bean
	ProviderSignInController providerSignController() {
		ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator,
				getUsersConnectionRepository(), socialNetworkingSignInAdapter());
		providerSignInController.setSignInUrl("/signin");
		providerSignInController.setPostSignInUrl("/");
		//providerSignInController.addSignInInterceptor((ProviderSignInInterceptor<?>) new FacebookInterceptor());
		return providerSignInController;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
