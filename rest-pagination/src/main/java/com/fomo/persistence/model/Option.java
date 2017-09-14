package com.fomo.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OPTIONS")
public class Option {
	@Id
	@GeneratedValue
	@Column(name="OPTION_ID")
	private long id; 
	
	@Column(name="OPTION_VALUE")
	private String value;
 
	public Option() {
		// TODO Auto-generated constructor stub
	}
 
	public final long getId() {
		return id;
	}
 
	public final void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param id
	 * @param value
	 */
	public Option(long id, String value) {
		this.id = id;
		this.value = value;
	}
	
	
	
	
	
	
	
}
