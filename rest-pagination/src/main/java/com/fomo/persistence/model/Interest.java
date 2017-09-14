package com.fomo.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Interest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long interestId;;
	private String interestName;

	public Interest() {
		// TODO Auto-generated constructor stub
	}

	public final long getInterestId() {
		return interestId;
	}

	public final void setInterestId(long interestId) {
		this.interestId = interestId;
	}

	public final String getInterestName() {
		return interestName;
	}

	public final void setInterestName(String interestName) {
		this.interestName = interestName;
	}

	@Override
	public String toString() {
		return "Interest [interestId=" + interestId + ", interestName=" + interestName + "]";
	}
	
}
