package com.fomo.persistence.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class AccountContentFeedSocial {
	@EmbeddedId
	private AccountContentFeedSocialPK pk;

	public AccountContentFeedSocial(AccountContentFeedSocialPK pk) {
		super();
		this.pk = pk;
	}
	
	@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional=false)
	private Account account ; 
	
	@JoinColumn(name = "socialMediaId", referencedColumnName = "socialMediaId", insertable = false, updatable = false)
	@ManyToOne(optional=false)
	private SocialMedia socialMedia; 
	
	@JoinColumn(name = "contentFeedId", referencedColumnName = "contentFeedId", insertable = false, updatable = false)
	@ManyToOne(optional=false)
	private ContentFeed contentFeed;

	private boolean shared;

	@Enumerated(EnumType.STRING)
	private  ShareType shareType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sharedDate;
	
	
	
	public AccountContentFeedSocial() {
		super();
	}



	public AccountContentFeedSocial(AccountContentFeedSocialPK pk, Account account, SocialMedia socialMedia,
			ContentFeed contentFeed) {
		super();
		this.pk = pk;
		this.account = account;
		this.socialMedia = socialMedia;
		this.contentFeed = contentFeed;
	}
	
	

	public AccountContentFeedSocial(AccountContentFeedSocialPK pk, Account account, SocialMedia socialMedia,
			ContentFeed contentFeed, boolean shared, ShareType shareType, Date sharedDate) {
		super();
		this.pk = pk;
		this.account = account;
		this.socialMedia = socialMedia;
		this.contentFeed = contentFeed;
		this.shared = shared;
		this.shareType = shareType;
		this.sharedDate = sharedDate;
	}



	public final AccountContentFeedSocialPK getPk() {
		return pk;
	}

	
	public final boolean isShared() {
		return shared;
	}



	public final void setShared(boolean shared) {
		this.shared = shared;
	}



	public final ShareType getShareType() {
		return shareType;
	}



	public final void setShareType(ShareType shareType) {
		this.shareType = shareType;
	}



	public final Date getSharedDate() {
		return sharedDate;
	}



	public final void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}



	public final void setPk(AccountContentFeedSocialPK pk) {
		this.pk = pk;
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

	public final ContentFeed getContentFeed() {
		return contentFeed;
	}

	public final void setContentFeed(ContentFeed contentFeed) {
		this.contentFeed = contentFeed;
	} 
	
	
}
