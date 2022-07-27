package com.marshall.singlestonedemo.contacts.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Set of variables associated with a full name, provided as
 * an easy way to embed into tables using this information.
 *
 */
@Embeddable
public class FullNameEmbeddable {

	@Column(name = "first_name")
	private String first;
	
	@Column(name = "middle_name")
	private String middle;
	
	@Column(name = "last_name")
	private String last;
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String firstName) {
		this.first = firstName;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middleName) {
		this.middle = middleName;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String lastName) {
		this.last = lastName;
	}
	
	
}
