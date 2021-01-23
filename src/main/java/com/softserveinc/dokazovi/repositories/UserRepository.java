package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

	@Query(nativeQuery = true,
			value = " SELECT U.* FROM DOCTORS D "
					+ "     JOIN USERS U ON D.USER_ID = U.USER_ID "
					+ " ORDER BY RANDOM() ")
	Page<UserEntity> findRandomExperts(Pageable pageable);

	@Query(nativeQuery = true,
			value = " SELECT U.* FROM ( "
					+ "     SELECT DD.DOCTOR_ID FROM DOCTORS_DIRECTIONS DD "
					+ "		WHERE DD.DIRECTION_ID IN (:directionsIds) "
					+ "     GROUP BY DD.DOCTOR_ID "
					+ " ) DOCS_DIRS "
					+ "     JOIN DOCTORS D ON DOCS_DIRS.DOCTOR_ID=D.DOCTOR_ID "
					+ "     JOIN USERS U ON D.USER_ID = U.USER_ID "
					+ " ORDER BY RANDOM() ",
			countQuery = " SELECT COUNT(DISTINCT DD.DOCTOR_ID) FROM DOCTORS_DIRECTIONS DD "
					+ " WHERE DD.DIRECTION_ID IN (:directionsIds) ")
	Page<UserEntity> findRandomUsersByDirectionsIdIn(Iterable<Integer> directionsIds, Pageable pageable);

	@Query(nativeQuery = true,
			value = " SELECT U.* FROM DOCTORS D "
					+ "     JOIN USERS U ON U.USER_ID = D.USER_ID "
					+ " ORDER BY D.PROMOTION_LEVEL DESC, D.RATING, "
					+ "          U.LAST_NAME, U.FIRST_NAME ")
	Page<UserEntity> findDoctorsProfilesOrderByRatingThenByName(Pageable pageable);

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
	Page<UserEntity> findDoctorsProfilesByRegionsIdsOrderByRatingThenByName(
			Iterable<Integer> regionsIds, Pageable pageable);

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
	Page<UserEntity> findDoctorsProfilesByDirectionsIdsOrderByDirectionsMatchesThenByRatingThenByName(
			Iterable<Integer> directionsIds, Pageable pageable);

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
	Page<UserEntity> findDoctorsProfilesByDirectionsIdsAndRegionsIdsOrderByDirectionsMatchesThenByRatingThenByName(
			Iterable<Integer> directionsIds, Iterable<Integer> regionsIds, Pageable pageable);
}
