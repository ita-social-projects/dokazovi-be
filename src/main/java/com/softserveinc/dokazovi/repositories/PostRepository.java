package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.SqlResultSetMapping;
import java.sql.Timestamp;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

	Page<PostEntity> findAllByImportantIsTrueAndStatusOrderByImportanceOrder(PostStatus status, Pageable pageable);

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

	Page<PostEntity> findAllByAuthorIdAndStatusOrderByPublishedAtDesc(Integer authorId, PostStatus postStatus,
			Pageable pageable);

	@Query(value = "SELECT p.fakeViews FROM post_entity p WHERE p.id = :postId")
	Integer getFakeViewsByPostId(Integer postId);

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
					+ " ORDER BY PUBLISHED_AT DESC, P1.POST_ID DESC")
	Page<PostEntity> findLatestByPostTypeExpertOpinion(Pageable pageable);

	@Query(nativeQuery = true,
			value = " SELECT P1.* "
					+ " FROM POSTS P1 "
					+ " WHERE (P1.POST_ID IN (SELECT POST_ID "
					+ "                       FROM POSTS_ORIGINS "
					+ "                       WHERE ORIGIN_ID = 2)) "
					+ "   AND P1.STATUS IN ('PUBLISHED') "
					+ "   AND P1.TYPE_ID NOT IN (2) "
					+ " ORDER BY PUBLISHED_AT DESC, P1.POST_ID DESC ")
	Page<PostEntity> findLatestByPostTypeMedia(Pageable pageable);


	@Query(nativeQuery = true,
			value = " SELECT P1.*"
					+ " FROM POSTS P1 "
					+ " WHERE (P1.POST_ID IN (SELECT POST_ID "
					+ "                      FROM POSTS_ORIGINS "
					+ "                      WHERE ORIGIN_ID = 3)) "
					+ "  AND P1.STATUS IN ('PUBLISHED') "
					+ "  AND P1.TYPE_ID NOT IN (2) "
					+ " ORDER BY PUBLISHED_AT DESC, P1.POST_ID DESC ")
	Page<PostEntity> findLatestByPostTypeTranslation(Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT P1.* "
					+ "   FROM POSTS P1 "
					+ "   WHERE P1.TYPE_ID IN (2) "
					+ "   AND P1.STATUS IN ('PUBLISHED') "
					+ " ORDER BY PUBLISHED_AT DESC, P1.POST_ID DESC ")
	Page<PostEntity> findLatestByOriginVideo(Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT P1.* FROM POSTS P1 "
					+ "WHERE P1.STATUS = :#{#postStatus.name()} "
					+ "  AND P1.IMPORTANT = :important "
					+ "  AND CASE WHEN :typesIds IS NOT NULL "
					+ "           THEN P1.TYPE_ID IN (:typesIds) "
					+ "           ELSE P1.POST_ID IS NOT NULL "
					+ "      END "
					+ "  AND CASE WHEN :directionsIds IS NOT NULL "
					+ "           THEN P1.POST_ID IN "
					+ "                  (SELECT POST_ID FROM POSTS_DIRECTIONS WHERE DIRECTION_ID IN (:directionsIds)) "
					+ "           ELSE P1.POST_ID IS NOT NULL "
					+ "      END "
					+ "  AND CASE WHEN :originsIds IS NOT NULL "
					+ "           THEN P1.POST_ID IN "
					+ "                  (SELECT POST_ID FROM POSTS_ORIGINS WHERE ORIGIN_ID IN (:originsIds)) "
					+ "           ELSE P1.POST_ID IS NOT NULL "
					+ "      END "
					+ "ORDER BY (P1.IMPORTANT_IMAGE_URL <> '' AND P1.IMPORTANT_IMAGE_URL IS NOT NULL) DESC, "
					+ "          P1.PUBLISHED_AT DESC, P1.POST_ID "
	)
	Page<PostEntity> findByDirectionsAndTypesAndOriginsAndStatusAndImportantSortedByImportantImagePresence(
			Set<Integer> directionsIds, Set<Integer> typesIds, Set<Integer> originsIds, PostStatus postStatus,
			Boolean important, Pageable pageable);


	@Query(nativeQuery = true,
			value = "SELECT * FROM posts p "
					+ "WHERE CASE WHEN :typeIds IS NOT NULL "
						+ "THEN p.type_id IN (:typeIds) "
						+ "ELSE p.post_id IS NOT NULL "
						+ "END "
					+ "AND CASE WHEN :directionIds IS NOT NULL "
						+ "THEN p.post_id IN "
						+ "(SELECT pd.post_id FROM posts_directions pd WHERE pd.direction_id IN (:directionIds)) "
						+ "ELSE p.post_id IS NOT NULL "
						+ "END "
					+ "AND CASE WHEN :originIds IS NOT NULL "
						+ "THEN p.post_id IN "
						+ "(SELECT po.post_id FROM posts_origins po WHERE po.origin_id IN (:originIds)) "
						+ "ELSE p.post_id IS NOT NULL "
						+ "END "
					+ "AND CASE WHEN :statuses IS NOT NULL "
						+ "THEN p.status IN (:statuses) "
						+ "ELSE p.post_id IS NOT NULL "
						+ "END "
					+ "AND p.modified_at between :startDate and :endDate "
					+ "AND p.author_id IN "
						+ "(SELECT user_id FROM users u "
						+ "WHERE UPPER((u.first_name || ' ' || u.last_name) COLLATE \"uk-ua-dokazovi-x-icu\") "
							+ "LIKE UPPER((:author || '%') COLLATE \"uk-ua-dokazovi-x-icu\") "
						+ "OR UPPER((u.last_name || ' ' || u.first_name) COLLATE \"uk-ua-dokazovi-x-icu\") "
							+ "LIKE UPPER((:author || '%') COLLATE \"uk-ua-dokazovi-x-icu\")) "
					+ "AND p.title LIKE (:title || '%')")
	Page<PostEntity> findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(Set<Integer> typeIds,
			Set<Integer> directionIds, Set<String> statuses, Set<Integer> originIds, String title, String author,
			Timestamp startDate, Timestamp endDate,Pageable pageable);
}
