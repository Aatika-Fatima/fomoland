package com.fomo.security.social;

import java.sql.PreparedStatement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.pinterest.api.Data;
import org.springframework.social.pinterest.api.Pinterest;
import org.springframework.social.pinterest.api.UserOperations;
import org.springframework.stereotype.Component;

import com.fomo.persistence.dao.AccountRepository;
import com.fomo.persistence.model.Account;

@Component
public class SocialNetworkingSignUp implements ConnectionSignUp {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	public void postConstruct() {
		System.err.println("Account repository created......................");
	}

	private void saveCustomer(Account account) {
		try {
			java.sql.Connection connnection = dataSource.getConnection();
			connnection.setAutoCommit(true);
			String SQL = "insert into Account(username, password, enabled) values (?,?,? )";
			PreparedStatement pst = connnection.prepareStatement(SQL);
			pst.setString(1, account.getUsername());
			pst.setString(2, account.getPassword());
			pst.setBoolean(3, true);
			pst.execute();
			System.err.println("Account created for " + account.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String internalFacebookSignUp(Connection<?> connection) {
		Facebook facebook = (Facebook) connection.getApi();
		User userProfile = facebook.userOperations().getUserProfile();
		String gender = userProfile.getGender();
		if (gender.equals("female")) {
			Account account = new Account();
			account.setId(Long.parseLong(userProfile.getId()));
			account.setUsername(userProfile.getId());
			account.setPassword("secret");
			account.setFirstName(userProfile.getFirstName());
			account.setLastName(userProfile.getLastName());
			account.setEnabled(true);
			account.setPoints(100);
			if (accountRepository != null) {

				accountRepository.save(account);
				System.err.println("Facebook Account created .......via datasource.....................");
			}

			return String.valueOf(account.getId());
		}
		return null;
	}



	@Override
	public String execute(Connection<?> connection) {
		Object object = connection.getApi();
		if (object instanceof Facebook) {
			return internalFacebookSignUp(connection);
		} else {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			System.err.println("Authentication name ==" + authentication.getName());
			Account account = accountRepository.findByUsername(authentication.getName());
			if (!authentication.getName().equals("anonymousUser") && account != null) {
				return authentication.getName();
			}

		}
		return null;

	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	private AccountRepository accountRepository;

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	private String internalPinterestSignup(Connection<?> connection) {
		Pinterest pinterest = (Pinterest) connection.getApi();
		Data<org.springframework.social.pinterest.api.User> userProfile = pinterest.userOperations().getMe();
		org.springframework.social.pinterest.api.User user = userProfile.getData();
		String id = userProfile.getData().getId();
		String gender = "female";
		if (gender.equals("female")) {
			Account account = new Account();
			account.setId(Long.parseLong(id));
			account.setUsername(id);
			account.setPassword("secret");
			account.setFirstName(user.getFirst_name());
			account.setLastName(user.getLast_name());
			account.setEnabled(true);

			if (accountRepository != null) {

				accountRepository.save(account);
				System.err.println("Pinterest Account created .......via datasource.....................");
			}

			return id;
		}
		return null;
	}

}
