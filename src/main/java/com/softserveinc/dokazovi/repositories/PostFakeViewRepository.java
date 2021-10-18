package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostFakeViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostFakeViewRepository extends JpaRepository<PostFakeViewEntity, Integer> {

	Optional<PostFakeViewEntity> getPostFakeViewEntityByPostId(Integer postId);

}
