package com.fomo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.pinterest.api.Pinterest;
 
import org.springframework.social.twitter.api.Twitter;

@Configuration
@EnableSocial
@PropertySource("classpath:/application.properties")
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class SocialConfig {

	@Autowired(required = true)
	public DataSource dataSource;

	@Autowired(required = true)
	Environment env;

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebookTemplate(ConnectionRepository connectionRepository) {
		Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
		return facebookConnection != null ? facebookConnection.getApi() : null;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Twitter twitterTemplate(ConnectionRepository connectionRepository) {
		Connection<Twitter> twitterConnection = connectionRepository.findPrimaryConnection(Twitter.class);
		return twitterConnection != null ? twitterConnection.getApi() : null;
	}

 

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Pinterest pinterestTemplate(ConnectionRepository connectionRepository) {
		Connection<Pinterest> pinterestConnection = connectionRepository.findPrimaryConnection(Pinterest.class);
		return pinterestConnection != null ? pinterestConnection.getApi() : null;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public LinkedIn linkedinTemplate(ConnectionRepository connectionRepository) {
		Connection<LinkedIn> linkedinConnection = connectionRepository.findPrimaryConnection(LinkedIn.class);
		return linkedinConnection != null ? linkedinConnection.getApi() : null;
	}
 
	@Bean
	public DataSourceInitializer databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(
				new ClassPathResource("org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql"));
		populator.setContinueOnError(true); // Continue in case the create
											// script already ran
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDatabasePopulator(populator);
		initializer.setDataSource(dataSource);
		return initializer;
	}

	@Bean
	public UserIdSource userIdSource() {
		return new UserIdSource() {
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
				}
				System.err.println("Authentication " + authentication.getName());
				return authentication.getName();
			}
		};
	}

}
