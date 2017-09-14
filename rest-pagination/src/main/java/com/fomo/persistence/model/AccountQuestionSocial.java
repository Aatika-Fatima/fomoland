package com.fomo.persistence.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class AccountQuestionSocial {

	@EmbeddedId
	private AccountQuestionSocialPK id;

	public AccountQuestionSocial() {
		// TODO Auto-generated constructor stub
	}
	

	@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional=false)
	private Account account;

	@JoinColumn(name = "socialMediaId", referencedColumnName = "socialMediaId", insertable = false, updatable = false)
	@ManyToOne(optional=false)
	private SocialMedia socialMedia;

	@JoinColumn(name = "qid", referencedColumnName = "qid", insertable = false, updatable = false)
	@ManyToOne(optional=false)
	private Question question;

	private boolean shared;

	@Enumerated(EnumType.STRING)
	private  ShareType shareType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sharedDate;

	public final ShareType getShareType() {
		return shareType;
	}

	public final void setShareType(ShareType shareType) {
		this.shareType = shareType;
	}

	public AccountQuestionSocial(AccountQuestionSocialPK id, boolean shared, ShareType shareType, Date sharedDate) {
		super();
		this.id = id;
		this.shared = shared;
		this.shareType = shareType;
		this.sharedDate = sharedDate;
	}

	public AccountQuestionSocial(AccountQuestionSocialPK id) {
		super();
		this.id = id;
	}

	public AccountQuestionSocial(int userId, int questionId, int socialMediaId) {
		id = new AccountQuestionSocialPK(userId, questionId, socialMediaId);
	}

	public final AccountQuestionSocialPK getId() {
		return id;
	}

	public final void setId(AccountQuestionSocialPK id) {
		this.id = id;
	}

	public final void setQuestion(Question question) {
		this.question = question;
	}

	public final boolean isShared() {
		return shared;
	}

	public final void setShared(boolean shared) {
		this.shared = shared;
	}

	public final Date getSharedDate() {
		return sharedDate;
	}

	public final void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}

	public final Account getAccount() {
		return account;
	}

	public final void setAccount(Account account) {
		this.account = account;
	}

	public final SocialMedia getSocialMedia() {
		return socialMedia;
	}

	public final void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public final Question getQuestion() {
		return question;
	}

}
