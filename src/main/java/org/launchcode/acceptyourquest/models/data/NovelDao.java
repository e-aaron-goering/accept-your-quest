package org.launchcode.acceptyourquest.models.data;

import org.launchcode.acceptyourquest.models.Novel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface NovelDao extends CrudRepository<Novel, Integer> {

}