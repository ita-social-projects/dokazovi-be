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
					+ "     SELECT UD.DIRECTION_ID FROM USERS_DIRECTIONS UD "
					+ "     WHERE UD.USER_ID=U.USER_ID AND UD.DIRECTION_ID IN (:directionsIds) "
					+ ") AND U.STATUS=:#{#userStatus.name()}  "
					+ "ORDER BY RANDOM()")
	Page<UserEntity> findRandomUsersByDirectionsAndStatus(
			Iterable<Integer> directionsIds, UserStatus userStatus, Pageable pageable);

	@Query(value = "SELECT U FROM user_entity U "
				+ "      LEFT JOIN U.posts P "
				+ "WHERE U.status = 'ACTIVE' "
				+ "GROUP BY U.id "
				+ "ORDER BY CASE "
				+ "             WHEN U.promotionLevel='TOP' THEN :allPublishedPostsCount "
				+ "             WHEN U.promotionLevel='PROMOTED' THEN :averagePublishedPostsPerUser "
				+ "             ELSE 0.0 "
				+ "         END + SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) * U.promotionScale DESC, "
				+ "         U.id DESC ",
			countQuery = "SELECT COUNT(U) FROM user_entity U WHERE U.status = 'ACTIVE' GROUP BY U.id "
						+ "ORDER BY :averagePublishedPostsPerUser, :allPublishedPostsCount")
	Page<UserEntity> findAllActiveUsersOrderByRating(
			Double allPublishedPostsCount, Double averagePublishedPostsPerUser, Pageable pageable);

	@Query(value = "SELECT U FROM user_entity U "
				+ "       LEFT JOIN U.posts P "
				+ "WHERE U.status = 'ACTIVE' AND U.mainInstitution.city.region.id IN (:regionsIds) "
				+ "GROUP BY U.id "
				+ "ORDER BY	CASE "
				+ "             WHEN U.promotionLevel='TOP' THEN :allPublishedPostsCount "
				+ "             WHEN U.promotionLevel='PROMOTED' THEN :averagePublishedPostsPerUser "
				+ "             ELSE 0.0 "
				+ "         END + SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) * U.promotionScale DESC, "
				+ "         U.id DESC ",
			countQuery = "SELECT COUNT(U) FROM user_entity U "
						+ "     WHERE U.status = 'ACTIVE' AND U.mainInstitution.city.region.id IN (:regionsIds) "
						+ "GROUP BY U.id ORDER BY :averagePublishedPostsPerUser, :allPublishedPostsCount")
	Page<UserEntity> findAllActiveUsersByRegionsIdsInOrderByRating(
			Iterable<Integer> regionsIds, Double allPublishedPostsCount, Double averagePublishedPostsPerUser,
			Pageable pageable);

	@Query(value = "SELECT U FROM user_entity U "
				+ "     LEFT JOIN U.directions D "
				+ "     LEFT JOIN U.posts P "
				+ "WHERE U.status = 'ACTIVE' AND D.id IN (:directionsIds) "
				+ "GROUP BY U.id "
				+ "ORDER BY COUNT(DISTINCT D) DESC, "
				+ "         CASE "
				+ "             WHEN U.promotionLevel='TOP' THEN :allPublishedPostsCount "
				+ "             WHEN U.promotionLevel='PROMOTED' THEN :averagePublishedPostsPerUser "
				+ "             ELSE 0.0 "
				+ "         END + SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) * U.promotionScale DESC, "
				+ "         U.id DESC ",
			countQuery = "SELECT COUNT(U) FROM user_entity U INNER JOIN U.directions D "
						+ "WHERE U.status = 'ACTIVE' AND D.id IN (:directionsIds) "
						+ "GROUP BY U.id ORDER BY :averagePublishedPostsPerUser, :allPublishedPostsCount")
	Page<UserEntity> findAllActiveUsersByDirectionsIdsInOrderByDirectionsMatchesThenByRating(
			Iterable<Integer> directionsIds, Double allPublishedPostsCount, Double averagePublishedPostsPerUser,
			Pageable pageable);

	@Query(value = "SELECT U FROM user_entity U "
				+ "     LEFT JOIN U.directions D "
				+ "     LEFT JOIN U.posts P "
				+ "WHERE U.status = 'ACTIVE' AND D.id IN (:directionsIds) "
				+ "      AND U.mainInstitution.city.region.id IN (:regionsIds) "
				+ "GROUP BY U.id "
				+ "ORDER BY COUNT(DISTINCT D) DESC, "
				+ "         CASE "
				+ "             WHEN U.promotionLevel='TOP' THEN :allPublishedPostsCount "
				+ "             WHEN U.promotionLevel='PROMOTED' THEN :averagePublishedPostsPerUser "
				+ "         ELSE 0.0 "
				+ "         END + SUM(CASE WHEN P.status='PUBLISHED' THEN 1 ELSE 0 END) * U.promotionScale DESC, "
				+ "         U.id DESC ",
			countQuery = "SELECT COUNT(U) FROM user_entity U INNER JOIN U.directions D "
						+ "WHERE U.status = 'ACTIVE' AND D.id IN (:directionsIds) "
						+ "      AND U.mainInstitution.city.region.id IN (:regionsIds) "
						+ "GROUP BY U.id ORDER BY :averagePublishedPostsPerUser, :allPublishedPostsCount")
	Page<UserEntity> findAllActiveUsersByDirectionsIdsInAndRegionsIdsInOrderByDirectionsMatchesThenByRating(
			Iterable<Integer> directionsIds, Iterable<Integer> regionsIds, Double allPublishedPostsCount,
			Double averagePublishedPostsPerUser, Pageable pageable);
}
