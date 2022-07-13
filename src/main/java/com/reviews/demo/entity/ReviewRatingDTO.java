package com.reviews.demo.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "rating")
@EntityListeners(AuditingEntityListener.class)
@IdClass(ReviewRatingIdClass.class)
public class ReviewRatingDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 225415994850052368L;
	@Id	
	@Column(name = "review_source")
	private String reviewSource;
	@Id	
	@Column(name = "rating")
	private String rating;
	@Id	
	@Column(name = "count")
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