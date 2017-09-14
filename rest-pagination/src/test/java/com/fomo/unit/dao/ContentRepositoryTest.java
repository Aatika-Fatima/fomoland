package com.fomo.unit.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.fomo.persistence.dao.ContentFeedRepository;
import com.fomo.persistence.model.ContentFeed;
import com.fomo.persistence.model.Interest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContentRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ContentFeedRepository contentFeedRepository;

	@Test
	public void whenUploadContent_thenNotNull() {

		Interest music = new Interest();
 		music.setInterestName("MUSIC");

		Interest dance = new Interest();
 		dance.setInterestName("DANCE");

		entityManager.persist(music);
		entityManager.persist(dance);

		ContentFeed contentFeed = new ContentFeed();
		contentFeed.setDescription(
				"Far far away, behind the word mountains, fa");
		contentFeed.setPoints(100);
		contentFeed.setImage(null);
		contentFeed.setInterest(music);
		contentFeed.setImageUrl("http://www.imageurl.com");

		Long id = entityManager.persistAndGetId(contentFeed, Long.class);
		Assert.notNull(id);
	}

	@Test
	public void whenFindByInterestID_then_ReturnContentFeed() {
		Interest music = new Interest();
		music.setInterestName("MUSIC");

		Interest dance = new Interest();
 		dance.setInterestName("DANCE");

		entityManager.persist(music);
		entityManager.persist(dance);

		ContentFeed contentFeed = new ContentFeed();
		contentFeed.setDescription(
				"Far far away, beuden flows by their placeen the all it is an almost unorthographic lif");
		contentFeed.setPoints(100);
		contentFeed.setImage(null);
		contentFeed.setInterest(music);
		contentFeed.setImageUrl("http://www.imageurl.com");
 
		ContentFeed c = entityManager.persistFlushFind(contentFeed);
		System.err.println(c);
		
		
 		Set<ContentFeed> contentFeeds = contentFeedRepository
				.findByInterestInterestIdIn(Arrays.asList(new Long(music.getInterestId())));
		System.err.println(contentFeeds);
		System.err.println(contentFeedRepository.findByInterest(new Long(music.getInterestId())));
		Assert.notNull(contentFeeds);
 
	}
}
