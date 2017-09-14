package com.fomo.persistence.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.Interest;

@Repository
public interface InterestRepostiory extends JpaRepository<Interest, Long>{
	List<Interest> findByInterestNameIn(Collection<String> interestNames);
}
