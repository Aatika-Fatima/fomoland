package com.fomo.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import com.fomo.persistence.model.Option;

public interface OptionRepository extends CrudRepository<Option, Long>{

}
