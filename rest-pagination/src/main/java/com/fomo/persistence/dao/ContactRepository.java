package com.fomo.persistence.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.Contact;
@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long>{

}
