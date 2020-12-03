package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByEmail(String email);

	Page<UserEntity> findAllByStatus(UserStatus userStatus, Pageable pageable);

	Page<UserEntity> findAllByMainDirectionIdInAndStatus(Iterable<Integer> directionsIds, UserStatus userStatus,
			Pageable pageable);

	Page<UserEntity> findAllByMainInstitutionCityRegionIdInAndStatus(Iterable<Integer> regionsIds,
			UserStatus userStatus, Pageable pageable);

	Page<UserEntity> findAllByMainDirectionIdInAndMainInstitutionCityRegionIdInAndStatus(
			Iterable<Integer> directionsIds, Iterable<Integer> regionsIds, UserStatus userStatus, Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT * FROM users u WHERE u.status='ACTIVE' ORDER BY random()")
	Page<UserEntity> findRandomActiveUsers(Pageable pageable);

	@Query(nativeQuery = true,
			value = "SELECT * FROM users u "
					+ "WHERE u.status='ACTIVE' "
					+ "AND u.direction_id IN (:directionsIds)"
					+ " ORDER BY random()")
	Page<UserEntity> findRandomActiveUsersByDirections(@Param("directionsIds") Iterable<Integer> directionsIds,
			Pageable pageable);

	Boolean existsByEmail(String email);
}
