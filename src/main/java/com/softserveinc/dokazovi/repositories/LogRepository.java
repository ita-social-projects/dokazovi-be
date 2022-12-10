package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.LogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Integer> {

    Page<LogEntity> findAll(Pageable pageable);
    Page<LogEntity> findAllByNameOfChangerContainingIgnoreCase(Pageable pageable, String nameOfChanger);
    Page<LogEntity> findAllByTitleContainingIgnoreCase(Pageable pageable, String title);
    Page<LogEntity> findByDateOfChangeBetween(Pageable pageable, Timestamp startDate, Timestamp endDate);
}