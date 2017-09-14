package com.fomo.rest.controller.facebook;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FacebookProfileController {

	private final Facebook facebook;
	@PostConstruct
	private void init() {
	    try {
	        String[] fieldsToMap = { "id", "about", "age_range", "birthday",
	                "context", "cover", "currency", "devices", "education",
	                "email", "favorite_athletes", "favorite_teams",
	                "first_name", "gender", "hometown", "inspirational_people",
	                "installed", "install_type", "is_verified", "languages",
	                "last_name", "link", "locale", "location", "meeting_for",
	                "middle_name", "name", "name_format", "political",
	                "quotes", "payment_pricepoints", "relationship_status",
	                "religion", "security_settings", "significant_other",
	                "sports", "test_group", "timezone", "third_party_id",
	                "updated_time", "verified", "viewer_can_send_gift",
	                "website", "work" };

	        Field field = Class.forName(
	                "org.springframework.social.facebook.api.UserOperations")
	                .getDeclaredField("PROFILE_FIELDS");
	        field.setAccessible(true);

	        Field modifiers = field.getClass().getDeclaredField("modifiers");
	        modifiers.setAccessible(true);
	        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	        field.set(null, fieldsToMap);

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	@Inject
	public FacebookProfileController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/facebook/profile", method = RequestMethod.GET)
	public ModelAndView getProfile() {
		
 		User profile = facebook.userOperations().getUserProfile();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("facebook/profile");
		mv.addObject("profile", profile);
		return mv;
	}


	@RequestMapping(value = "/facebook/feed", method = RequestMethod.GET)
	public ModelAndView post() {
		 facebook.pageOperations().post("asdf", "welcome");
		return new ModelAndView("facebook/profile");
	}
}
