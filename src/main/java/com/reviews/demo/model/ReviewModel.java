package com.reviews.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7306846066841043997L;

	@JsonProperty("review")
	private String review;
	@JsonProperty("author")
	private String author;
	@JsonProperty("review_source")
	private String reviewSource;
	@JsonProperty("rating")
	private String rating;
	@JsonProperty("title")
	private String title;
	@JsonProperty("product_name")
	private String productName;
	@JsonProperty("reviewed_date")
	private Date reviewedDate;
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getReviewedDate() {
		return reviewedDate;
	}
	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(author, productName, rating, review, reviewSource, reviewedDate, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewModel other = (ReviewModel) obj;
		return Objects.equals(author, other.author) && Objects.equals(productName, other.productName)
				&& Objects.equals(rating, other.rating) && Objects.equals(review, other.review)
				&& Objects.equals(reviewSource, other.reviewSource) && Objects.equals(reviewedDate, other.reviewedDate)
				&& Objects.equals(title, other.title);
	}

	
}
