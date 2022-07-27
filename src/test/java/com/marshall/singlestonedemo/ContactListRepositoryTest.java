package com.marshall.singlestonedemo;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marshall.singlestonedemo.contacts.modeling.Address;
import com.marshall.singlestonedemo.contacts.modeling.Contact;
import com.marshall.singlestonedemo.contacts.modeling.FullName;
import com.marshall.singlestonedemo.contacts.modeling.Phone;
import com.marshall.singlestonedemo.contacts.modeling.PhoneNumberType;
import com.marshall.singlestonedemo.contacts.persistence.repository.ContactRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ContactListRepositoryTest {

	@Autowired
    private ContactRepository contactRepository;
	
	/*
	 * Cases
	 * 
	 * 	- No contacts
	 *  - No valid contacts
	 *  - One valid contacts
	 *  - One valid and one non-valid contact
	 *  - Multiple valid contacts, ensure order
	 */
	
	
	@Test
	public void emptyDataSetReturnsEmptyCallList() {
		//Arrange (none needed, empty repo)
		
		//Act
		List<Contact> callListContacts = contactRepository.findAllWithHomePhone();
		
		//Assert
		assertThat(callListContacts.isEmpty());
	}
	
	@Test
	public void noValidContactsReturnsEmptyCallList() {
		//Arrange (none needed, empty repo)
		Contact invalidContact = contact(haroldGilkeyName(), Collections.singletonList(phone(PhoneNumberType.MOBILE)));
		contactRepository.saveContact(invalidContact);
		
		//Act
		List<Contact> callListContacts = contactRepository.findAllWithHomePhone();
		
		//Assert
		assertThat(callListContacts.isEmpty());
	}
	
	@Test
	public void oneValidContactReturns() {
		//Arrange
		Contact validContact = contact(haroldGilkeyName(), Collections.singletonList(phone(PhoneNumberType.HOME)));
		contactRepository.saveContact(validContact);
		
		//Act
		List<Contact> callListContacts = contactRepository.findAllWithHomePhone();
		
		//Assert
		assertThat(callListContacts).size().isEqualTo(1);
	}
	
	@Test
	public void mixedValidityContactsOnlyReturnsValid() {
		//Arrange
		Contact validContact = contact(haroldGilkeyName(), Collections.singletonList(phone(PhoneNumberType.HOME)));
		contactRepository.saveContact(validContact);
		Contact invalidContact = contact(haroldGilkeyName(), Collections.singletonList(phone(PhoneNumberType.MOBILE)));
		contactRepository.saveContact(invalidContact);
		
		//Act
		List<Contact> callListContacts = contactRepository.findAllWithHomePhone();
		
		//Assert
		assertThat(callListContacts).size().isEqualTo(1);
	}
	
	@Test
	public void multipleContactsAreOrderedByLastNameFirstName() {
		//Arrange
		Contact contactBB = contact(haroldGilkeyName(), Collections.singletonList(phone(PhoneNumberType.HOME)));
		contactBB.getName().setLast("B");
		contactBB.getName().setFirst("B");
		contactRepository.saveContact(contactBB);
		
		Contact contactA = contact(haroldGilkeyName(), Collections.singletonList(phone(PhoneNumberType.HOME)));
		contactA.getName().setLast("A");
		contactRepository.saveContact(contactA);
		
		Contact contactBA = contact(haroldGilkeyName(), Collections.singletonList(phone(PhoneNumberType.HOME)));
		contactBA.getName().setLast("B");
		contactBA.getName().setFirst("A");
		contactRepository.saveContact(contactBA);
		

		//Act
		List<Contact> callListContacts = contactRepository.findAllWithHomePhone();
		
		//Assert 
		assertThat(callListContacts).size().isEqualTo(3);
		assertThat(callListContacts).element(0).matches(contact -> contact.getName().getLast().equals("A"));
		assertThat(callListContacts).element(1).matches(contact -> {
			return contact.getName().getLast().equals("B") && contact.getName().getFirst().equals("A");
		});
		assertThat(callListContacts).element(2).matches(contact -> {
			return contact.getName().getLast().equals("B") && contact.getName().getFirst().equals("B");
		});
		
		// A more succinct test suite might make use of comparators to ensure the list is sorted
	}
	
	
	
	
	
	/*
	 * Helper methods to construct filled out Contact objects
	 * 
	 * Usually I would create a dedicated "Mother" class for prototypical instances,
	 * and potentially tie it into a builder pattern to allow easy slight modifications
	 * to prototypical instances.
	 */
	private Contact contact(FullName name, List<Phone> numbers) {
		Contact contact = new Contact();
		
		Address address = new Address();
		address.setCity("city");
		address.setState("state");
		address.setStreet("street");
		address.setZip("zip");
		contact.setAddress(address);
		
		contact.setEmail("email");
		contact.setName(name);
		
		contact.setPhone(numbers);
		
		return contact;
	}
	
	private FullName haroldGilkeyName() {
		FullName name = new FullName();
		name.setFirst("Harold");
		name.setMiddle("Francis");
		name.setLast("Gilkey");
		
		return name;
	}
	
	private Phone phone(PhoneNumberType type) {
		Phone phone = new Phone();
		phone.setNumber("540-867-5309");
		phone.setType(type);
		return phone;
	}

}
