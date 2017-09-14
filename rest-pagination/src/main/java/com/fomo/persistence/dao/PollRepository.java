package com.fomo.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.Poll;
@Repository
public interface PollRepository extends CrudRepository<Poll, Long>{

}
