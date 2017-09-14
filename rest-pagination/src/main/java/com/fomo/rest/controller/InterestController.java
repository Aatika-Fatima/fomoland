package com.fomo.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fomo.exception.ResourceNotFoundException;
import com.fomo.persistence.dao.InterestRepostiory;
import com.fomo.persistence.model.Interest;

@RestController
@RequestMapping("/rest/v1/interests")
public class InterestController {
	@Autowired
	public InterestRepostiory interestRepository;

	public void verifyInterest(long interestId) {
		Interest interest = interestRepository.findOne(interestId);
		if (interest == null) {
			throw new ResourceNotFoundException("Interst with " + interestId + " does not exist");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createInterets(@Valid @RequestBody Interest interest) {
		interest = interestRepository.save(interest);
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Interest>> getAllInterests() {
		
		return new ResponseEntity<List<Interest>>(interestRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value="{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Interest> getInterestById(@PathVariable("interestId") long interestId) {
		Interest i = new Interest();i.setInterestName("aat");
		i.setInterestId(1);
		return new ResponseEntity<Interest>(i, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<List<Interest>> deleteInterestById(@PathVariable("interestId") long interestId) {

		return new ResponseEntity<List<Interest>>(interestRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<List<Interest>> updateInterests(@Valid @RequestBody Interest interest) {
		return new ResponseEntity<List<Interest>>(interestRepository.findAll(), HttpStatus.OK);
	}
}
