package com.marshall.singlestonedemo.contacts.modeling;

/**
 * A first, middle, and last name for a person
 */
public class FullName {

	private String first;
	private String middle;
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
