package com.marshall.singlestonedemo.contacts.rest.dto;

import com.marshall.singlestonedemo.contacts.modeling.FullName;

/**
 * Slimmed down representation of a Contact, with just their
 * name and home phone number.
 *
 */
public class CallListItem {

	private FullName name;
	private String phone;
	
	public FullName getName() {
		return name;
	}
	public void setName(FullName name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phoneNumber) {
		this.phone = phoneNumber;
	} 
	
	
}
