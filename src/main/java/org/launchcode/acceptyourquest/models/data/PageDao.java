package org.launchcode.acceptyourquest.models.data;

import org.launchcode.acceptyourquest.models.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PageDao extends CrudRepository<Page, Integer> {

}