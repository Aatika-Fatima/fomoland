package com.fomo.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String contactName;
	private String contactNumber;
	
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	public Contact(long id, String contactName, String contactNumber) {
		super();
		this.id = id;
		this.contactName = contactName;
		this.contactNumber = contactNumber;
	}
	public final long getId() {
		return id;
	}
	public final void setId(long id) {
		this.id = id;
	}
	public final String getContactName() {
		return contactName;
	}
	public final void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public final String getContactNumber() {
		return contactNumber;
	}
	public final void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	

}
