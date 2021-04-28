package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.OriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OriginRepository extends JpaRepository<OriginEntity, Integer> {
	@Query(nativeQuery = true,
			value = "SELECT * FROM ORIGINS"
					+ " WHERE ORIGIN_ID IN (SELECT DISTINCT ORIGIN_ID FROM POSTS_ORIGINS "
					+ " WHERE POST_ID IN (SELECT DISTINCT POST_ID FROM POSTS "
					+ " WHERE AUTHOR_ID IN (:userId) ))")
	@Modifying
	List<OriginEntity> findAllOriginsByUserId(Integer userId);
}
