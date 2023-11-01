package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.LogForLoginEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface LogForLoginService {
    LogForLoginEntity save(LogForLoginEntity log);

    Page<LogForLoginEntity> findAllLogs(Pageable pageable, LocalDate  start, LocalDate end,
            String status, String login, String ip);

    void deleteOutdatedLogs();
}
