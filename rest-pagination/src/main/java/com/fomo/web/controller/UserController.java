package com.fomo.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.pinterest.api.Boards;
import org.springframework.social.pinterest.api.BoardsOperations;
import org.springframework.social.pinterest.api.Data;
import org.springframework.social.pinterest.api.Pinterest;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fomo.exception.ResourceNotFoundException;
import com.fomo.persistence.dao.SocialMediaRepository;
import com.fomo.persistence.model.Account;
import com.fomo.persistence.model.ContentFeed;
import com.fomo.persistence.model.Interest;
import com.fomo.persistence.model.Question;
import com.fomo.persistence.model.ShareType;
import com.fomo.persistence.model.SocialMedia;
import com.fomo.persistence.service.IAccountService;
import com.fomo.persistence.service.IContentFeedService;
import com.fomo.persistence.service.IQuestionService;
import com.fomo.web.dto.SharedObjectDto;
import com.fomo.web.dto.SocialMediaDto;

/**
 * @author aatika.fatima
 *
 */
/**
 * @author aatika.fatima
 *
 */
@Controller
@SessionAttributes({ "facebook", "twitter", "pinterest", "facebook", "linkedin", "instagram" })
public class UserController {

	@Value("${spring.data.rest.base-path}")
	private String baseUrl;

	@Autowired
	ConnectionRepository connectionRepository;
	@Autowired
	private Facebook facebook;

	@Autowired
	SocialMediaRepository socialMediaRepository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	IAccountService accountService;

	@Autowired
	IQuestionService questionService;

	@Autowired
	IContentFeedService contentFeedService;
	HttpClient client = HttpFactory.httpClient(true);

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void signin() {
	}

	private void updatePoints(long id, long qid, long socialMediaId, ShareType shareType, int points,
			Date datedShared) {
		accountService.updatePoints(id, qid, socialMediaId, shareType, points, datedShared);
	}

	private Set<ContentFeed> getContentFeed(Principal principal, int page, int size) {
		String id = principal.getName();
		System.err.println("user id = " + id);
		Set<Interest> interest = accountService.getUserInterest(Long.valueOf(id));
		System.err.println("User Interests = " + interest + " size = " + size);
		Page<ContentFeed> resultPage = contentFeedService.findPaginated(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new ResourceNotFoundException("Request Resource is not available");
		}
		for (ContentFeed cf : resultPage.getContent()) {
			System.err.println(cf);
		}
		Set<ContentFeed> cfs = contentFeedService.findByInterest(1);
		for (ContentFeed c : cfs) {
			System.err.println(c);
		}
		return contentFeedService.findByInterest(1);
	}

	private Set<Question> getQuestion(Principal principal, int page, int size) {
		String id = principal.getName();
		return questionService.findByInterestInterestIdIn(accountService.getUserInterestIds(Long.valueOf(id)));
	}

	@RequestMapping(value = "/web/contentFeed", method = RequestMethod.GET)
	public ModelAndView showContentFeedForm(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "1", required = false) int size,
			final UriComponentsBuilder uriComponentsBuilder, HttpServletResponse response,
			Principal principal, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		Set<ContentFeed> contentFeeds = getContentFeed(principal, page, size);
		System.err.println("Content Feed ... " + contentFeeds.size());
		for (ContentFeed contentFeed : contentFeeds) {
			mv.setViewName("contentFeed");
			mv.addObject("contentFeed", contentFeed);
			mv.addObject("contentShortDescription", contentFeed.getDescription().substring(0, 10));

		}
		mv.addObject("userTotalPoints", accountService.getPointsById(Long.valueOf(principal.getName())));
		List<SocialMedia> socialMediaList = socialMediaRepository.findAll();
		for (SocialMedia socialMedia : socialMediaList) {
			String mediaName = socialMedia.getSocialMediaName();
			int points = socialMedia.getPoints();
			mv.addObject(mediaName.toLowerCase(), new SocialMediaDto(socialMedia.getSocialMediaId(),
					socialMedia.getSocialMediaName(), points, false, ShareType.CONTENT_FEED));
			System.err.println("Social Media " + mediaName);
		}
		return mv;

	}

	@RequestMapping(value = "/web/showQuestion", method = RequestMethod.GET)
	public ModelAndView showQuestionForm(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "1", required = false) int size,
			final UriComponentsBuilder uriComponentsBuilder, HttpServletResponse response,
			Principal principal, HttpServletRequest req) {

		ModelAndView mv = new ModelAndView();
		Set<Question> questions = getQuestion(principal, page, size);
		for (Question question : questions)
			mv.addObject("question", question);
		List<SocialMedia> socialMediaList = socialMediaRepository.findAll();
		for (SocialMedia socialMedia : socialMediaList) {
			String mediaName = socialMedia.getSocialMediaName();
			int points = socialMedia.getPoints();
			mv.addObject(mediaName.toLowerCase(), new SocialMediaDto(socialMedia.getSocialMediaId(),
					socialMedia.getSocialMediaName(), points, false, ShareType.QUESTION));
			System.err.println("Social Media " + mediaName);
		}
		mv.addObject("userTotalPoints", accountService.getPointsById(Long.valueOf(principal.getName())));
		mv.setViewName("play_and_earn");
		return mv;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/web/postQuestion", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity processQuestionForm(@RequestParam("qid") long qid,
			@RequestParam("quiz_group") String quiz_group, Principal principal, Model model) {
		System.err.println("post method ===" + quiz_group);
		Question question = questionService.findOne(qid);
		Account account = accountService.findOne(new Long(principal.getName()));
		int points = account.getPoints();
		int totalPoints = 0;
		if (question.getAnswer().equals(quiz_group)) {

			totalPoints = points + 500;

		} else {
			totalPoints = points + 100;
		}
		account.setPoints(totalPoints);
		accountService.update(account);
		model.addAttribute("userTotalPoints", totalPoints);
		return new ResponseEntity(totalPoints - points, HttpStatus.OK);
	}

	@RequestMapping(value = "/web/facebook/share", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Integer> shareOnFacebok(@ModelAttribute("facebook") SocialMediaDto sdo,
			Principal principal) {
		if (!sdo.isShared()) {
			FacebookLink link = new FacebookLink("http://www.springsource.org/spring-social",
					"Spring Social",
					"The Spring Social Project",
					"Spring Social is an extension to Spring to enable applications to connect with service providers.");
			String id = facebook.feedOperations().post(principal.getName(), "Aatika Posted");
			if (id != null) {
				ModelAndView mv = new ModelAndView();
				sdo.setShared(true);
				mv.addObject("facebook", sdo);
				mv.addObject("userTotalPoints", accountService.getPointsById(Long.valueOf(principal.getName())));

				updatePoints(Long.valueOf(principal.getName()), 1, sdo.getSocialMediaId(), sdo.getShareType(),
						sdo.getPoints(),
						Calendar.getInstance().getTime());
			}

			return new ResponseEntity<Integer>(sdo.getPoints(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.IM_USED);
	}

	@RequestMapping(value = "/web/twitter/share", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity shareOnTwitter(@ModelAttribute("twitter") SocialMediaDto twitterDto, Principal principal) {
		if (!twitterDto.isShared()) {
			Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
			if (connection == null) {
				return new ResponseEntity<String>("/signin/twitter", HttpStatus.PERMANENT_REDIRECT);
			}
			Tweet t = connection.getApi().timelineOperations()
					.updateStatus("Hello from FOMO " + UUID.randomUUID().toString());
			if (t != null) {
				ModelAndView mv = new ModelAndView();
				twitterDto.setShared(true);
				mv.addObject("twitter", twitterDto);
				updatePoints(Long.valueOf(principal.getName()), 1, twitterDto.getSocialMediaId(),
						twitterDto.getShareType(), twitterDto.getPoints(),
						Calendar.getInstance().getTime());
			}
		}
		return new ResponseEntity<Integer>(twitterDto.getPoints(), HttpStatus.OK);
	}
 

	public void generateRandomContent() {
		SharedObjectDto shareType; Object o;
		LinkedHashMap<SharedObjectDto, Object> hm;
		Random random = new Random();
		int r = random.nextInt(3);
		switch (r) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}

	public ResponseEntity shareOnPinterest(@ModelAttribute("pinterest") SocialMediaDto pinterestDto,
			Principal principal) {
		if (!pinterestDto.isShared()) {
			Connection<Pinterest> connection = connectionRepository.findPrimaryConnection(Pinterest.class);
			if (connection == null) {
				return new ResponseEntity<String>("/signin/twitter", HttpStatus.PERMANENT_REDIRECT);
			}
			BoardsOperations boardOperations = connection.getApi().boardsOperations();
			Data<Boards> boards = boardOperations.getBoard("Places");
			Boards b = boards.getData();

		}
		return new ResponseEntity<Integer>(pinterestDto.getPoints(), HttpStatus.OK);
	}

	public ModelAndView showQuestion(Principal principal) {
		String id = principal.getName();
		Set<Interest> interest = accountService.getUserInterest(Long.valueOf(id));
		return null;
	}

	public ModelAndView processQuestionForm() {
		return null;
	}

	private RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
			final HttpMessageConverter<?> httpMessageConverter = restTemplate.getMessageConverters().get(i);
			System.err.println(httpMessageConverter.getClass().getName());
		}

		restTemplate.setMessageConverters(getMessageConverters());
		return restTemplate;
	}

	private List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter
				.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
						MediaType.APPLICATION_OCTET_STREAM));
		converters.add(jsonConverter);
		converters.add(createXmlHttpMessageConverter());
		return converters;
	}

	private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		return xmlConverter;
	}
}
