package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

}
