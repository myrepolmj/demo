package com.reviews.demo.entity;

import java.io.Serializable;

public class ReviewAvgIdClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 517646849383237807L;

	private String reviewSource;

	private Integer year;

	private Integer month;

	public ReviewAvgIdClass() {

	}

	public ReviewAvgIdClass(String reviewSource, Integer year, Integer month) {
		super();
		this.reviewSource = reviewSource;
		this.year = year;
		this.month = month;
	}

	public String getReviewSource() {
		return reviewSource;
	}

	public void setReviewSource(String reviewSource) {
		this.reviewSource = reviewSource;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}