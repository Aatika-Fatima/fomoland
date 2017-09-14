package com.fomo.persistence.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.ContentFeed;

@Repository
public interface ContentFeedRepository extends PagingAndSortingRepository<ContentFeed, Long> {
	@Query("select c from ContentFeed c where c.interest.interestId=:interestId")
	public Set<ContentFeed> findByInterest(@Param("interestId") long interestId);
	
 	Set<ContentFeed> findByInterestInterestIdIn(@Param("interestId")List<Long> interestId);
 	
 	@Query("select c.contentFeedId from ContentFeed c where c.interest.interestId =:interestId and c.contentFeedId not in ( select s.contentFeed.contentFeedId from AccountContentFeedSocial s where s.account.id =:accountid and s.sharedDate < :sharedDate)")
 	public List<Long> getContentFeedIds(@Param("interestId") long interestId, @Param("accountid") long accountid, @Param("sharedDate") Date sharedDate);
}
