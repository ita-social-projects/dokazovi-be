package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.UserLoginIpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginIpRepository extends JpaRepository<UserLoginIpEntity, Integer> {
    boolean existsByUserIdAndIpAddress(Integer userId, String ipAddress);

    List<UserLoginIpEntity> findAllByUserId(Integer userId);
}
