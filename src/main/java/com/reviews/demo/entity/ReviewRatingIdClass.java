package com.reviews.demo.entity;

import java.io.Serializable;

public class ReviewRatingIdClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5063803544957130438L;

	private String reviewSource;

	private String rating;

	public ReviewRatingIdClass() {
		super();
	}

	public ReviewRatingIdClass(String reviewSource, String rating) {
		super();
		this.reviewSource = reviewSource;
		this.rating = rating;
	}

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

	

}