package org.foolchauhan.messenger.model;

import java.util.Date;

public class Profile {
	private long id;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date creation_date;
	
	
	public Profile() {
		super();
	}
	public Profile(long id, String profileName, String firstName, String lastName, Date creation_date) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creation_date = creation_date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	
	
	
}
