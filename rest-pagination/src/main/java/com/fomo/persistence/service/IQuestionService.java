package com.fomo.persistence.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fomo.persistence.IOperations;
import com.fomo.persistence.model.ContentFeed;
import com.fomo.persistence.model.Question;

public interface IQuestionService extends IOperations<Question>{
 	public Set<Question> findByInterest(long interestId);
	
 	Set<Question> findByInterestInterestIdIn(List<Long> interestId);
 	public String findAnswerByQid(long qid);
}
