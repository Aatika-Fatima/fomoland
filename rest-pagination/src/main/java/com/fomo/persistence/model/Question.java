package com.fomo.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

@Entity
public class Question  implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long qid;
	@Column(name = "QUESTION_TEXT")
	@NotNull
	private String qText;

	@NotNull
	@Column(name = "OPTION_A" )
	private String optA;
	@NotNull
	@Column(name = "OPTION_B" )
	private String optB;
	@NotNull
	@Column(name = "OPTION_C" )
	private String optC;
	@NotNull
	@Column(name = "OPTION_D" )
	private String optD;

	@Column(name = "ANSWER")
	private String answer;
	@NotNull
	private String notes;

	@NotNull
	private String link;

	@NotNull
	private int points;

	@ManyToOne
	@JoinColumn(name = "INTEREST_ID")
	Interest interest;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="question")
	private Set<AccountQuestionSocial> accountQuestionSocial = new HashSet<>();
	
	
	public final Set<AccountQuestionSocial> getAccountQuestionSocial() {
		return accountQuestionSocial;
	}

	public final void setAccountQuestionSocial(Set<AccountQuestionSocial> accountQuestionSocial) {
		this.accountQuestionSocial = accountQuestionSocial;
	}

	public Question() {
		super();
	}

	public final long getQid() {
		return qid;
	}

	public final void setQid(long qid) {
		this.qid = qid;
	}

	public final String getqText() {
		return qText;
	}

	public final void setqText(String qText) {
		this.qText = qText;
	}

	public final String getOptA() {
		return optA;
	}

	public final void setOptA(String optA) {
		this.optA = optA;
	}

	public final String getOptB() {
		return optB;
	}

	public final void setOptB(String optB) {
		this.optB = optB;
	}

	public final String getOptC() {
		return optC;
	}

	public final void setOptC(String optC) {
		this.optC = optC;
	}

	public final String getOptD() {
		return optD;
	}

	public final void setOptD(String optD) {
		this.optD = optD;
	}

	public final String getAnswer() {
		return answer;
	}

	public final void setAnswer(String answer) {
		this.answer = answer;
	}

	public final String getNotes() {
		return notes;
	}

	public final void setNotes(String notes) {
		this.notes = notes;
	}

	public final String getLink() {
		return link;
	}

	public final void setLink(String link) {
		this.link = link;
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
		return "Question [qid=" + qid + ", qText=" + qText + ", optA=" + optA + ", optB=" + optB + ", optC=" + optC
				+ ", optD=" + optD + ", answer=" + answer + ", notes=" + notes + ", link=" + link + ", points=" + points
				+ ", interest=" + interest + ", accountQuestionSocial=" + accountQuestionSocial + "]";
	}
 
}
