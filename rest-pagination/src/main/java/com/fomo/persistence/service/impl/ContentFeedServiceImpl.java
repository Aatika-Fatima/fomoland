package com.fomo.persistence.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fomo.persistence.dao.ContentFeedRepository;
import com.fomo.persistence.model.ContentFeed;
import com.fomo.persistence.model.Interest;
import com.fomo.persistence.service.IContentFeedService;
import com.fomo.persistence.service.common.AbstractService;

@Component
@Transactional
public class ContentFeedServiceImpl extends AbstractService<ContentFeed> implements IContentFeedService {

	@Autowired
	ContentFeedRepository contentFeedRepository;

	// API

	@Override
	protected PagingAndSortingRepository<ContentFeed, Long> getDao() {
		return contentFeedRepository;
	}

	@Override
	public Page<ContentFeed> findPaginated(Pageable pageable) {
		// TODO Auto-generated method stub
		return contentFeedRepository.findAll(pageable);
	}

	@Override
	public Page<ContentFeed> findPaginated(Pageable pageable, Set<Interest> interest) {

		return null;

	}

	@Override
	public Set<ContentFeed> findByInterest(long interestId) {

		return contentFeedRepository.findByInterest(interestId);
	}

	@Override
	public Set<ContentFeed> findByInterestInterestIdIn(List<Long> interestId) {
		// TODO Auto-generated method stub
		return contentFeedRepository.findByInterestInterestIdIn(interestId);
	}

}
