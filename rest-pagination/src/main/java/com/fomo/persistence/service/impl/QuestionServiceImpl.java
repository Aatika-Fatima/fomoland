package com.fomo.persistence.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.fomo.persistence.dao.QuestionsRepository;
import com.fomo.persistence.model.Question;
import com.fomo.persistence.service.IQuestionService;
import com.fomo.persistence.service.common.AbstractService;

@Component
public class QuestionServiceImpl extends AbstractService<Question> implements IQuestionService {
	@Autowired
	QuestionsRepository questionRepository;

	public Set<Question> findByInterest(long interestId) {
		return questionRepository.findByInterest(interestId);
	}

	@Override
	protected PagingAndSortingRepository<Question, Long> getDao() {
		return questionRepository;
	}

	@Override
	public Set<Question> findByInterestInterestIdIn(List<Long> interestId) {
		return questionRepository.findByInterestInterestIdIn(interestId);
	}

	@Override
	public String findAnswerByQid(long qid) {

		return questionRepository.findAnswerByQid(qid);
	}

}
