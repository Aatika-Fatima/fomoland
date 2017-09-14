package com.fomo.persistence.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AccountContentFeedSocialPK implements Serializable{
	long id;
	long contentFeedId;
	long socialMediaId;
	
	
	public AccountContentFeedSocialPK() {
		// TODO Auto-generated constructor stub
	}
	
	public AccountContentFeedSocialPK(long id, long contentFeedId, long socialMediaId) {
		super();
		this.id = id;
		this.contentFeedId = contentFeedId;
		this.socialMediaId = socialMediaId;
	}
	public final long getId() {
		return id;
	}
	
	public final void setId(long id) {
		this.id = id;
	}
	public final long getContentFeedId() {
		return contentFeedId;
	}
	public final void setContentFeedId(long contentFeedId) {
		this.contentFeedId = contentFeedId;
	}
	public final long getSocialMediaId() {
		return socialMediaId;
	}
	public final void setSocialMediaId(long socialMediaId) {
		this.socialMediaId = socialMediaId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (contentFeedId ^ (contentFeedId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (socialMediaId ^ (socialMediaId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountContentFeedSocialPK other = (AccountContentFeedSocialPK) obj;
		if (contentFeedId != other.contentFeedId)
			return false;
		if (id != other.id)
			return false;
		if (socialMediaId != other.socialMediaId)
			return false;
		return true;
	}
	
	
}
