package com.fomo.rest.integration.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.util.Assert;

import com.fomo.security.social.SocialNetworkingUsersDetailsService;

public class WithMockCustomUserSecurityContext implements 
WithSecurityContextFactory<WithUserDetails>{

	@Autowired
	private SocialNetworkingUsersDetailsService userDetails; 
	

	@Override
	public SecurityContext createSecurityContext(WithUserDetails withUser) {
		String username = withUser.value();
		Assert.hasLength(username, "value() must not be empty string");
		
		UserDetails principal = userDetails.loadUserByUserId(username);
		Authentication authentication= 
				new UsernamePasswordAuthenticationToken(principal,
						principal.getPassword(), principal.getAuthorities());
		SecurityContext context = SecurityContextHolder.createEmptyContext(); 
		context.setAuthentication(authentication);
 		return context;
	}

}
 
