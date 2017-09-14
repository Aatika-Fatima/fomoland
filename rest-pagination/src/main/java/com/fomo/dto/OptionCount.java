package com.fomo.dto;

public class OptionCount {
	private long optionId; 
	private int counts;
	/**
	 * @return the optionId
	 */
	public final long getOptionId() {
		return optionId;
	}
	/**
	 * @param optionId the optionId to set
	 */
	public final void setOptionId(long optionId) {
		this.optionId = optionId;
	}
	/**
	 * @return the counts
	 */
	public final int getCounts() {
		return counts;
	}
	/**
	 * @param counts the counts to set
	 */
	public final void setCounts(int counts) {
		this.counts = counts;
	}
	/**
	 * 
	 */
	public OptionCount() {
	}
	
}
