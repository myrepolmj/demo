package com.reviews.demo.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReviewAvgModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7306846066841043997L;

	@JsonIgnore
	private String reviewSource;
	private Integer year;
	private Integer month;
	private Double rating;
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
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getReviewSource() {
		return reviewSource;
	}
	public void setReviewSource(String reviewSource) {
		this.reviewSource = reviewSource;
	}
	@Override
	public int hashCode() {
		return Objects.hash(month, rating, reviewSource, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewAvgModel other = (ReviewAvgModel) obj;
		return Objects.equals(month, other.month) && Objects.equals(rating, other.rating)
				&& Objects.equals(reviewSource, other.reviewSource) && Objects.equals(year, other.year);
	}
	
	
	

	
}
