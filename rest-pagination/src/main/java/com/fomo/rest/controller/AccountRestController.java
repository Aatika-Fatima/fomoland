package com.fomo.rest.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fomo.persistence.model.Interest;
import com.fomo.persistence.service.IAccountService;

@RestController
@RequestMapping(value = "/rest/v1/accounts")
public class AccountRestController {

	@Autowired
	IAccountService accountService;

	@RequestMapping(value = "{username}/intersts", method = RequestMethod.POST)
	public ResponseEntity<Void> createUserIntersts(@PathVariable("username") String username, String intersets) {

		String split[] = intersets.split(",");
		Collection<String> interestNames = new HashSet<>();
		for (String s : split)
			interestNames.add(s);
		System.err.println(interestNames);
		System.err.println("Username = " + username);
		accountService.addInterests(username, interestNames);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getPointsById(@PathVariable("id") long id) {
		Integer integer = accountService.getPointsById(id);
		JsonNode jsonNode = new ObjectMapper().valueToTree(new HashMap<>().put("points", integer));
		return new ResponseEntity<>(jsonNode, HttpStatus.OK);
	}
	



}
