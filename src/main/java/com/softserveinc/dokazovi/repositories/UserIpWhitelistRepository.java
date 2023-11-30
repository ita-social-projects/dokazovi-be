package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserIpWhitelistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIpWhitelistRepository extends JpaRepository<UserIpWhitelistEntity, Integer> {

}
