package com.fomo.security.social;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

public class SocialNetworkingUsersDetailsService implements SocialUserDetailsService {

	@PostConstruct
	public void postConstruct() {
		System.err.println("SocialNetworkingUsersDetailsService...........");
	}

	private UserDetailsService userDetailsService;

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		System.err.println("user loaded........................");
		SocialUser socialUser = new SocialUser(userDetails.getUsername(), userDetails.getPassword(),
				userDetails.getAuthorities());
		return socialUser;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
