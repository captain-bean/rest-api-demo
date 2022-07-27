package com.marshall.singlestonedemo.contacts.persistence.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.marshall.singlestonedemo.contacts.modeling.PhoneNumberType;

/**
 * Set of variables associated with a phone number, provided as
 * an easy way to embed into tables using this information.
 *
 */
@Embeddable
public class PhoneEmbeddable {

	private String number;
	
	@Enumerated(EnumType.STRING)
	private PhoneNumberType type;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneNumberType getType() {
		return type;
	}

	public void setType(PhoneNumberType type) {
		this.type = type;
	}
	
	
}
