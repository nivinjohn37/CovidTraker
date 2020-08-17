package com.javabrains.coronavirustracker.models;

public class LocationStats {
	private String state;
	private String country;
	private int latestTotalCases;
	private int deltaPrev;

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the latestTotalCases
	 */
	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	/**
	 * @param latestTotalCases the latestTotalCases to set
	 */
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	/**
	 * @return the deltaPrev
	 */
	public int getDeltaPrev() {
		return deltaPrev;
	}

	/**
	 * @param deltaPrev the deltaPrev to set
	 */
	public void setDeltaPrev(int deltaPrev) {
		this.deltaPrev = deltaPrev;
	}

}
