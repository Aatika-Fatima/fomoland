package com.fomo.persistence.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.Question;

@Repository
public interface QuestionsRepository extends JpaRepository<Question,Long>{
	@Query("select q from Question q where q.interest.interestId=:interestId")
	public Set<Question> findByInterest(@Param("interestId") long interestId);
	
 	Set<Question> findByInterestInterestIdIn(@Param("interestId")List<Long> interestId);
 	
 	@Query("select q.answer from Question q where q.qid=:qid")
 	public String findAnswerByQid(@Param("qid")long qid);
 	
 	@Query("select q.qid from Question q where q.interest.interestId =:interestId and q.qid not in ( select s.question.qid from AccountQuestionSocial s where s.account.id =:accountid and s.sharedDate < :sharedDate)")
 	public List<Long> getQuestionIds(@Param("interestId") long interestId, @Param("accountid") long accountid, @Param("sharedDate") Date sharedDate);
 	 
}
