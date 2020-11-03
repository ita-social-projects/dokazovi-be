package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTypeRepository extends JpaRepository<PostType, Integer> {
}
