package com.marshall.singlestonedemo.contacts.modeling;

import java.util.List;

/**
 * A person and methods to communicate with them
 */
public class Contact {
	
	private Long id;
	private FullName name;
	private Address address;
	private List<Phone> phone;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public FullName getName() {
		return name;
	}
	public void setName(FullName name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Phone> getPhone() {
		return phone;
	}
	public void setPhone(List<Phone> phoneNumbers) {
		this.phone = phoneNumbers;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
