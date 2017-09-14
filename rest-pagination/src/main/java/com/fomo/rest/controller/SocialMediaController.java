package com.fomo.rest.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fomo.persistence.dao.SocialMediaRepository;
import com.fomo.persistence.model.SocialMedia;

@RestController
@RequestMapping("/rest/v1/socialMedia")
public class SocialMediaController {
	@Autowired
	SocialMediaRepository socialMediaRepository;

	private void verifySocialMedia(long socialMediaId) {
		SocialMedia socialMedia = socialMediaRepository.findOne(socialMediaId);
		if (socialMedia == null) {
			throw new ResourceNotFoundException("Social Media with " + socialMediaId + " doesnot exist");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createSocialMedia(@Valid @RequestBody SocialMedia socialMedia) {
		socialMedia = socialMediaRepository.save(socialMedia);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(socialMedia.getSocialMediaId())
				.toUri();
		responseHeaders.setLocation(newPollUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SocialMedia>> getAllSocialMedia() {
		//System.err.println(socialMediaRepository.findAll().get(0));
		return new ResponseEntity<List<SocialMedia>>(socialMediaRepository.findAll(), null, HttpStatus.OK);
	}

	@RequestMapping(value = "{socialMediaId}", method = RequestMethod.GET)
	public ResponseEntity<SocialMedia> getSocialMediaById(@PathVariable("socialMediaId") long socialMediaId) {
		SocialMedia socialMedia = socialMediaRepository.findOne(socialMediaId);
		return new ResponseEntity<SocialMedia>(socialMedia, HttpStatus.OK);
	}

	@RequestMapping(value = "{socialMediaId}", method = RequestMethod.PUT)
	public ResponseEntity<SocialMedia> updateSocialMedia(@Valid @RequestBody SocialMedia socialMedia) {
		verifySocialMedia(socialMedia.getSocialMediaId());
		socialMedia = socialMediaRepository.save(socialMedia);
		return new ResponseEntity<SocialMedia>(socialMedia, HttpStatus.OK);
	}

	@RequestMapping(value = "{socialMediaId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSocialMedia(long socialMedia) {
		verifySocialMedia(socialMedia);
		socialMediaRepository.delete(socialMedia);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
