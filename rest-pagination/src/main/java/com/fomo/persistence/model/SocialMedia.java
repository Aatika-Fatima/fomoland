package com.fomo.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class SocialMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long socialMediaId;

	@Column(name = "SOCIAL_MEDIA_NAME", length = 30)
	@NotNull
	private String socialMediaName;

	@Column(name = "POINTS", length = 4)
	private int points;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="socialMedia")
	private Set<AccountQuestionSocial> accountQuestionSocial = new HashSet<>();

	public final Set<AccountQuestionSocial> getAccountQuestionSocial() {
		return accountQuestionSocial;
	}

	public final void setAccountQuestionSocial(Set<AccountQuestionSocial> accountQuestionSocial) {
		this.accountQuestionSocial = accountQuestionSocial;
	}

	public SocialMedia(int socialMediaId, String socialMediaName, int points) {
		super();
		this.socialMediaId = socialMediaId;
		this.socialMediaName = socialMediaName;
		this.points = points;
	}

	public SocialMedia() {
		super();
	}

	public final long getSocialMediaId() {
		return socialMediaId;
	}

	public final void setSocialMediaId(long socialMediaId) {
		this.socialMediaId = socialMediaId;
	}

	public final String getSocialMediaName() {
		return socialMediaName;
	}

	public final void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}

	public final int getPoints() {
		return points;
	}

	public final void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "SocialMedia [socialMediaId=" + socialMediaId + ", socialMediaName=" + socialMediaName + ", points="
				+ points + ", accountQuestionSocial=" + accountQuestionSocial + "]";
	}

 

}
