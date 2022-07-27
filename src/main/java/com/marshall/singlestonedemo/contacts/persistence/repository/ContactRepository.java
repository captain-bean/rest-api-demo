package com.marshall.singlestonedemo.contacts.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.marshall.singlestonedemo.contacts.modeling.Contact;
import com.marshall.singlestonedemo.contacts.modeling.PhoneNumberType;
import com.marshall.singlestonedemo.contacts.persistence.entity.ContactEntity;
import com.marshall.singlestonedemo.contacts.persistence.entity.QContactEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * A service for providing CRUD operations to do with Contacts
 * 
 * Dev note: This layer exists to provide the ability to make use of 
 * the Spring JPA provided repository, while restricting the breadth of the operations
 * allowed. It also gives us a place to inject additional dependencies, giving
 * us the ability to use other helpful persistence frameworks like QueryDSL.
 *
 */
public class ContactRepository {

	private ContactJPARepository jpaRepository;
	private ContactEntityMapper mapper;
	
	private JPAQueryFactory queryFactory;
	
	public ContactRepository(ContactJPARepository jpaRepository, 
			ContactEntityMapper contactEntityMapper,
			EntityManager entityManager) {
		this.jpaRepository = jpaRepository;
		this.mapper = contactEntityMapper;
		
		this.queryFactory = new JPAQueryFactory(entityManager);
	}
	
	
	public List<Contact> listAll() {
		return mapper.entitiesToModels(jpaRepository.findAll());
	}
	
	public Contact saveContact(Contact contact) {
		return mapper.entityToModel(jpaRepository.save(mapper.modelToEntity(contact)));
	}
	
	public Contact findContactById(Long id) {
		Optional<ContactEntity> potentialMatch = jpaRepository.findById(id);
		if(potentialMatch.isPresent()) {
			return mapper.entityToModel(potentialMatch.get());
		}
		return null;
	}
	
	public void deleteContactById(Long id) {
		jpaRepository.deleteById(id);
	}
	
	public List<Contact> findAllWithHomePhone() {
		QContactEntity contact = QContactEntity.contactEntity;
		
		List<ContactEntity> results = queryFactory.selectFrom(contact)
				.where(contact.phone.any().type.eq(PhoneNumberType.HOME))
				.orderBy(contact.name.last.asc())
				.orderBy(contact.name.first.asc())
				.fetch();
		
		if(results == null) {
			results = new ArrayList<>();
		}
		
		return mapper.entitiesToModels(results);
	}
}
