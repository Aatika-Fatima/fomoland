package com.fomo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fomo.persistence.model.AccountContentFeedSocial;
import com.fomo.persistence.model.AccountContentFeedSocialPK;
import com.fomo.persistence.model.AccountQuestionSocial;
import com.fomo.persistence.model.AccountQuestionSocialPK;
@Repository
public interface AccountContentFeedSocialRepository extends JpaRepository<AccountContentFeedSocial, AccountContentFeedSocialPK>{

}
