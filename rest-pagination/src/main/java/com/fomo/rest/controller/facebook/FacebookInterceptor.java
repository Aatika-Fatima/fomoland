package com.fomo.rest.controller.facebook;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ProviderSignInInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

public class FacebookInterceptor implements ProviderSignInInterceptor<Facebook> {

	@Override
	public void postSignIn(Connection<Facebook> arg0, WebRequest arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSignIn(ConnectionFactory<Facebook> arg0, MultiValueMap<String, String> arg1, WebRequest arg2) {
		// TODO Auto-generated method stub
		
	}

 

}
