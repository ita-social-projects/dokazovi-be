package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

	@Query("SELECT U FROM user_entity AS U "
			+ "		LEFT OUTER JOIN post_entity P ON P.author = U "
			+ "WHERE U.status = :userStatus "
			+ "GROUP BY U "
			+ "ORDER BY SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) + U.promotionLevel DESC, "
			+ "			U.id DESC")
	Page<UserEntity> findAllByStatusOrderByRating(UserStatus userStatus, Pageable pageable);

	@Query("SELECT U FROM user_entity AS U "
			+ "		LEFT OUTER JOIN post_entity P ON P.author = U "
			+ "WHERE U.status = :userStatus "
			+ "		AND U.mainInstitution.city.region.id IN (:regionsIds) "
			+ "GROUP BY U "
			+ "ORDER BY SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) + U.promotionLevel DESC, "
			+ "			U.id DESC")
	Page<UserEntity> findAllByRegionsIdsInAndStatusOrderByRating(Iterable<Integer> regionsIds,
			UserStatus userStatus, Pageable pageable);

	@Query("SELECT U FROM user_entity AS U "
			+ "		INNER JOIN U.directions UD "
			+ "		LEFT OUTER JOIN post_entity P ON P.author = U "
			+ "WHERE U.status = :userStatus "
			+ "		AND UD.id IN (:directionsIds) "
			+ "GROUP BY U "
			+ "ORDER BY COUNT(DISTINCT UD) DESC, "
			+ "			SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) + U.promotionLevel DESC, "
			+ "			U.id DESC")
	Page<UserEntity> findAllByDirectionsIdsInAndStatusOrderByDirectionsMatchesThenByRating(
			Iterable<Integer> directionsIds, UserStatus userStatus, Pageable pageable);

	@Query("SELECT U FROM user_entity AS U "
			+ "		INNER JOIN U.directions UD "
			+ "		LEFT OUTER JOIN post_entity P ON P.author = U "
			+ "WHERE U.status = :userStatus "
			+ "		AND UD.id IN (:directionsIds) "
			+ "		AND U.mainInstitution.city.region.id IN (:regionsIds) "
			+ "GROUP BY U "
			+ "ORDER BY COUNT(DISTINCT UD) DESC, "
			+ "			SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) + U.promotionLevel DESC, "
			+ "			U.id DESC")
	Page<UserEntity> findAllByDirectionsIdsInAndRegionsIdsInAndStatusOrderByDirectionsMatchesThenByRating(
			Iterable<Integer> directionsIds, Iterable<Integer> regionsIds, UserStatus userStatus, Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT * FROM USERS U "
					+ "	WHERE U.STATUS=:#{#status.name()} "
					+ "ORDER BY RANDOM()")
	Page<UserEntity> findRandomUsersByStatus(@Param("status") UserStatus userStatus, Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT * FROM USERS U "
					+ "WHERE EXISTS("
					+ "		SELECT UD.DIRECTION_ID FROM USERS_DIRECTIONS UD "
					+ "		WHERE UD.DIRECTION_ID IN (:directionsIds) "
					+ "			AND UD.USER_ID=U.USER_ID "
					+ ") "
					+ "		AND U.STATUS=:#{#status.name()}  "
					+ "ORDER BY RANDOM()")
	Page<UserEntity> findRandomUsersByDirectionsAndStatus(@Param("directionsIds") Iterable<Integer> directionsIds,
			@Param("status") UserStatus userStatus, Pageable pageable);
}
