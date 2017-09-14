package com.fomo.unit.dao;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.fomo.persistence.dao.AccountRepository;
import com.fomo.persistence.dao.InterestRepostiory;
import com.fomo.persistence.dao.QuestionsRepository;
import com.fomo.persistence.model.Account;
import com.fomo.persistence.model.AccountQuestionSocial;
import com.fomo.persistence.model.Interest;
import com.fomo.persistence.model.Question;
import com.fomo.persistence.model.SocialMedia;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	InterestRepostiory interestRepository;

	@Autowired
	QuestionsRepository questionRepository;
 

	// @Test
	public void whenFindByUsername_thenReturnAccount() {

		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setPoints(100);
		entityManager.persist(account);
		account = accountRepository.findByUsername("Aatika Fatima");
		Assert.assertEquals(account.getFirstName(), "Aatika");
	}

	// @Test
	public void whenAddInterest_thenReturnList() {

		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		entityManager.persist(account);
		account = accountRepository.findByUsername("Aatika Fatima");
		Assert.assertEquals(account.getFirstName(), "Aatika");

		Interest dance = interestRepository.findOne((long) 1);
		Interest music = interestRepository.findOne((long) 2);

		Set<Interest> interests = new HashSet<>();
		interests.add(dance);
		interests.add(music);
		account.setInterests(interests);

		account = accountRepository.save(account);
		Assert.assertEquals(account.getInterests().size(), 2);
		;

	}

	// @Test
	public void whenFindPointsByID_thenReturnPoints() {
		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setId(1);
		;
		account.setPoints(100);
		entityManager.persist(account);
		account = accountRepository.findByUsername("Aatika Fatima");
		Integer points = accountRepository.getPointsById(1);
		Assert.assertEquals(new Integer(100), points);
	}

	// @Test
	public void whenUpdatePoints_thenReturnUpdatedPoints() {
		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setId(1);
		;
		account.setPoints(100);
		entityManager.persist(account);
		account = accountRepository.findByUsername("Aatika Fatima");
		account.setPoints(110);
		Assert.assertEquals(110, accountRepository.getPointsById(1).doubleValue(), 0.0);
	}

	public void whenUserId_thenReturn_Intersts() {
		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setId(1);

		Interest music = new Interest();
		music.setInterestName("MUSIC");
		entityManager.persistAndFlush(music);

		Interest dance = new Interest();
		dance.setInterestName("DANCE");
		entityManager.persistAndFlush(dance);

		Set<Interest> interests = new HashSet<>();
		interests.add(dance);
		interests.add(music);

		account.setInterests(interests);

	}

	// @Test
	public void whenQuestion_Shared_thenUpdates() {

		Interest dance = new Interest();

		dance.setInterestName("DANCE");

		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setId(1);

		account.setPoints(100);
		entityManager.persist(account);

		Interest music = new Interest();

		dance.setInterestName("MUSIC");
		entityManager.persistAndFlush(dance);

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

		dance = interestRepository.findOne(new Long(1));
		question.setInterest(dance);
		entityManager.persist(question);

		SocialMedia socialMedia = new SocialMedia();
		socialMedia.setSocialMediaName("facebook");
		socialMedia.setPoints(10);
		entityManager.persist(socialMedia);

		socialMedia = new SocialMedia();
		socialMedia.setSocialMediaName("twitter");
		socialMedia.setPoints(20);
		entityManager.persist(socialMedia);

		socialMedia = new SocialMedia();
		socialMedia.setSocialMediaName("whatsapp");
		socialMedia.setPoints(30);
		entityManager.persist(socialMedia);

		AccountQuestionSocial accountQuestionSocial = new AccountQuestionSocial();
		account = accountRepository.findOne((long) 1);
		// accountQuestionSocial.setAccount(account);
		// accountQuestionSocial.setQuestion(question);
		// accountQuestionSocial.setSocialMedia(socialMedia);
		// accountQuestionSocial.setId(new AccountQuestionSocialPK(account ,
		// socialMedia ,question ));;
		// accountQuestionSocial.setId(new
		// AccountQuestionSocialPK(account.getId(),
		// question.getQid(),socialMedia.getSocialMediaId() ));;
		accountQuestionSocial.setShared(true);
		accountQuestionSocial.setSharedDate(Calendar.getInstance().getTime());
		account.getAccountQuestionSocial().add(accountQuestionSocial);
		account = accountRepository.save(account);
		account = accountRepository.findOne((long) 1);
		Set<AccountQuestionSocial> acs = account.getAccountQuestionSocial();
		for (AccountQuestionSocial a : acs) {
			// System.out
			// .println(a.getId().getQuestion().getQid() + " " +
			// a.getId().getSocialMedia().getSocialMediaName() +
			// " " + a.getAccount().getId());
			System.out.println(a.getSharedDate() + " " + a.isShared());
			System.out.println("**********************************");
			// System.out.println(a.getSocialMedia());
			// System.out.println(a.getAccount());
			// System.out.println(a.getQuestion());
		}

	}

	@Test
	public void getUserInterestId() {
		Interest music = new Interest();
		// music.setInterestId(1);
		music.setInterestName("MUSIC");

		Interest dance = new Interest();
		// dance.setInterestId(1);
		dance.setInterestName("DANCE");

		entityManager.persist(music);
		entityManager.persist(dance);

		Set<Interest> interests = new HashSet<>();
		interests.add(music);
		interests.add(dance);

		Account account = new Account("Aatika Fatima", "secret", "Aatika", "Fatima", true);
		account.setId(1);
		account.setPoints(100);
		// account.setInterests(interests);
		entityManager.persist(account);

	}
}
