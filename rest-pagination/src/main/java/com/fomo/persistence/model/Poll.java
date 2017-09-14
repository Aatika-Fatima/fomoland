package com.fomo.persistence.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Poll {
	@Id
	@GeneratedValue
	@Column(name = "POLL_ID")
	private long id;

	@Column(name = "QUESTION")
	@NotEmpty
	private String question;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "POLL_ID")
	@OrderBy
	@Size(min=2, max=4)
	private Set<Option> options;

	/**
	 * @param id
	 * @param question
	 * @param options
	 */
	public Poll(long id, String question, Set<Option> options) {
		this.id = id;
		this.question = question;
		this.options = options;
	}

	/**
	 * 
	 */
	public Poll() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public final long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the question
	 */
	public final String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public final void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the options
	 */
	public final Set<Option> getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public final void setOptions(Set<Option> options) {
		this.options = options;
	}

}
