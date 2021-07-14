package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

}
