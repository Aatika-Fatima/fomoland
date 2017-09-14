package com.fomo.rest.controller.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1")
public class FacebookFeedController {
	private Facebook facebook;

	@Autowired
	public FacebookFeedController(Facebook facebook) {
		super();
		this.facebook = facebook;
	}

	@RequestMapping(value="facebook/feed", method=RequestMethod.GET)
	public String showFeed(Model model) {
		PagedList<Post>feedList = facebook.feedOperations().getFeed();
		for(Post post: feedList){
			System.out.println(post.getMessage());
		}
		model.addAttribute("feed", facebook.feedOperations().getFeed());
		return "facebook/feed";
	}
	@RequestMapping(value = "facebook/share", method = RequestMethod.POST)
	public ResponseEntity<Integer> feedOperations() {
		FacebookLink link = new FacebookLink("http://www.springsource.org/spring-social",
				"Spring Social",
				"The Spring Social Project",
				"Spring Social is an extension to Spring to enable applications to connect with service providers.");
		facebook.feedOperations().postLink("I'm trying out Spring Social!", link);
		/*facebook.feedOperations().post(new PostData("me").message("I'm trying out Spring Social!")
				.link("http://www.springsource.org/spring-social", null, "Spring Social", "The Spring Social Project",
						"Spring Social is an extension to Spring to enable applications to connect with service providers."));*/
		return new ResponseEntity<Integer>(10, HttpStatus.OK);
	}

}
