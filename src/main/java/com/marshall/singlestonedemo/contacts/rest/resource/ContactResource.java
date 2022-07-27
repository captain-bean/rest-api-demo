package com.marshall.singlestonedemo.contacts.rest.resource;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marshall.singlestonedemo.contacts.modeling.Contact;
import com.marshall.singlestonedemo.contacts.persistence.repository.ContactRepository;
import com.marshall.singlestonedemo.contacts.rest.dto.CallListItem;

/**
 * ReST resource for API operations to do with Contacts
 * 
 * Error handling not implemented for brevity. Generally I would
 * use ResponseEntity and set the error code and message details.
 * There are also ways to set generic error handlers for exceptions and
 * such that would be good practice.
 *
 */
@RestController
@RequestMapping("/contacts")
public class ContactResource {

	private ContactRepository repo;
	
	public ContactResource(ContactRepository contactRepository) {
		this.repo = contactRepository;	
	}
	
	
	@GetMapping
	@ResponseBody
	public List<Contact> listAllContacts() {
		return repo.listAll();
	}
	
	@PostMapping
	@ResponseBody
	public Contact createContact(@RequestBody Contact contact) {
		if(contact.getId() != null) {
			//Error out
		}
		
		return repo.saveContact(contact);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseBody
	public Contact updateContact(@RequestBody Contact contact, @PathVariable Long id) {
		if(repo.findContactById(id) == null) {
			//Error out
		}
		
		//To ensure ignoring passed in ID if any attempted
		contact.setId(id);
		
		return repo.saveContact(contact);
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public Contact getContactById(@PathVariable Long id) {
		Contact contact = repo.findContactById(id);
		if(contact == null) {
			//Error out
		}
		
		return contact;
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteContact(@PathVariable Long id) {
		Contact contact = repo.findContactById(id);
		if(contact == null) {
			//Error out
		}
		
		repo.deleteContactById(id);
	}
	
	@GetMapping(value = "/call-list")
	public List<CallListItem> getCallList(){
		return CallListItemMapper.contactListToCallList(
				repo.findAllWithHomePhone());
	}
	
}