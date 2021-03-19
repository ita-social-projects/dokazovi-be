package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
 //Todo: get PUBLISHED POSTS
	@Query(nativeQuery = true,
			value = "SELECT P.* FROM ("
					+ "    SELECT POST_ID FROM POSTS P"
					+ "        JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "        JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "        WHERE T.TYPE_ID IN (:typesIds)"
					+ "    ) POSTS_TYPES"
					+ "		JOIN("
					+ "    SELECT PD.POST_ID, COUNT(PD.DIRECTION_ID) DIR_MATCHED"
					+ "    FROM POSTS_DIRECTIONS PD"
					+ "    WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ "    GROUP BY PD.POST_ID"
					+ ") POSTS_DIR ON POSTS_TYPES.POST_ID=POSTS_DIR.POST_ID"
					+ "		JOIN ("
					+ "    SELECT PO.POST_ID, COUNT(PO.ORIGIN_ID) ORIG_MATCHED"
					+ "    FROM POSTS_ORIGINS PO"
					+ "    WHERE PO.ORIGIN_ID IN (:originsIds)"
					+ "    GROUP BY PO.POST_ID"
					+ "    ) POSTS_ORIG ON POSTS_DIR.POST_ID = POSTS_ORIG.POST_ID"
					+ "    JOIN POSTS P"
					+ "        ON POSTS_DIR.POST_ID=P.POST_ID"
					+ "ORDER BY P.POST_ID",
			countQuery = "SELECT COUNT(POSTS_DIR.POST_ID) FROM ("
					+ "SELECT POST_ID FROM POSTS P"
					+ "        JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "        JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "    WHERE T.TYPE_ID IN (:typesIds)"
					+ ") POSTS_TYPES"
					+ "		JOIN ("
					+ "SELECT DISTINCT PD.POST_ID FROM POSTS_DIRECTIONS PD"
					+ "WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ ") POSTS_DIR ON POSTS_TYPES.POST_ID=POSTS_DIR.POST_ID"
					+ "JOIN ("
					+ "    SELECT DISTINCT PO.POST_ID FROM POSTS_ORIGINS PO"
					+ "    WHERE PO.ORIGIN_ID in (:originsIds)"
					+ "    ) POSTS_ORIG ON POSTS_DIR.POST_ID = POSTS_ORIG.POST_ID")
	Page<PostEntity> findAllByDirectionsAndByPostTypesAndByOrigins(
			Set<Integer> directionsIds, Set<Integer> typesIds, Set<Integer> originsIds, Pageable pageable);
	//Todo: get PUBLISHED POSTS
	@Query(nativeQuery = true,
			value = "SELECT P.* FROM ("
					+ "                    SELECT PD.POST_ID"
					+ "                    FROM POSTS_DIRECTIONS PD"
					+ "                    WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ "                    GROUP BY PD.POST_ID"
					+ "                ) POSTS_DIR"
					+ "                    JOIN POSTS P ON POSTS_DIR.POST_ID=P.POST_ID"
					+ "	ORDER BY POST_ID",
			countQuery = "SELECT COUNT(POSTS_DIR.POST_ID) FROM("
					+ "    SELECT DISTINCT PD.POST_ID FROM POSTS_DIRECTIONS PD"
					+ "    WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ ") POSTS_DIR")
	Page<PostEntity> findAllByDirections(Set<Integer> directionsIds, Pageable pageable);
	//Todo: get PUBLISHED POSTS
	@Query(nativeQuery = true,
			value = " SELECT P.* FROM ("
					+ "        SELECT POST_ID FROM POSTS P"
					+ "                JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "                JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "                WHERE T.TYPE_ID IN (:typesIds)"
					+ "        ) POSTS_TYPES"
					+ "	JOIN POSTS P ON POSTS_TYPES.POST_ID=P.POST_ID"
					+ "	ORDER BY POST_ID",
			countQuery = "	SELECT COUNT(POSTS_TYPES.POST_ID) FROM ("
					+ "        SELECT POST_ID FROM POSTS P"
					+ "                JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "                JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "        WHERE T.TYPE_ID IN (:typesIds)"
					+ "   ) POSTS_TYPES")
	Page<PostEntity> findAllByPostTypes(Set<Integer> typesIds, Pageable pageable);
	//Todo: get PUBLISHED POSTS
	@Query(nativeQuery = true,
			value = "SELECT P.* FROM ("
					+ "                    SELECT PO.POST_ID"
					+ "                    FROM POSTS_ORIGINS PO"
					+ "                    WHERE PO.ORIGIN_ID IN (:originsIds)"
					+ "                    GROUP BY PO.POST_ID"
					+ "                ) POSTS_ORIG"
					+ "                    JOIN POSTS P ON POSTS_ORIG.POST_ID=P.POST_ID"
					+ "	ORDER BY POST_ID",
			countQuery = "SELECT COUNT(POSTS_ORIG.POST_ID) FROM("
					+ "    SELECT DISTINCT PO.POST_ID FROM POSTS_ORIGINS PO"
					+ "    WHERE PO.ORIGIN_ID IN (:originsIds)"
					+ ") POSTS_ORIG")
	Page<PostEntity> findAllByOrigins(Set<Integer> originsIds, Pageable pageable);
	//Todo: get PUBLISHED POSTS
	@Query(nativeQuery = true,
			value = "SELECT P.* FROM ("
					+ "   SELECT POST_ID FROM POSTS P"
					+ "                JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "                JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "                WHERE T.TYPE_ID IN (:typesIds)"
					+ "        ) POSTS_TYPES"
					+ " JOIN("
					+ "    SELECT PD.POST_ID, COUNT(PD.DIRECTION_ID) DIR_MATCHED"
					+ "    FROM POSTS_DIRECTIONS PD"
					+ "    WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ "    GROUP BY PD.POST_ID"
					+ ") POSTS_DIR ON POSTS_TYPES.POST_ID=POSTS_DIR.POST_ID"
					+ "	JOIN POSTS P ON POSTS_TYPES.POST_ID=P.POST_ID"
					+ "	ORDER BY POST_ID",
			countQuery = "SELECT COUNT(POSTS_TYPES.POST_ID)"
					+ "	FROM ("
					+ "    SELECT POST_ID"
					+ "    FROM POSTS P"
					+ "        JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "        JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "        WHERE T.TYPE_ID IN (:typesIds)"
					+ "     ) POSTS_TYPES"
					+ " JOIN ("
					+ " SELECT DISTINCT PD.POST_ID"
					+ "    FROM POSTS_DIRECTIONS PD"
					+ "    WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ "	) POSTS_DIR ON POSTS_TYPES.POST_ID = POSTS_DIR.POST_ID")
	Page<PostEntity> findAllByDirectionsAndByPostTypes(Set<Integer> directionsIds, Set<Integer> typesIds,
			Pageable pageable);
	//Todo: get PUBLISHED POSTS
	@Query(nativeQuery = true,
			value = "SELECT P.*"
					+ "	FROM ("
					+ "         SELECT PO.POST_ID, COUNT(PO.ORIGIN_ID) ORIG_MATCHED"
					+ "         FROM POSTS_ORIGINS PO"
					+ "         WHERE PO.ORIGIN_ID IN (:originsIds)"
					+ "         GROUP BY PO.POST_ID"
					+ "     ) POSTS_ORIG"
					+ " JOIN("
					+ "    SELECT PD.POST_ID, COUNT(PD.DIRECTION_ID) DIR_MATCHED"
					+ "    FROM POSTS_DIRECTIONS PD"
					+ "    WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ "    GROUP BY PD.POST_ID"
					+ "		) POSTS_DIR ON POSTS_ORIG.POST_ID = POSTS_DIR.POST_ID"
					+ " JOIN POSTS P ON POSTS_ORIG.POST_ID = P.POST_ID"
					+ "	ORDER BY POST_ID",
			countQuery = "SELECT COUNT(POSTS_ORIG.POST_ID)"
					+ "FROM  ("
					+ "    SELECT DISTINCT PO.POST_ID FROM POSTS_ORIGINS PO"
					+ "    WHERE PO.ORIGIN_ID in (:originsIds)"
					+ "    ) POSTS_ORIG"
					+ "	JOIN ("
					+ "    SELECT DISTINCT PD.POST_ID FROM POSTS_DIRECTIONS PD"
					+ "    WHERE PD.DIRECTION_ID IN (:directionsIds)"
					+ "		) POSTS_DIR ON POSTS_ORIG.POST_ID = POSTS_DIR.POST_ID")
	Page<PostEntity> findAllByDirectionsAndByOrigins(Set<Integer> directionsIds, Set<Integer> originsIds,
			Pageable pageable);
	//Todo: get PUBLISHED POSTS - DONE
	@Query(nativeQuery = true,
			value = "SELECT P.*"
					+ "FROM ("
					+ "         SELECT PO.POST_ID, COUNT(PO.ORIGIN_ID) ORIG_MATCHED"
					+ "         FROM POSTS_ORIGINS PO"
					+ "         WHERE PO.ORIGIN_ID IN (:originsIds)"
					+ "         GROUP BY PO.POST_ID"
					+ "     ) POSTS_ORIG"
					+ "         JOIN("
					+ "    SELECT POST_ID"
					+ "    FROM POSTS P"
					+ "             JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "             JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "    WHERE T.TYPE_ID IN (:typesIds)"
					+ ") POST_TYPES ON POSTS_ORIG.POST_ID = POST_TYPES.POST_ID"
					+ "         JOIN POSTS P ON POSTS_ORIG.POST_ID = P.POST_ID"
					+ "        WHERE P.STATUS IN ('PUBLISHED')"
					+ "ORDER BY POST_ID",
			countQuery = "SELECT COUNT(POSTS_ORIG.POST_ID)\n"
					+ "FROM ("
					+ "         SELECT DISTINCT PO.POST_ID"
					+ "         FROM POSTS_ORIGINS PO"
					+ "         WHERE PO.ORIGIN_ID in (:originsIds)"
					+ "     ) POSTS_ORIG"
					+ "         JOIN ("
					+ "    SELECT POST_ID"
					+ "    FROM POSTS P"
					+ "             JOIN USERS U ON P.AUTHOR_ID = U.USER_ID"
					+ "             JOIN POST_TYPES T ON P.TYPE_ID = T.TYPE_ID"
					+ "    WHERE T.TYPE_ID IN (:typesIds)"
					+ "      AND P.STATUS IN ('PUBLISHED')"
					+ ") POST_TYPES ON POSTS_ORIG.POST_ID = POST_TYPES.POST_ID")
	Page<PostEntity> findAllByPostTypesAndByOrigins(Set<Integer> typesIds, Set<Integer> originsIds,
			Pageable pageable);
	//Todo: get PUBLISHED POSTS - DONE
	@Query(nativeQuery = true,
			value = "SELECT * FROM POSTS\n"
					+ " WHERE POSTS.STATUS IN ('PUBLISHED')"
					+ " ORDER BY POST_ID",
			countQuery = "SELECT COUNT(POSTS.POST_ID) FROM POSTS"
					+ " WHERE POSTS.STATUS IN ('PUBLISHED')")
	Page<PostEntity> findAll(Pageable pageable);
}
