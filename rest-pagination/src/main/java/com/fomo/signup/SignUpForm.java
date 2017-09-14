package com.fomo.signup;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

public class SignUpForm {
	@NotEmpty
	private String username;

	@Size(min = 6, message = "must be at least 6 characters")
	private String password;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	/**
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public static SignUpForm fromProviderUser(UserProfile providerUser){
		SignUpForm signUpForm = new SignUpForm(); 
		System.err.println("Sign up form ----------->"+providerUser.getUsername());
		signUpForm.setUsername(providerUser.getUsername());
		signUpForm.setFirstName(providerUser.getFirstName());
		signUpForm.setLastName(providerUser.getLastName());
		return signUpForm;
		
	}
}
