package com.softserveinc.dokazovi.repositories;


import com.softserveinc.dokazovi.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<ProviderEntity, Integer> {

	Optional<ProviderEntity> findProviderEntityByEmail(String email);

	/*@Query(nativeQuery = true,
			value = "insert into user_providers(email, provider_name, user_id, user_id_by_provider) "
					+ "VALUES ((:email),(:providerName),(:userId),(:userIdByProvider));")


	@Query(nativeQuery = true,
			value = "insert into user_providers(email, provider_name, user_id, user_id_by_provider) "
					+ "VALUES (?1,?2,?3,?4);")
	UserProviderEntity save(@Param("email")String email,@Param("providerName")String providerName,@Param("userId")Integer userId,
			@Param("userIdByProvider")String userIdByProvider); */
}
