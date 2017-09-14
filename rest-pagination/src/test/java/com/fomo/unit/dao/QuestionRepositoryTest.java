package com.fomo.unit.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.fomo.persistence.dao.QuestionsRepository;
import com.fomo.persistence.model.Account;
import com.fomo.persistence.model.AccountQuestionSocial;
import com.fomo.persistence.model.AccountQuestionSocialPK;
import com.fomo.persistence.model.Interest;
import com.fomo.persistence.model.Question;
import com.fomo.persistence.model.ShareType;
import com.fomo.persistence.model.SocialMedia;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	QuestionsRepository questionRepository;

	//@Test
	public void whenQuestionById_then_ReturnQuestion() {

		Interest music = new Interest();
		music.setInterestId(1);
		music.setInterestName("MUSIC");

		Interest dance = new Interest();
		dance.setInterestId(1);
		dance.setInterestName("DANCE");

		Set<Interest> interests = new HashSet<>();
		interests.add(music);
		interests.add(dance);

		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setId(1);
		account.setPoints(100);
		account.setInterests(interests);
		entityManager.persist(account);

		Question question = new Question();
		question.setqText("What is an apple");
		question.setNotes("Apple is a fruit");
		question.setAnswer("Fruit");
		question.setOptA("Vegetable");
		question.setOptB("Tomato");
		question.setOptB("asdf");
		question.setOptC("ham");
		question.setOptD("carrot");
		question.setPoints(10);
		question.setNotes("notes for question");
		question.setLink("Link for Question");
		question.setInterest(music);
		entityManager.persist(question);

		question = new Question();
		question.setqText("What is an Mango");
		question.setNotes("Apple is a fruit");
		question.setAnswer("Fruit");
		question.setOptA("Vegetable");
		question.setOptB("Tomato");
		question.setOptB("asdf");
		question.setOptC("ham");
		question.setOptD("carrot");
		question.setPoints(10);
		question.setNotes("notes for question");
		question.setLink("Link for Question");
		question.setInterest(dance);
		entityManager.persist(question);

		Set<Question> q = questionRepository.findByInterest(1);
		Assert.notNull(q);
		System.err.println(q);

		Set<String> interestNames = new HashSet<>();
		interestNames.add("MUSIC");
		interestNames.add("DANCE");
		Set<Question> qs = questionRepository.findByInterestInterestIdIn(Arrays.asList(new Long(1), new Long(2)));

		Assert.notNull(qs);
		System.err.println(qs);
	}

	@Test
	public void whenQuestionID_then_Return_QuestionIds() {
		Interest music = new Interest();
 		music.setInterestName("MUSIC");
 		entityManager.persist(music);
		Interest dance = new Interest();
 		dance.setInterestName("DANCE");
 		entityManager.persist(dance);
 		
		Set<Interest> interests = new HashSet<>();
		interests.add(music);
		interests.add(dance);

		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setId(1);
		account.setPoints(100);
		account.setInterests(interests);
		entityManager.persist(account);

		Question question = new Question();
		question.setqText("What is an apple");
		question.setNotes("Apple is a fruit");
		question.setAnswer("Fruit");
		question.setOptA("Vegetable");
		question.setOptB("Tomato");
		question.setOptB("asdf");
		question.setOptC("ham");
		question.setOptD("carrot");
		question.setPoints(10);
		question.setNotes("notes for question");
		question.setLink("Link for Question");
		question.setInterest(music);
		entityManager.persist(question);
		entityManager.flush();
		question = new Question();
		question.setqText("What is an Mango");
		question.setNotes("Apple is a fruit");
		question.setAnswer("Fruit");
		question.setOptA("Vegetable");
		question.setOptB("Tomato");
		question.setOptB("asdf");
		question.setOptC("ham");
		question.setOptD("carrot");
		question.setPoints(10);
		question.setNotes("notes for question");
		question.setLink("Link for Question");
		question.setInterest(dance);
		entityManager.persist(question);entityManager.flush();

		SocialMedia sm = new SocialMedia();
		sm.setSocialMediaName("FACEBOOK");
		sm.setPoints(1000);
		entityManager.persist(sm);entityManager.flush();

		AccountQuestionSocial acs = new AccountQuestionSocial();
		acs.setId(new AccountQuestionSocialPK(account.getId(), question.getQid(), sm.getSocialMediaId()));
		acs.setAccount(account);
	    acs.setQuestion(question);
		acs.setSharedDate(new Date());
		acs.setShareType(ShareType.QUESTION);
		acs.setShared(true);
	    acs.setSocialMedia(sm);
		entityManager.persist(acs); 
		
		List<Long>qids = questionRepository.getQuestionIds(music.getInterestId(), account.getId(), new Date());
		System.out.println(qids); 
		Assert.notNull(qids);
 	}
}
