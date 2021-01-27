package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
	Optional<RoleEntity> getRoleEntityByName(String name);
}
