package com.fomo.web.dto;

import com.fomo.persistence.model.ShareType;

public class SocialMediaDto {
	private long socialMediaId;
	private String socialMediaName;
	private int points;
	private boolean shared;
	private ShareType shareType; 
	
	

	public SocialMediaDto(long socialMediaId, String socialMediaName, int points, boolean shared, ShareType shareType) {
		super();
		this.socialMediaId = socialMediaId;
		this.socialMediaName = socialMediaName;
		this.points = points;
		this.shared = shared;
		this.shareType = shareType;
	}

	public final ShareType getShareType() {
		return shareType;
	}

	public final void setShareType(ShareType shareType) {
		this.shareType = shareType;
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

	public final boolean isShared() {
		return shared;
	}

	public final void setShared(boolean shared) {
		this.shared = shared;
	}

	public SocialMediaDto(String socialMediaName, int points, boolean shared) {
		super();

		this.socialMediaName = socialMediaName;
		this.points = points;
		this.shared = shared;
	}

}
