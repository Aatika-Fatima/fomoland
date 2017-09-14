package com.fomo.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vote {
	@Id
	@GeneratedValue
	@Column(name = "VOTE_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "OPTION_ID")
	private Option option;
	
 
	/**
	 * @param id
	 * @param option
	 */
	public Vote(Long id, Option option) {
		this.id = id;
		this.option = option;
	}
	/**
	 * 
	 */
	public Vote() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the option
	 */
	public final Option getOption() {
		return option;
	}
	/**
	 * @param option the option to set
	 */
	public final void setOption(Option option) {
		this.option = option;
	}
	
	
}
