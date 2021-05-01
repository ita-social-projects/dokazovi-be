package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The User Repository is responsible for encapsulation a set of
 * User objects stored in the database and operations that can be performed on them. *
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	/**
	 * Gets the user by its email.
	 *
	 * @param email user email from service
	 * @return optional user entity page
	 */
	@Query(nativeQuery = true,
			value = "SELECT * FROM users u WHERE user_id= ("
					+ "SELECT  u.user_id FROM providers u WHERE  u.email IN (:email) LIMIT 1)")
	Optional<UserEntity> findByEmail(@Param("email") String email);

	/**
	 * Gets the page of random doctors.
	 *
	 * @param pageable interface for pagination information received from user service
	 * @return the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = " SELECT U.* FROM DOCTORS D "
					+ "     JOIN USERS U ON D.USER_ID = U.USER_ID "
					+ " ORDER BY RANDOM() ")
	Page<UserEntity> findRandomExperts(Pageable pageable);

	/**
	 * Gets the page of doctors by directions id.
	 *
	 * @param pageable interface for pagination information received from user service
	 * @param directionsIds received from user service
	 * @return the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = " SELECT U.* FROM ( "
					+ "     SELECT DD.DOCTOR_ID FROM DOCTORS_DIRECTIONS DD "
					+ "     WHERE DD.DIRECTION_ID IN (:directionsIds) "
					+ "     GROUP BY DD.DOCTOR_ID "
					+ " ) DOCS_DIRS "
					+ "     JOIN DOCTORS D ON DOCS_DIRS.DOCTOR_ID=D.DOCTOR_ID "
					+ "     JOIN USERS U ON D.USER_ID = U.USER_ID "
					+ " ORDER BY RANDOM() ",
			countQuery = " SELECT COUNT(DISTINCT DD.DOCTOR_ID) FROM DOCTORS_DIRECTIONS DD "
					+ " WHERE DD.DIRECTION_ID IN (:directionsIds) ")
	Page<UserEntity> findRandomExpertsByDirectionsIdIn(Iterable<Integer> directionsIds, Pageable pageable);

	/**
	 * Gets the page of doctors order by promotion level and rating.
	 *
	 * @param pageable interface for pagination information received from user service
	 * @return the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = " SELECT U.* FROM DOCTORS D "
					+ "     JOIN USERS U ON U.USER_ID = D.USER_ID "
					+ " ORDER BY D.PROMOTION_LEVEL DESC, D.RATING DESC, "
					+ "          U.LAST_NAME, U.FIRST_NAME ")
	Page<UserEntity> findDoctorsProfiles(Pageable pageable);

	/**
	 * Gets the page of doctors by directions ids and regions Ids.
	 *
	 * @param pageable interface for pagination information received from user service
	 * @param directionsIds received from user service
	 * @param regionsIds received from user service
	 * @return the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = " SELECT U.* FROM ( "
					+ "     SELECT DOCTOR_ID FROM DOCTORS D "
					+ "         JOIN INSTITUTIONS I ON D.INSTITUTION_ID=I.INSTITUTION_ID "
					+ "         JOIN CITIES C ON I.CITY_ID=C.CITY_ID "
					+ "     WHERE C.REGION_ID IN (:regionsIds) "
					+ " ) DOCS_REG "
					+ "     JOIN ( "
					+ "         SELECT DD.DOCTOR_ID, COUNT(DD.DIRECTION_ID) DIR_MATCHED "
					+ "         FROM DOCTORS_DIRECTIONS DD "
					+ "         WHERE DD.DIRECTION_ID IN (:directionsIds) "
					+ "         GROUP BY DD.DOCTOR_ID "
					+ "     ) DOCS_DIR ON DOCS_REG.DOCTOR_ID=DOCS_DIR.DOCTOR_ID "
					+ "         JOIN DOCTORS D ON DOCS_DIR.DOCTOR_ID=D.DOCTOR_ID "
					+ "         JOIN USERS U ON U.USER_ID = D.USER_ID "
					+ " ORDER BY DOCS_DIR.DIR_MATCHED DESC, D.PROMOTION_LEVEL DESC, D.RATING DESC, "
					+ "          U.LAST_NAME, U.FIRST_NAME ",
			countQuery = " SELECT COUNT(DOCS_DIR.DOCTOR_ID) FROM ( "
					+ "     SELECT DOCTOR_ID FROM DOCTORS D "
					+ "         JOIN INSTITUTIONS I ON D.INSTITUTION_ID=I.INSTITUTION_ID "
					+ "         JOIN CITIES C ON I.CITY_ID=C.CITY_ID "
					+ "     WHERE C.REGION_ID IN (:regionsIds) "
					+ " ) DOCS_REG "
					+ "     JOIN ( "
					+ "         SELECT DISTINCT DD.DOCTOR_ID FROM DOCTORS_DIRECTIONS DD "
					+ "         WHERE DD.DIRECTION_ID IN (:directionsIds) "
					+ "     ) DOCS_DIR ON DOCS_REG.DOCTOR_ID=DOCS_DIR.DOCTOR_ID ")
	Page<UserEntity> findDoctorsProfiles(
			Iterable<Integer> directionsIds, Iterable<Integer> regionsIds, Pageable pageable);

	/**
	 * Gets the page of doctors by  regions ids.
	 *
	 * @param pageable interface for pagination information received from user service
	 * @param regionsIds received from user service
	 * @return the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = " SELECT U.* FROM ( "
					+ "     SELECT D.PROMOTION_LEVEL, D.RATING, D.USER_ID FROM DOCTORS D "
					+ "         JOIN INSTITUTIONS I ON D.INSTITUTION_ID=I.INSTITUTION_ID "
					+ "         JOIN CITIES C ON I.CITY_ID=C.CITY_ID "
					+ "     WHERE C.REGION_ID IN (:regionsIds) "
					+ " ) DOCS_REG "
					+ "     JOIN USERS U ON U.USER_ID = DOCS_REG.USER_ID "
					+ " ORDER BY DOCS_REG.PROMOTION_LEVEL DESC, DOCS_REG.RATING DESC, "
					+ "          U.LAST_NAME, U.FIRST_NAME ",
			countQuery = " SELECT COUNT(D.DOCTOR_ID) FROM DOCTORS D "
					+ "     JOIN INSTITUTIONS I ON D.INSTITUTION_ID=I.INSTITUTION_ID "
					+ "     JOIN CITIES C ON I.CITY_ID=C.CITY_ID "
					+ " WHERE C.REGION_ID IN (:regionsIds) ")
	Page<UserEntity> findDoctorsProfilesByRegionsIds(
			Iterable<Integer> regionsIds, Pageable pageable);

	/**
	 * Gets the page of doctors by directions ids.
	 *
	 * @param pageable interface for pagination information received from user service
	 * @param directionsIds received from user service
	 * @return the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = " SELECT U.* FROM ( "
					+ "     SELECT DD.DOCTOR_ID, COUNT(DD.DIRECTION_ID) DIR_MATCHED"
					+ "     FROM DOCTORS_DIRECTIONS DD "
					+ "     WHERE DD.DIRECTION_ID IN (:directionsIds) "
					+ "     GROUP BY DD.DOCTOR_ID "
					+ " ) DOCS_DIR "
					+ "     JOIN DOCTORS D ON DOCS_DIR.DOCTOR_ID=D.DOCTOR_ID "
					+ "     JOIN USERS U ON U.USER_ID=D.USER_ID "
					+ " ORDER BY DOCS_DIR.DIR_MATCHED DESC, D.PROMOTION_LEVEL DESC, D.RATING DESC, "
					+ "          U.LAST_NAME, U.FIRST_NAME ",
			countQuery = " SELECT COUNT(DISTINCT DD.DOCTOR_ID) FROM DOCTORS_DIRECTIONS DD "
					+ " WHERE DD.DIRECTION_ID IN (:directionsIds) ")
	Page<UserEntity> findDoctorsProfilesByDirectionsIds(
			Iterable<Integer> directionsIds, Pageable pageable);

	/**
	 * Gets the page of doctors by single name.
	 *
	 * @param name user name received from user service
	 * @param pageable interface for pagination information received from user service
	 * @return the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = " SELECT U.* FROM USERS U"
					+ "   JOIN DOCTORS D ON U.USER_ID = D.USER_ID"
					+ "     WHERE UPPER(U.FIRST_NAME) LIKE CONCAT(UPPER(:name), '%') OR "
					+ "          UPPER(U.LAST_NAME) LIKE CONCAT(UPPER(:name), '%')")
	Page<UserEntity> findDoctorsByName(@Param("name") String name, Pageable pageable);

	/**
	 * Gets the page of doctors by firstName and lastName.	 *
	 * Sorts the experts according to the coincidence. First, the experts with the same firstname and  lastname are
	 * displayed. Through   the CASE...WHEN...THEN statement, the TURN parameter is defined. Then results of the SELECT
	 * statement are sorted according to the parameter TURN.
	 *
	 * @param firstName received from user service
	 * @param lastName received from user service
	 * @param pageable interface for pagination information received from user service
	 * @return  the resulting user entity page
	 */
	@Query(nativeQuery = true,
			value = "SELECT *, "
					+ "  CASE "
					+ "         WHEN ((UPPER(U.FIRST_NAME) LIKE CONCAT(UPPER(:firstName), '%') "
					+ "               AND UPPER(U.LAST_NAME) LIKE CONCAT(UPPER(:lastName), '%'))) "
					+ "           OR ((UPPER(U.FIRST_NAME) LIKE CONCAT(UPPER(:lastName), '%')"
					+ "               AND UPPER(U.LAST_NAME) LIKE CONCAT(UPPER(:firstName), '%')))  THEN 1"
					+ "         WHEN (UPPER(U.LAST_NAME) LIKE CONCAT(UPPER(:lastName), '%')) "
					+ "           OR (UPPER(U.LAST_NAME) LIKE CONCAT(UPPER(:firstName), '%'))"
					+ "           OR (UPPER(U.FIRST_NAME) LIKE CONCAT(UPPER(:firstName), '%')) "
					+ "           OR (UPPER(U.FIRST_NAME) LIKE CONCAT(UPPER(:lastName), '%'))       THEN 2"
					+ "                                                               ELSE 3 "
					+ "     END as TURN "
					+ "   FROM USERS U "
					+ "     JOIN DOCTORS D ON U.USER_ID = D.USER_ID "
					+ "       WHERE (UPPER(U.LAST_NAME) LIKE CONCAT(UPPER(:lastName), '%')) "
					+ "         OR (UPPER(U.LAST_NAME) LIKE CONCAT(UPPER(:firstName), '%'))"
					+ "         OR (UPPER(U.FIRST_NAME) LIKE CONCAT(UPPER(:lastName), '%'))"
					+ "         OR (UPPER(U.FIRST_NAME) LIKE CONCAT(UPPER(:firstName), '%'))"
					+ "       ORDER BY TURN")
	Page<UserEntity> findDoctorsByName(
			@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);

	/**
	 * Checks whether the user exists by email.
	 *
	 * @param email received from user service
	 * @return true or false
	 */
	Boolean existsByEmail(String email);
}
