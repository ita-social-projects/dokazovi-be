package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * The Direction Repository is responsible for encapsulation a set of Direction objects stored in the database and
 * operations that can be performed on them.
 */

@Repository
public interface DirectionRepository extends JpaRepository<DirectionEntity, Integer> {

	/**
	 * Updates the directions status. If the directions have at least
	 * one doctor, its status changes to "true". In other
	 * cases - "false"
	 */
	@Query(nativeQuery = true,
			value = " UPDATE DIRECTIONS"
					+ " SET HAS_DOCTORS = TRUE"
					+ " WHERE DIRECTION_ID IN (SELECT DISTINCT DIRECTION_ID FROM DOCTORS_DIRECTIONS) ")
	@Modifying
	void updateDirectionsHasDoctorsStatus();

	/**
	 * Updates the directions status. If the directions have at least
	 * one post, its status changes to "true". In other cases -
	 * "false"
	 */
	@Query(nativeQuery = true,
			value = "UPDATE DIRECTIONS d "
					+ " SET HAS_POSTS = (SELECT EXISTS "
					+ "		(SELECT 1 FROM DOCTOR_POST_DIRECTIONS "
					+ "			WHERE DIRECTION_ID = d.DIRECTION_ID "
					+ "           AND VISIBLE = TRUE "
					+ "			LIMIT 1)) "
					+ " WHERE DIRECTION_ID IN (:directions) ")
	@Modifying
	void updateDirectionsHasPostsStatus(Set<Integer> directions);

	/**
	 * Gets all directions by user id.
	 *
	 * @param userId user id from service
	 * @return returns the directions entity list
	 */
	@Query(nativeQuery = true,
			value = "SELECT * FROM public.directions WHERE direction_id"
					+ "  IN (SELECT DISTINCT direction_id FROM public.doctor_post_directions dpd"
					+ "			WHERE dpd.user_id = :userId)")
	@Modifying
	List<DirectionEntity> findAllDirectionsByUserId(Integer userId);

	/**
	 * Gets all directions of posts which user has
	 *
	 * @param id id of user
	 * @return list of directions
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM directions d "
			+ "WHERE d.direction_id IN "
			+ "(SELECT DISTINCT direction_id FROM public.doctor_post_directions "
			+ "WHERE doctor_id = (:doctorId))")
	List<DirectionEntity> findAllDirectionsOfPostsByUserId(@Param("doctorId") Integer id);
}

