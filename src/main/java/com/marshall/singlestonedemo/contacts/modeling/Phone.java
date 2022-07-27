package com.marshall.singlestonedemo.contacts.modeling;

/**
 * A number to dial a telephone
 */
public class Phone {

	private String number;
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
