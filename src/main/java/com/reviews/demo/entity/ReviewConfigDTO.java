package com.reviews.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "config")
@EntityListeners(AuditingEntityListener.class)
public class ReviewConfigDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3328687384631247408L;
	@Id
	@Column(name = "job_id")
	private String jobId;

	@Column(name = "last_run_time")
	private Date lastRunTime;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getLastRunTime() {
		return lastRunTime;
	}

	public void setLastRunTime(Date lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

}