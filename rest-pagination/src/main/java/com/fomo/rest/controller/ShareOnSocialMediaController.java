package com.fomo.rest.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.pinterest.api.Pinterest;
import org.springframework.social.twitter.api.Twitter;

 
public class ShareOnSocialMediaController {

	@Autowired
	private Twitter twitter;

	@Autowired
	private Facebook facebook;

	@Autowired
	private Pinterest pinterest;

	@Autowired
	private LinkedIn linkedIn;

	public ResponseEntity<?> sharedOnSocialMedia(String params, String message) throws JSONException {
		JSONObject json = new JSONObject();
		int points = 0;
		String tokens[] = params.split(",");
		for (String mediaName : tokens)
			switch (mediaName) {
			case "FACEBOOK":
				shareOnFacebook(message);
				points += 10;
				json.put("points", points);
 				return new ResponseEntity<>(json, null, HttpStatus.OK);
			case "TWITTER":
				shareOnTwitter(message);
				points += 10;
				return new ResponseEntity<>(json, HttpStatus.OK);

			case "LINKEDIN":
				shareOnLinkedIn(message);
				points += 10;
				return new ResponseEntity<>(json, HttpStatus.OK);

			case "PINTEREST":
				shareOnLinkedIn(message);
				points += 10;
				return new ResponseEntity<>(json, HttpStatus.OK);

			case "INSTAGRAM":
				shareOnPinterest(message);
				points += 10;
				return new ResponseEntity<>(json, HttpStatus.OK);

			}
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	private void shareOnFacebook(String message) {
		facebook.feedOperations().post(facebook.userOperations().getUserProfile().getId(), message);
	}

	private void shareOnTwitter(String message) {
		twitter.timelineOperations().updateStatus(message);
	}

	private void shareOnLinkedIn(String message) {
	}

	private void shareOnInstagram(String message) {
	}

	private void shareOnPinterest(String message) {
	}
}
