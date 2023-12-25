package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserIpWhitelistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softserveinc.dokazovi.entity.UserEntity;

import java.util.List;

@Repository
public interface UserIpWhitelistRepository extends JpaRepository<UserIpWhitelistEntity, Integer> {
    List<UserIpWhitelistEntity> findAllByUser(UserEntity user);

    boolean existsByUserAndWhitelistIp(UserEntity user, String whitelistIp);
}
