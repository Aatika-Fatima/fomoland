package com.fomo.persistence.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.fomo.persistence.IOperations;
import com.fomo.persistence.model.ContentFeed;
import com.fomo.persistence.model.Interest;

public interface IContentFeedService extends IOperations<ContentFeed> {

	Page<ContentFeed> findPaginated(Pageable pageable);

	Page<ContentFeed> findPaginated(Pageable pageable, Set<Interest> interest);

	public Set<ContentFeed> findByInterest( long interestId);

	Set<ContentFeed> findByInterestInterestIdIn( List<Long> interestId);
}
