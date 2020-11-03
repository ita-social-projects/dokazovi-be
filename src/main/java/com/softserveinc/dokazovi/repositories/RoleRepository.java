package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
