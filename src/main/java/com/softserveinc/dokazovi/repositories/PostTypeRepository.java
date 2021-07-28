package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTypeRepository extends JpaRepository<PostTypeEntity, Integer> {

	@Query(nativeQuery = true,
			value = " SELECT * "
					+ " FROM POST_TYPES"
					+ " WHERE (TYPE_ID IN (SELECT DISTINCT P.TYPE_ID "
					+ "                   FROM POSTS P "
					+ "                   WHERE P.AUTHOR_ID IN (:userId))) ")
	List<PostTypeEntity> findAllPostTypesByUserId(Integer userId);

	@Query(nativeQuery = true,
			value = " SELECT * "
					+ " FROM POST_TYPES"
					+ " WHERE TYPE_ID IN (SELECT DISTINCT P.TYPE_ID "
					+ "                   FROM POSTS P "
					+ "                   WHERE P.AUTHOR_ID = :userId "
					+ "                     AND P.status = :#{#postStatus?.name()}) ")
	List<PostTypeEntity> findAllPostTypesByUserIdAndStatus(Integer userId, PostStatus postStatus);
}
