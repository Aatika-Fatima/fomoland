package com.fomo.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IOperations<T extends Serializable> {

	T findOne(final long id); 

	//read all
	List<T> findAll();
	Page<T> findPaginated(int page, int size);
	
	//write all
	T create(final T t);
	T update(final T t);
	void delete(final T t);
	void deleteById(long entityId);
	
	void deleteAll();
}
