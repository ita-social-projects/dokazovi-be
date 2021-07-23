package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

	Optional<PasswordResetTokenEntity> findByToken (String token);

}
