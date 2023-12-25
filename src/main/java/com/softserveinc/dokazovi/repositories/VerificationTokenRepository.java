package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken>  findByToken(String token);

    @Query(nativeQuery = true,
            value = "SELECT vt FROM VerificationToken vt WHERE vt.dateExpiration < :currentTime")
    List<VerificationToken> findAllExpritedTokens(LocalDateTime currentTime);
}
