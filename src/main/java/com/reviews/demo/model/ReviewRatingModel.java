package com.reviews.demo.model;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ReviewRatingModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 225415994850052368L;
	
	@JsonIgnore
	private String reviewSource;
	
	private String rating;
	
	private BigInteger count;
	
	public String getReviewSource() {
		return reviewSource;
	}
	public void setReviewSource(String reviewSource) {
		this.reviewSource = reviewSource;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	
	
	

}