package com.fomo.rest.controller;

import java.net.URI;

import javax.validation.Valid;

import org.eclipse.jdt.internal.compiler.ast.MethodDeclaration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fomo.dto.error.ErrorDetails;
import com.fomo.exception.ResourceNotFoundException;
import com.fomo.persistence.dao.QuestionsRepository;
import com.fomo.persistence.model.Poll;
import com.fomo.persistence.model.Question;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class QuestionController {
	@Autowired
	private QuestionsRepository questionRepository;

	protected void verifyQuestion(long i) throws ResourceNotFoundException {
		Question question = questionRepository.findOne(i);
		if (question == null) {
			throw new ResourceNotFoundException("Question with id " + i + " not found");
		}
	}

	@ApiOperation(value = "Create Question", notes = "The newly created questionn will be sent to location header")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Poll Created Successfully", response = Void.class),
			@ApiResponse(code = 500, message = "Error in creating poll", response = ErrorDetails.class) })
	@RequestMapping(value = "/questions", method = RequestMethod.POST)
	public ResponseEntity<Void> createQuestion(@Valid @RequestBody Question question) {
		//@// @formatter:off
		question = questionRepository.save(question);
		URI newQuestionUri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
				.buildAndExpand(question.getQid())
				.toUri();
		// @formatter:on
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(newQuestionUri);

		return new ResponseEntity<Void>(null, responseHeaders, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Question", notes = "Returns the question for the given question id")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "Question does not exist", response = ErrorDetails.class),
			@ApiResponse(code = 200, message = "Question found", response = Question.class)
	})
	@RequestMapping(value = "/questions/{qid}", method = RequestMethod.GET)
	public ResponseEntity<Question> getQuestion(@PathVariable("qid") long qid) {
		Question question = questionRepository.findOne(qid);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

	@ApiOperation(value = "Update Question", notes = "Updates a question and returns updated questiion",
			response = Question.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "question updated successfully", response = Void.class),
			@ApiResponse(code = 404, message = "unable to find Question", response = ErrorDetails.class)
	})
	@RequestMapping(value = "/questions", method = RequestMethod.PUT)
	public ResponseEntity<Question> updatePoll(@RequestBody Question question) {
		verifyQuestion(question.getQid());
		question = questionRepository.save(question);
		return new ResponseEntity<Question>(question, HttpStatus.OK);

	}

	@RequestMapping(value = "/questions/{qid}", method = RequestMethod.DELETE)
	@ApiOperation(value = "deletes a question",
			notes = "Delete a Question with given question id. If the question with given questionId doesnot exist, Question API with throw ResourceNotFoundException",
			response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "", response = Void.class),
			@ApiResponse(code = 404, message = "Unable to find question", response = ErrorDetails.class) })
	public ResponseEntity<Void> deleteQuestion(@PathVariable("qid") long qid) {
		verifyQuestion(qid);
		questionRepository.delete(qid);
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
 

}
