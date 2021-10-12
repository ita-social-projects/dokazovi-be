package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostFakeViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PostFakeViewRepository extends JpaRepository<PostFakeViewEntity, Integer> {

	Optional<PostFakeViewEntity> getPostFakeViewEntityByPostId(Integer postId);

	@Transactional
	@Query(value = "UPDATE public.post_fake_views SET views = 0 WHERE post_id = :postId", nativeQuery = true)
	@Modifying
	void resetFakeViews(@Param("postId") Integer postId);
}
