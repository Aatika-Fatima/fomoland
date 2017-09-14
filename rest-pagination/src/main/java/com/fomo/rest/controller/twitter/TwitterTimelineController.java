package com.fomo.rest.controller.twitter;

import java.security.Principal;
import java.util.UUID;

import javax.inject.Inject;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.TweetData;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1")
public class TwitterTimelineController {
	@Autowired
	private final Twitter twitter;

	public TwitterTimelineController(Twitter twitter) {
		this.twitter = twitter;
	}

	@Inject
	private ConnectionRepository connectionRepository;

	@RequestMapping(value = "/twitter", method = RequestMethod.GET)
	public String home(Principal currentUser, Model model) {
		Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
		if (connection == null) {
			return "redirect:/connect/twitter";
		}
		model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());
		return "twitter/profile";
	}

	@RequestMapping(value = "twitter/share", method = RequestMethod.POST)
	public ResponseEntity<?> tweet(String message) {
		Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
		if (connection == null) {
			return new ResponseEntity<String>("/signin/twitter", HttpStatus.PERMANENT_REDIRECT);
		}
		connection.getApi().timelineOperations().updateStatus("Hello from FOMO " + UUID.randomUUID().toString());
		return new ResponseEntity<Integer>(50, HttpStatus.OK);
	}

}
