package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

	Page<PostEntity> findAllByImportantIsTrueAndStatus(PostStatus status, Pageable pageable);

	Page<PostEntity> findAllByStatus(PostStatus postStatus, Pageable pageable);

	Page<PostEntity> findAllByDirectionsContainsAndStatus(
			DirectionEntity direction, PostStatus postStatus, Pageable pageable);

	Page<PostEntity> findAllByDirectionsContainsAndTypeIdInAndStatus(
			DirectionEntity direction, Set<Integer> typeId, PostStatus postStatus, Pageable pageable);

	Page<PostEntity> findAllByDirectionsContainsAndTagsIdInAndStatus(
			DirectionEntity direction, Set<Integer> tagId, PostStatus postStatus, Pageable pageable);

	Page<PostEntity> findAllByDirectionsContainsAndTypeIdInAndTagsIdInAndStatus(
			DirectionEntity direction, Set<Integer> typeId, Set<Integer> tagId, PostStatus postStatus,
			Pageable pageable);

	Page<PostEntity> findAllByAuthorIdAndStatus(Integer authorId, PostStatus postStatus, Pageable pageable);

	Page<PostEntity> findAllByAuthorIdAndTypeIdInAndStatus(
			Integer authorId, Set<Integer> typeId, PostStatus postStatus, Pageable pageable);

	@Query(nativeQuery = true,
	value = " UPDATE POSTS "
			+ " SET IMPORTANT = FALSE, "
			+ "    IMPORTANCE_ORDER = NULL "
			+ " WHERE POST_ID > 0;")
	@Modifying
	void removeImportantPostsAndOrder(Set<Integer> importantPostIds);

	@Query(nativeQuery = true,
	value = " UPDATE POSTS "
			+ " SET IMPORTANCE_ORDER = (:postNumber), "
			+ "    IMPORTANT = TRUE "
			+ " WHERE POST_ID = (:postId) ")
	@Modifying
	void setImportantPostOrder(Integer postNumber, Integer postId);


	@Query(nativeQuery = true,
			value = "SELECT * FROM POSTS "
					+ " WHERE POSTS.STATUS IN ('PUBLISHED')"
					+ " ORDER BY POST_ID",
			countQuery = "SELECT COUNT(POSTS.POST_ID) FROM POSTS"
					+ " WHERE POSTS.STATUS IN ('PUBLISHED')")
	Page<PostEntity> findAll(Pageable pageable);

	@Query(nativeQuery = true,
			value = " SELECT P.* FROM POSTS P "
					+ " WHERE (P.AUTHOR_ID IN (:authorId)) "
					+ " AND(array_length(ARRAY[(:typesIds)],1)>0 "
					+ "    AND P.TYPE_ID IN (:typesIds)) "
					+ "  AND (array_length(ARRAY [(:directionsIds)], 1) > 0 "
					+ "    AND P.POST_ID IN (SELECT POST_ID "
					+ "                       FROM POSTS_DIRECTIONS "
					+ "                       WHERE DIRECTION_ID IN (:directionsIds)))"
					+ " AND P.STATUS IN ('PUBLISHED') ")
	Page<PostEntity> findAllByExpertAndByDirectionsAndByPostType(Integer authorId, Set<Integer> typesIds,
			Set<Integer> directionsIds, Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT P1.* "
					+ " FROM POSTS P1 "
					+ " WHERE ((array_length(ARRAY [(:typesIds)], 1) > 0 "
					+ "     AND P1.TYPE_ID IN (:typesIds)) "
					+ "   AND (array_length(ARRAY [(:directionsIds)], 1) > 0 "
					+ "     AND P1.POST_ID IN (SELECT POST_ID "
					+ "                        FROM POSTS_DIRECTIONS "
					+ "                        WHERE DIRECTION_ID IN (:directionsIds))) "
					+ "   AND (array_length(ARRAY [(:originsIds)], 1) > 0 "
					+ "     AND P1.POST_ID IN (SELECT POST_ID "
					+ "                        FROM POSTS_ORIGINS "
					+ "                        WHERE ORIGIN_ID IN (:originsIds)))) "
					+ "   AND P1.STATUS IN ('PUBLISHED') "
	)
	Page<PostEntity> findAllByDirectionsAndByPostTypesAndByOrigins(Set<Integer> typesIds, Set<Integer> originsIds,
			Set<Integer> directionsIds, Pageable pageable);

	@Query(nativeQuery = true,
			value = " SELECT P.* "
					+ " FROM POSTS P "
					+ " WHERE (P.AUTHOR_ID IN (:authorId)) "
					+ "  AND (array_length(ARRAY [(:directionsIds)], 1) > 0 "
					+ "    AND P.POST_ID IN (SELECT POST_ID "
					+ "                      FROM POSTS_DIRECTIONS "
					+ "                      WHERE DIRECTION_ID IN (:directionsIds))) "
					+ "  AND P.STATUS IN ('PUBLISHED')")
	Page<PostEntity> findPostsByAuthorIdAndDirections(Pageable pageable, Integer authorId, Set<Integer> directionsIds);

	@Query(nativeQuery = true,
			value = " SELECT P1.* "
					+ " FROM POSTS P1 "
					+ " WHERE (P1.POST_ID IN (SELECT POST_ID"
					+ "                      FROM POSTS_ORIGINS"
					+ "                      WHERE ORIGIN_ID = 1))"
					+ "  AND P1.STATUS IN ('PUBLISHED')"
					+ "  AND P1.TYPE_ID NOT IN (2)"
					+ " ORDER BY CREATED_AT DESC")
	Page<PostEntity> findLatestByPostTypeExpertOpinion(Pageable pageable);

	@Query(nativeQuery = true,
			value = " SELECT P1.* "
					+ " FROM POSTS P1 "
					+ " WHERE (P1.POST_ID IN (SELECT POST_ID "
					+ "                       FROM POSTS_ORIGINS "
					+ "                       WHERE ORIGIN_ID = 2)) "
					+ "   AND P1.STATUS IN ('PUBLISHED') "
					+ "   AND P1.TYPE_ID NOT IN (2) "
					+ " ORDER BY CREATED_AT DESC ")
	Page<PostEntity> findLatestByPostTypeMedia(Pageable pageable);


	@Query(nativeQuery = true,
			value = " SELECT P1.*"
					+ " FROM POSTS P1 "
					+ " WHERE (P1.POST_ID IN (SELECT POST_ID "
					+ "                      FROM POSTS_ORIGINS "
					+ "                      WHERE ORIGIN_ID = 3)) "
					+ "  AND P1.STATUS IN ('PUBLISHED') "
					+ "  AND P1.TYPE_ID NOT IN (2) "
					+ " ORDER BY CREATED_AT DESC ")
	Page<PostEntity> findLatestByPostTypeTranslation(Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT P1.* "
					+ "   FROM POSTS P1 "
					+ "   WHERE P1.TYPE_ID IN (2) "
					+ "   AND P1.STATUS IN ('PUBLISHED') "
					+ " ORDER BY CREATED_AT DESC ")
	Page<PostEntity> findLatestByOriginVideo(Pageable pageable);

	@Query(nativeQuery = true,
			value = "(SELECT * "
					+ " FROM posts "
					+ " WHERE IMPORTANT_IMAGE_URL <> '' "
					+ "   AND IMPORTANT_IMAGE_URL IS NOT NULL "
					+ " ORDER BY CREATED_AT DESC) "
					+ " UNION ALL "
					+ "(SELECT * "
					+ " FROM posts "
					+ " WHERE IMPORTANT_IMAGE_URL = '' "
					+ "    OR IMPORTANT_IMAGE_URL IS NULL "
					+ " ORDER BY CREATED_AT DESC) ")
	Page<PostEntity> findAllByImportantImageUrlDesc (Pageable pageable);
}
