package com.fomo.signup;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.pinterest.api.Data;
import org.springframework.social.pinterest.api.Pinterest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.fomo.exception.UsernameAlreadyInUseException;
import com.fomo.persistence.dao.AccountRepository;
import com.fomo.persistence.model.Account;

@Controller
public class SignupController {

	private final AccountRepository accountRepository;
	private final ProviderSignInUtils providerSignInUtils;

	@Inject
	public SignupController(AccountRepository accountRepository,
			ConnectionFactoryLocator connectionFactoryLocator,
			UsersConnectionRepository connectionRepository) {
		this.accountRepository = accountRepository;
		this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(WebRequest request) {

		// public String signup(@Valid SignupForm form, BindingResult
		// formBinding, WebRequest request) {
		/*
		 * if (formBinding.hasErrors()) { return null; }
		 */
		Account account = null;
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		SignUpForm form = SignUpForm.fromProviderUser(connection.fetchUserProfile());
		Object object = connection.getApi();

		if (connection != null && (object instanceof Facebook || object instanceof Pinterest)) {
			if (object instanceof Facebook) {
				Facebook facebook = (Facebook) connection.getApi();
				String id = facebook.userOperations().getUserProfile().getId();
				System.err.println("Connection instance of facebook----------------->" + id + "===" + form);
				account = createAccount(form, id);
			} 
			if (account != null) {
				SignInUtils.signin(account.getUsername());
				providerSignInUtils.doPostSignUp(account.getUsername(), request);
				return "howPointsWorks";
			} else if (account == null) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				account = accountRepository.findOne(Long.parseLong(authentication.getName()));
				providerSignInUtils.doPostSignUp(String.valueOf(account.getId()), request);
				return "redirect:/";
			}

		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			System.err.println("Authentication name ==" + authentication.getName());
			if (!authentication.getName().equals("anonymousUser") && authentication != null) {
				account = accountRepository.findOne(Long.parseLong(authentication.getName()));
				providerSignInUtils.doPostSignUp(String.valueOf(account.getId()), request);
				return "/";
			}
		}
		System.err.println("Redirecting to same page");
		return "redirect:/";

	}

	// internal helpers

	private Account createAccount(SignUpForm form, String id) {
		try {
			Account account = new Account();
			account.setId(Long.parseLong(id));
			account.setFirstName(form.getFirstName());
			account.setLastName(form.getLastName());
			account.setPassword("secret");
			accountRepository.save(account);
			return account;
		} catch (UsernameAlreadyInUseException e) {
			// formBinding.rejectValue("username", "user.duplicateUsername",
			// "already in use");

			return null;
		}
	}

}
