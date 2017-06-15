package com.scmspain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Tweet extends BaseEntity {

	@Column(nullable = false)
	private String publisher;
	@Column(name = "body", nullable = false)
	@JsonProperty("tweet")
	private String body;
	@Column(nullable = false, length = 140)
	@JsonIgnore
	private String bodyWithoutLinks;
	@Column(nullable = true)
	private Long pre2015MigrationStatus = 0L;
	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = true)
	@JsonIgnore
	private Date published;
	@Column(nullable = true)
	@JsonIgnore
	private Date discarded;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBodyWithoutLinks() {
		return bodyWithoutLinks;
	}

	public void setBodyWithoutLinks(String bodyWithoutLinks) {
		this.bodyWithoutLinks = bodyWithoutLinks;
	}

	public Long getPre2015MigrationStatus() {
		return pre2015MigrationStatus;
	}

	public void setPre2015MigrationStatus(Long pre2015MigrationStatus) {
		this.pre2015MigrationStatus = pre2015MigrationStatus;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Date getDiscarded() {
		return discarded;
	}

	public void setDiscarded(Date discarded) {
		this.discarded = discarded;
	}

}
