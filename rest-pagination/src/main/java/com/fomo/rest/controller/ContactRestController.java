package com.fomo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fomo.persistence.dao.ContactRepository;
import com.fomo.persistence.model.Contact;

import io.swagger.annotations.ApiOperation;

@RestController
public class ContactRestController {

	@Autowired
	ContactRepository contactRepository;

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the questions", response = Contact.class,
			responseContainer = "List")
	public ResponseEntity<Page<Contact>> getAllContacts(Pageable pageable) {
		Page<Contact> allContacts = contactRepository.findAll(pageable);
		return new ResponseEntity<Page<Contact>>(allContacts, HttpStatus.OK);
	}
}
