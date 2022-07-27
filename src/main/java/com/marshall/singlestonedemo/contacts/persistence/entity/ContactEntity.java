package com.marshall.singlestonedemo.contacts.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Persistence layer representation of a Contact
 *
 */
@Entity
public class ContactEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private FullNameEmbeddable name;
	
	@Embedded
	private AddressEmbeddable address;
	
	@ElementCollection
	private List<PhoneEmbeddable> phone;
	
	@Column(name = "email")
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FullNameEmbeddable getName() {
		return name;
	}

	public void setName(FullNameEmbeddable name) {
		this.name = name;
	}

	public AddressEmbeddable getAddress() {
		return address;
	}

	public void setAddress(AddressEmbeddable address) {
		this.address = address;
	}

	public List<PhoneEmbeddable> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneEmbeddable> phoneNumbers) {
		this.phone = phoneNumbers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
