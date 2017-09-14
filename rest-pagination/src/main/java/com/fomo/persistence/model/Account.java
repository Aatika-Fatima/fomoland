package com.fomo.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
 
@NamedQueries({@NamedQuery(name="Account.getPointsById",
query="select account.points from Account account where account.id=:id")})
@DynamicUpdate(value=true)
@DynamicInsert(value=true)
public class Account implements Serializable {

	@Id
 	private long id;

	private String username;

	private String password;

	private String firstName;

	private String lastName;
	private boolean enabled;

	private int points;
	@ManyToMany
	private Set<Interest> interests;
 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="account")
	private Set<AccountQuestionSocial> accountQuestionSocial = new HashSet<>() ;
 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="account")
	private Set<AccountContentFeedSocial> accountContentFeedSocial = new HashSet<>();
	
	
	public final Set<AccountContentFeedSocial> getAccountContentFeedSocial() {
		return accountContentFeedSocial;
	}

	public final void setAccountContentFeedSocial(Set<AccountContentFeedSocial> accountContentFeedSocial) {
		this.accountContentFeedSocial = accountContentFeedSocial;
	}

	public final Set<AccountQuestionSocial> getAccountQuestionSocial() {
		return accountQuestionSocial;
	}

	public final void setAccountQuestionSocial(Set<AccountQuestionSocial> accountQuestionSocial) {
		this.accountQuestionSocial = accountQuestionSocial;
	}

	public final int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public final void setPoints(int points) {
		this.points = points;
	}



	public Account(String username, String password, String firstName, String lastName, boolean enabled) {

		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the interests
	 */
	public final Set<Interest> getInterests() {
		return interests;
	}
	public final void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", enabled=" + enabled + ", points=" + points + ", interests=" + interests
				+ ", accountQuestionSocial=" + accountQuestionSocial + "]";
	}
	

}
