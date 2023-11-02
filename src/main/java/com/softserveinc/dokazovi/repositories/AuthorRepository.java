package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    AuthorEntity getByProfileId(Integer profileId);

    List<AuthorEntity> findAllByProfileIdIn(Set<Integer> userIds);

}
