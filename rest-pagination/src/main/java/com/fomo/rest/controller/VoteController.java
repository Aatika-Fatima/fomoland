package com.fomo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fomo.persistence.dao.VoteRepository;
import com.fomo.persistence.model.Vote;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;

@RestController
public class VoteController {

	@Autowired
	public VoteRepository voteRepository;

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
	@ApiOperation(value = "Create Vote",
			notes = "Create a vote for a given poll Id",
			response = Void.class
			)
	public ResponseEntity<?> createVote(@PathVariable(name = "pollId") Long pollId, @RequestBody Vote vote) {
		vote = voteRepository.save(vote);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
	@ApiOperation(value="get All Votes", 
	notes="Retreive all the votes for a given poll id", 
	response=Vote.class, 
	responseContainer="List")
	public ResponseEntity<Iterable<Vote>> getAllVotes(@PathVariable("pollId") long pollId) {
		return new ResponseEntity<>(voteRepository.findByPoll(pollId), HttpStatus.OK);
	}
}
