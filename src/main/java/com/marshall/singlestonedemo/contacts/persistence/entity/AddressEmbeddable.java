package com.marshall.singlestonedemo.contacts.persistence.entity;

import javax.persistence.Embeddable;

/**
 * Set of variables associated with an address, provided as
 * an easy way to embed into tables using this information.
 *
 */
@Embeddable
public class AddressEmbeddable {

	private String street;
	private String city;
	private String state;
	private String zip;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
