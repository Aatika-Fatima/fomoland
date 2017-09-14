package com.fomo.persistence.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.fomo.exception.ResourceNotFoundException;
import com.fomo.persistence.dao.AccounQuestionSocialRepository;
import com.fomo.persistence.dao.AccountContentFeedSocialRepository;
import com.fomo.persistence.dao.AccountRepository;
import com.fomo.persistence.dao.InterestRepostiory;
import com.fomo.persistence.model.Account;
import com.fomo.persistence.model.AccountContentFeedSocial;
import com.fomo.persistence.model.AccountContentFeedSocialPK;
import com.fomo.persistence.model.AccountQuestionSocial;
import com.fomo.persistence.model.AccountQuestionSocialPK;
import com.fomo.persistence.model.Interest;
import com.fomo.persistence.model.Question;
import com.fomo.persistence.service.IAccountService;
import com.fomo.persistence.service.common.AbstractService;

@Component
public class AccountServiceImpl extends AbstractService<Account> implements IAccountService {

	@Autowired
	public AccountRepository accountRepository;

	@Autowired
	public InterestRepostiory interestRepository;

	@Autowired
	public AccounQuestionSocialRepository accounQuestionSocialRepository;

	@Autowired
	AccountContentFeedSocialRepository accountContentFeedSocialRepository;

	public void addInterests(String username, Collection<String> interestNames) {
		List<Interest> interests = interestRepository.findByInterestNameIn(interestNames);
		System.err.println(interests);

		Account account = accountRepository.findOne(Long.parseLong(username));
		System.err.println("account " + account.getId() + " " + account.getFirstName());
		account.setInterests(new HashSet<>(interests));
		accountRepository.save(account);
	}

	@Override
	public Integer getPointsById(long id) {
		try {
			return accountRepository.getPointsById(id);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Username " + id + " does not exist");
		}
	}

	@Override
	public boolean updatePoints(long id, long qid, long socialMediaId, com.fomo.persistence.model.ShareType shareType, int points,
			Date datedShared) {
		switch (shareType) {
		case QUESTION:
			return updateQuestionPoints(id, qid, socialMediaId, shareType, points, datedShared);
		case POLL:
			return true;
		case CONTENT_FEED:
			return updateContentFeedPoints(id, qid, socialMediaId, shareType, points, datedShared);
		}
		return false;

	}

	private boolean updateQuestionPoints(long id, long qid, long socialMediaId, com.fomo.persistence.model.ShareType shareType,
			int points,
			Date datedShared) {
		AccountQuestionSocial acq = new AccountQuestionSocial();
		acq.setId(new AccountQuestionSocialPK(id, qid, socialMediaId));
		acq.setShared(true);
		acq.setShareType(shareType);
		acq.setSharedDate(Calendar.getInstance().getTime());
		acq = accounQuestionSocialRepository.save(acq);
		
		Account account = accountRepository.findOne(id);
		int totalPoints = points + account.getPoints();
		account.setPoints(totalPoints);
		accountRepository.save(account);
	//	accountRepository.updatePointsById(id, points);
		if (acq == null) {
			throw new ResourceNotFoundException("cannot update Question points the data");
		}
		return true;

	}

	private boolean updateContentFeedPoints(long id, long qid, long socialMediaId, com.fomo.persistence.model.ShareType shareType,
			int points,
			Date datedShared) {
		AccountContentFeedSocial acq = new AccountContentFeedSocial();
		acq.setPk(new AccountContentFeedSocialPK(id, qid, socialMediaId));
		acq.setShared(true);
		acq.setShareType(shareType);
		acq.setSharedDate(Calendar.getInstance().getTime());
		acq = accountContentFeedSocialRepository.save(acq);
		
		Account account = accountRepository.findOne(id);
		int totalPoints = points + account.getPoints();
		account.setPoints(totalPoints);
		accountRepository.save(account);
		
 		if (acq == null) {
			throw new ResourceNotFoundException("cannot update Content feed points");
		}
		return true;
	}

	@Override
	public Set<Interest> getUserInterest(long id) {
		Set<Interest> interest = accountRepository.getUserInterest(id);
		return interest;
	}

	@Override
	protected PagingAndSortingRepository<Account, Long> getDao() {
		// TODO Auto-generated method stub
		return accountRepository;
	}

	@Override
	public List<Long> getUserInterestIds(long id) {
		Set<Interest> interests = accountRepository.getUserInterest(id);
		List<Long> interestIds = new ArrayList<>();
		for(Interest interest: interests){
			interestIds.add(interest.getInterestId());
		}
		return interestIds;
	}
	
	public Set<Question> getQuestionBasedOnInterest(){
		
		return null; 
	}

}
