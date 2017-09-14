package com.fomo.security.social;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.context.request.NativeWebRequest;

//@Component
public class SocialNetworkingSignInAdapter implements SignInAdapter {

	final RequestCache requestCache;

	// @Autowired
	public SocialNetworkingSignInAdapter(RequestCache requestCache) {
		super();
		this.requestCache = requestCache;
		if (requestCache != null) {
			System.out.println("ceatead..request cache");
		}
	}

	@Override
	public String signIn(String username, Connection<?> connection, NativeWebRequest request) {
		System.err.println("SocialNetworkingSignInAdapter....................    ");

		System.err.println(connection.getDisplayName());
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null,
				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		String redirectUrl = null;
		
		try {
			redirectUrl = nativeReq.getSession().getAttribute("redirectUrl").toString();
			System.err.println("---------------" + redirectUrl+"*****************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (connection.getApi() instanceof Facebook) ? "/howPointsWorks" : redirectUrl;
		// return "/howPointsWorks";
	}

	private String extractOriginalUrl(NativeWebRequest request) {

		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);

		if (nativeReq.getSession() != null) {
			SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
			System.err.println(saved.getRedirectUrl());
		}

		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return saved.getRedirectUrl();
	}

	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
