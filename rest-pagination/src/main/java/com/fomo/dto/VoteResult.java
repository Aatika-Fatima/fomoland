package com.fomo.dto;

import java.util.Collection;

public class VoteResult {
	private int totalVotes; 
	private Collection<OptionCount> results;
	/**
	 * 
	 */
	public VoteResult() {
	}
	/**
	 * @return the totalVotes
	 */
	public final int getTotalVotes() {
		return totalVotes;
	}
	/**
	 * @param totalVotes the totalVotes to set
	 */
	public final void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}
	/**
	 * @return the results
	 */
	public final Collection<OptionCount> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public final void setResults(Collection<OptionCount> results) {
		this.results = results;
	}
	
	
}
