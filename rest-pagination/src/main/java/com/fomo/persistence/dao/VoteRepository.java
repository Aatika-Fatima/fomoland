package com.fomo.persistence.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
	@Query(value = "select v.* from Options o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
	public Iterable<Vote> findByPoll(Long pollId);
	
	@Query(value="select o.option_id,count(v.option_id) from Options o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID group by o.option_id", nativeQuery=true)
	public Iterable<Object[]> countVotesByOptions(Long pollid);
}
