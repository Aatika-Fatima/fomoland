package com.fomo.security.social;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class ReferrerRedirectionAuthenticationSuccessHandler
		extends SimpleUrlAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {
	public ReferrerRedirectionAuthenticationSuccessHandler() {
		super();
		setUseReferer(true);
	}
}
