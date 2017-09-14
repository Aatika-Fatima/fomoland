package com.fomo.unit.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.fomo.persistence.dao.InterestRepostiory;
import com.fomo.persistence.model.Interest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InterestRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	InterestRepostiory interestRepository;

	@Before
	public void setUp() {
/*		Interest danceInterest = new Interest();
		danceInterest.setInterestName("Dance");
		entityManager.persist(danceInterest);

		Interest musicInterest = new Interest();
		musicInterest.setInterestName("Music");
		entityManager.persist(musicInterest);

		Interest singingInterest = new Interest();
		singingInterest.setInterestName("Sing");
		entityManager.persist(singingInterest);*/
	}

	@Test
	public void testSaveInterest() {
/*
		Interest danceInterest = new Interest();
		danceInterest.setInterestName("Dance");
		entityManager.persist(danceInterest);

		Interest musicInterest = new Interest();
		musicInterest.setInterestName("Music");
		entityManager.persist(musicInterest);

		Interest singingInterest = new Interest();
		singingInterest.setInterestName("Sing");
		entityManager.persist(singingInterest);*/

		Assert.assertEquals(interestRepository.findAll().size(), 3);
	}

	@Test
	public void whenGetInterestByName_thenReturnInterstLists() {

		Set<String> interestNames = new HashSet<>();
		interestNames.add("Dance");
		interestNames.add("Music");
		int size = interestRepository.findByInterestNameIn(interestNames).size();
		Assert.assertEquals(2, size);
	}
}
