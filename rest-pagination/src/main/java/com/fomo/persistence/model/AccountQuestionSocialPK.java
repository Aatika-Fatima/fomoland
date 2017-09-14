package com.fomo.persistence.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AccountQuestionSocialPK implements Serializable {


	long id;
	long qid;
	long socialMediaId;
	
	public AccountQuestionSocialPK() {
		// TODO Auto-generated constructor stub
	}
	public AccountQuestionSocialPK(long id, long qid, long socialMediaId) {
		super();
		this.id = id;
		this.qid = qid;
		this.socialMediaId = socialMediaId;
	}
	public final long getId() {
		return id;
	}
	public final void setId(long id) {
		this.id = id;
	}
	public final long getQid() {
		return qid;
	}
	public final void setQid(long qid) {
		this.qid = qid;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (qid ^ (qid >>> 32));
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
		AccountQuestionSocialPK other = (AccountQuestionSocialPK) obj;
		if (id != other.id)
			return false;
		if (qid != other.qid)
			return false;
		if (socialMediaId != other.socialMediaId)
			return false;
		return true;
	}

	 
}
