package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
