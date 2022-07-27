package com.marshall.singlestonedemo;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.marshall.singlestonedemo.contacts.persistence.repository.ContactEntityMapper;
import com.marshall.singlestonedemo.contacts.persistence.repository.ContactEntityMapperImpl;
import com.marshall.singlestonedemo.contacts.persistence.repository.ContactJPARepository;
import com.marshall.singlestonedemo.contacts.persistence.repository.ContactRepository;
import com.marshall.singlestonedemo.contacts.rest.resource.ContactResource;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.marshall.singlestonedemo")
public class SinglestoneDemoApplicationConfigGroup {

	@Bean
	ContactResource contactResource(ContactRepository contactRepository) {
		return new ContactResource(contactRepository);
	}
	
	@Bean
	ContactRepository contactRepository(ContactJPARepository contactJpaRepository,
			ContactEntityMapper contactEntityMapper,
			EntityManager entityManager) {
		return new ContactRepository(contactJpaRepository, contactEntityMapper, entityManager);
	}
	
	@Bean
	ContactEntityMapper contactEntityMapper() {
		return new ContactEntityMapperImpl();
	}
	
}
