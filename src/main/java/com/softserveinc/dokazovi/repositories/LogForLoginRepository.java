package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.LogForLoginEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;

public interface LogForLoginRepository extends JpaRepository<LogForLoginEntity, Integer> {
    void deleteLogForLoginEntitiesByDateOfLoginBefore(Timestamp minDate);

    Page<LogForLoginEntity> findByDateOfLoginBetween(Pageable pageable, Timestamp start, Timestamp end);

    Page<LogForLoginEntity> findByLoginStatusContainingIgnoreCase(Pageable pageable, String status);

    Page<LogForLoginEntity> findByLoginContainingIgnoreCase(Pageable pageable, String login);

    Page<LogForLoginEntity> findAllByIpContaining(Pageable pageable, String ip);
}
