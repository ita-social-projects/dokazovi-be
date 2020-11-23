package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

	@Query(nativeQuery = true,
			value = "SELECT * FROM users u WHERE u.status='ACTIVE' ORDER BY random()")
	Page<UserEntity> findRandomActiveUsers(Pageable pageable);

	Page<UserEntity> findAllByStatusAndMainDirectionId(UserStatus userStatus, Integer directionEntityId, Pageable pageable);
}
