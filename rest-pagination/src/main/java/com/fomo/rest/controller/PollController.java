package com.fomo.rest.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fomo.dto.error.ErrorDetails;
import com.fomo.exception.ResourceNotFoundException;
import com.fomo.persistence.dao.PollRepository;
import com.fomo.persistence.model.Poll;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@io.swagger.annotations.Api(value = "polls", description = "Poll API")
@RequestMapping(value="/rest/v1")
@RepositoryRestResource(exported = false)
public class PollController {
	@Autowired
	PollRepository pollRepository;

	protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
		Poll poll = pollRepository.findOne(pollId);
		if (poll == null) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
		}
	}

	@RequestMapping(value = "polls", method = RequestMethod.GET)
	@ApiOperation(
			value = "Get All Polls",
			notes = "Retrieves all polls",
			response = Poll.class,
			responseContainer = "List")
	public ResponseEntity<Iterable<Poll>> getAllPolls() {

		Iterable<Poll> polls = pollRepository.findAll();
		return new ResponseEntity<Iterable<Poll>>(polls, HttpStatus.OK);
	}

	@RequestMapping(value = "polls", method = RequestMethod.POST)
	@ApiOperation(
			value = "creates a new poll",
			notes = "The newly created poll id will be sent to the location response header",
			response = Void.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Poll Created Successfully",
					response = Void.class),
			@ApiResponse(code = 500, message = "Error creating Poll",
					response = ErrorDetails.class) })
	public ResponseEntity<Void> createPoll(@Valid @RequestBody Poll poll) {
		poll = pollRepository.save(poll);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId())
				.toUri();
		responseHeaders.setLocation(newPollUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

	}

	@RequestMapping(value = "polls/{pollId}", method = RequestMethod.GET)
	@ApiOperation(
			value = "Retrieves a Poll associated with the pollId",
			notes = "If the poll id does not exist it will throw a ResourceNotFoundException",
			response = Poll.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retreives all polls", response = Poll.class),
			@ApiResponse(code = 500, message = "Unable to find poll with given poll id", response = ErrorDetails.class)
	})
	public ResponseEntity<Poll> getPoll(@PathVariable Long pollId) {
		verifyPoll(pollId);

		Poll poll = pollRepository.findOne(pollId);
		return new ResponseEntity<Poll>(poll, HttpStatus.OK);
	}

	@ApiOperation(value = "updates a poll",
			notes = "Updates a poll for the given poll Id, Throws a ResourceNotFoundException if the Poll with given pollid does not exist",
			response = Poll.class,
			responseContainer = "java.util.List")
	@RequestMapping(value = "{pollId}", method = RequestMethod.PUT)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "poll updated successfully", response = Void.class),
			@ApiResponse(code = 404, message = "unable to find poll", response = ErrorDetails.class)
	})
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
		verifyPoll(pollId);

 		Poll p = pollRepository.save(poll);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "{pollId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "deletes a poll",
			notes = "Delete a poll with given poll id. If the poll with given pollId doesnot exist, Poll API with throw ResourceNotFoundException",
			response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "", response = Void.class),
			@ApiResponse(code = 404, message = "Unable to find Poll", response = ErrorDetails.class) })
	public ResponseEntity<Void> deletePoll(@PathVariable Long pollId) {
		verifyPoll(pollId);
		pollRepository.delete(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
