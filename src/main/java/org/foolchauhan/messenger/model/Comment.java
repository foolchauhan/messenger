package org.foolchauhan.messenger.model;

import java.util.Date;

public class Comment {
	private long id;
	private String message;
	private Date creationDate;
	private String author;
	
	public Comment() {
		super();
	}

	public Comment(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.creationDate = new Date();
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
}
