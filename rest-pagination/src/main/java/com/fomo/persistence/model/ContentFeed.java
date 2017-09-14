package com.fomo.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.sql.rowset.serial.SerialBlob;

@SuppressWarnings("serial")
@Entity
public class ContentFeed implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long contentFeedId; 
	private SerialBlob image; 
	private String imageUrl;
	private String description; 
	private int points;
 
	@JoinColumn(name="interestId", referencedColumnName="interestId")
	@ManyToOne
	Interest interest;

	
	public ContentFeed() {
		// TODO Auto-generated constructor stub
	}

	public ContentFeed(Long id, SerialBlob image, String imageUrl, String description, int points, Interest interest) {
		super();
		this.contentFeedId = id;
		this.image = image;
		this.imageUrl = imageUrl;
		this.description = description;
		this.points = points;
		this.interest = interest;
	}

	public final Long getId() {
		return contentFeedId;
	}

	public final void setId(Long id) {
		this.contentFeedId = id;
	}

	public final SerialBlob getImage() {
		return image;
	}

	public final void setImage(SerialBlob image) {
		this.image = image;
	}

	public final String getImageUrl() {
		return imageUrl;
	}

	public final void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final int getPoints() {
		return points;
	}

	public final void setPoints(int points) {
		this.points = points;
	}

	public final Interest getInterest() {
		return interest;
	}

	public final void setInterest(Interest interest) {
		this.interest = interest;
	}

	@Override
	public String toString() {
		return "ContentFeed [contentFeedId=" + contentFeedId + ", image=" + image + ", imageUrl=" + imageUrl
				+ ", description=" + description + ", points=" + points + ", interest=" + interest + "]";
	}
 
	
}
