package com.fomo.persistence.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.Account;
import com.fomo.persistence.model.Interest;
 
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByUsername(final String username);

	@Query(name="Account.getPointsById")
	public Integer getPointsById(@Param("id") long id);
	
	@Query(value="update Account set points = points + :points where id = :id")
	public Account updatePointsById(@Param("id")long id, @Param("points") int points);
	
	@Query(value="select a.interests from Account a where a.id = :id")
	public Set<Interest> getUserInterest(@Param("id") long id);
	 
	
	
}
