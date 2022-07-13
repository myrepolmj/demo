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
@Table(name = "number_of_reviews")
@EntityListeners(AuditingEntityListener.class)
@IdClass(ReviewAvgIdClass.class)
public class ReviewAvgDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3328687384631247408L;
	@Id
	@Column(name = "review_source")
	private String reviewSource;
	@Id
	@Column(name = "year")
	private Integer year;
	@Id
	@Column(name = "month")
	private Integer month;


	@Column(name = "sum")
	private Double sum;
	@Column(name = "total_num")
	private BigInteger totalNum;

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



	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public BigInteger getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigInteger totalNum) {
		this.totalNum = totalNum;
	}
	
	
	

}