package com.fomo.persistence.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fomo.persistence.IOperations;
import com.google.common.collect.Lists;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {
	protected abstract PagingAndSortingRepository<T, Long> getDao();

	@Override
	@Transactional(readOnly = true)
	public T findOne(long id) {
		// TODO Auto-generated method stub
		return getDao().findOne(id);
	}

	@Override
    @Transactional(readOnly = true)
	public List<T> findAll() {
		return Lists.newArrayList(getDao().findAll());
	}

	@Override
	public Page<T> findPaginated(int page, int size) {
 		return getDao().findAll(new PageRequest(page, size));
	}

	@Override
	public T create(T t) {
		// TODO Auto-generated method stub
		return getDao().save(t);
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return getDao().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		getDao().delete(t);
	}

	@Override
	public void deleteById(long entityId) {
		getDao().delete(entityId);

	}
	
	@Override
	public void deleteAll() {
		getDao().deleteAll();
	}

}
