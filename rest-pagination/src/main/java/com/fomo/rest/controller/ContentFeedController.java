package com.fomo.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fomo.persistence.dao.ContentFeedRepository;
import com.fomo.persistence.model.Contact;
import com.fomo.persistence.model.ContentFeed;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/rest/v1/contentFeed")
public class ContentFeedController {

	@Autowired
	ContentFeedRepository contentFeedRepository;


	@RequestMapping(value = "/contentFeed", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the questions", response = Contact.class,
			responseContainer = "List")
	public ResponseEntity<Page<ContentFeed>> getAllContacts(Pageable pageable) {
		Page<ContentFeed> allContacts = contentFeedRepository.findAll(pageable);
		return new ResponseEntity<Page<ContentFeed>>(allContacts, HttpStatus.OK);
	}
	
	
	private void verifyContentFeed(long id) {
		ContentFeed contentFeed = contentFeedRepository.findOne(id);
		if (contentFeed == null) {
			throw new ResourceNotFoundException("Content Feed with " + id + " does not exist");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createContentFeed(@Valid @RequestBody ContentFeed contentFeed) {

		contentFeedRepository.save(contentFeed);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ContentFeed> updateContentFeed(@Valid @RequestBody ContentFeed contentFeed) {
		verifyContentFeed(contentFeed.getId());
		contentFeed = contentFeedRepository.save(contentFeed);
		return new ResponseEntity<>(contentFeed, null, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getContentFeed() {
		Iterable<ContentFeed> contentFeedList = contentFeedRepository.findAll();
 		return new ResponseEntity<>(contentFeedList, null, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getContentFeedById(@PathVariable("id") long id) {
		ContentFeed contentFeed = contentFeedRepository.findOne(id);
		return new ResponseEntity<ContentFeed>(contentFeed, null, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContentFeedById(@PathVariable("id") long id) {
		verifyContentFeed(id);
		contentFeedRepository.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContent(){
		contentFeedRepository.deleteAll();
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

}
