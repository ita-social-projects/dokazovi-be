package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

	@Query("SELECT COUNT(U) FROM user_entity U "
			+ "WHERE EXISTS(SELECT P.id FROM post_entity P WHERE P.author=U AND P.status=:postsStatus)")
	Integer countUsersWhereExistsPostWithStatus(PostStatus postsStatus);

	@Query(nativeQuery = true,
			value = "SELECT * FROM USERS U "
					+ "WHERE U.STATUS=:#{#userStatus.name()} "
					+ "ORDER BY RANDOM()")
	Page<UserEntity> findRandomUsersByStatus(UserStatus userStatus, Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT * FROM USERS U "
					+ "WHERE EXISTS("
					+ "		SELECT UD.DIRECTION_ID FROM USERS_DIRECTIONS UD "
					+ "		WHERE UD.USER_ID=U.USER_ID AND UD.DIRECTION_ID IN (:directionsIds) "
					+ ") AND U.STATUS=:#{#userStatus.name()}  "
					+ "ORDER BY RANDOM()")
	Page<UserEntity> findRandomUsersByDirectionsAndStatus(
			Iterable<Integer> directionsIds, UserStatus userStatus, Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT U.* FROM USERS U "
					+ "		LEFT OUTER JOIN POSTS P ON P.AUTHOR_ID = U.USER_ID "
					+ "WHERE U.STATUS = 'ACTIVE' "
					+ "GROUP BY U.USER_ID "
					+ "ORDER BY CASE "
					+ "				WHEN U.PROMOTION_LEVEL='TOP' THEN :allPublishedPostsCount "
					+ "				WHEN U.PROMOTION_LEVEL='PROMOTED' THEN :averagePublishedPostsPerUser "
					+ "				ELSE 0 "
					+ "			END + "
					+ "			SUM(CASE WHEN P.STATUS='PUBLISHED' THEN 1 ELSE 0 END) * U.PROMOTION_SCALE DESC, "
					+ "			U.USER_ID DESC")
	Page<UserEntity> findAllActiveUsersOrderByRating(
			Integer allPublishedPostsCount, Integer averagePublishedPostsPerUser, Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT U.* FROM USERS U "
					+ "		LEFT OUTER JOIN POSTS P ON P.AUTHOR_ID = U.USER_ID "
					+ "		LEFT OUTER JOIN INSTITUTIONS I ON U.INSTITUTION_ID = I.INSTITUTION_ID "
					+ "		LEFT OUTER JOIN CITIES C ON I.CITY_ID = C.CITY_ID "
					+ "WHERE U.STATUS = 'ACTIVE' "
					+ "		AND C.REGION_ID IN (:regionsIds) "
					+ "GROUP BY U.USER_ID "
					+ "ORDER BY CASE "
					+ "				WHEN U.PROMOTION_LEVEL='TOP' THEN :allPublishedPostsCount "
					+ "				WHEN U.PROMOTION_LEVEL='PROMOTED' THEN :averagePublishedPostsPerUser "
					+ "				ELSE 0 "
					+ "			END + "
					+ "			SUM(CASE WHEN P.STATUS='PUBLISHED' THEN 1 ELSE 0 END) * U.PROMOTION_SCALE DESC, "
					+ "			U.USER_ID DESC")
	Page<UserEntity> findAllActiveUsersByRegionsIdsInOrderByRating(
			Iterable<Integer> regionsIds, Integer allPublishedPostsCount, Integer averagePublishedPostsPerUser,
			Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT U.* FROM USERS U "
					+ "		LEFT OUTER JOIN POSTS P ON P.AUTHOR_ID = U.USER_ID "
					+ "		FULL JOIN USERS_DIRECTIONS UD ON U.USER_ID = UD.USER_ID "
					+ "WHERE U.STATUS='ACTIVE' "
					+ "		AND UD.DIRECTION_ID IN (:directionsIds) "
					+ "GROUP BY U.USER_ID,  "
					+ "ORDER BY COUNT(DISTINCT UD) DESC, "
					+ "			CASE "
					+ "				WHEN U.PROMOTION_LEVEL='TOP' THEN :allPublishedPostsCount "
					+ "				WHEN U.PROMOTION_LEVEL='PROMOTED' THEN :averagePublishedPostsPerUser "
					+ "				ELSE 0 "
					+ "			END + "
					+ "			SUM(CASE WHEN P.STATUS='PUBLISHED' THEN 1 ELSE 0 END) * U.PROMOTION_SCALE DESC, "
					+ "			U.USER_ID DESC")
	Page<UserEntity> findAllActiveUsersByDirectionsIdsInOrderByDirectionsMatchesThenByRating(
			Iterable<Integer> directionsIds, Integer allPublishedPostsCount, Integer averagePublishedPostsPerUser,
			Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT U.* "
					+ "FROM USERS U "
					+ "		LEFT OUTER JOIN POSTS P ON P.AUTHOR_ID = U.USER_ID "
					+ "		JOIN USERS_DIRECTIONS UD ON U.USER_ID = UD.USER_ID "
					+ "		LEFT OUTER JOIN INSTITUTIONS I ON U.INSTITUTION_ID = I.INSTITUTION_ID "
					+ "		LEFT OUTER JOIN CITIES C ON I.CITY_ID = C.CITY_ID "
					+ "WHERE U.STATUS = 'ACTIVE' "
					+ "		AND UD.DIRECTION_ID IN (:directionsIds) "
					+ "		AND C.REGION_ID IN (:regionsIds) "
					+ "GROUP BY U.USER_ID "
					+ "ORDER BY COUNT(DISTINCT UD) DESC, "
					+ "			CASE "
					+ "				WHEN U.PROMOTION_LEVEL='TOP' THEN :allPublishedPostsCount "
					+ "				WHEN U.PROMOTION_LEVEL='PROMOTED' THEN :averagePublishedPostsPerUser "
					+ "				ELSE 0 "
					+ "			END + "
					+ "			SUM(CASE WHEN P.STATUS='PUBLISHED' THEN 1 ELSE 0 END) * U.PROMOTION_SCALE DESC, "
					+ "			U.USER_ID DESC")
	Page<UserEntity> findAllActiveUsersByDirectionsIdsInAndRegionsIdsInOrderByDirectionsMatchesThenByRating(
			Iterable<Integer> directionsIds, Iterable<Integer> regionsIds, Integer allPublishedPostsCount,
			Integer averagePublishedPostsPerUser, Pageable pageable);
}
