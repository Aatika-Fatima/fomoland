package com.fomo.persistence.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fomo.persistence.IOperations;
import com.fomo.persistence.model.Account;
import com.fomo.persistence.model.Interest;
import com.fomo.persistence.model.ShareType;

public interface IAccountService  extends IOperations<Account>{
	public void addInterests(String username, Collection<String> interestNames);
	public Integer getPointsById(long id);
	public boolean updatePoints(long id, long qid, long socialMediaId, ShareType shareType,int points, Date datedShared); 
	public Set<Interest> getUserInterest(long id);
 	public List<Long> getUserInterestIds( long id);

}
