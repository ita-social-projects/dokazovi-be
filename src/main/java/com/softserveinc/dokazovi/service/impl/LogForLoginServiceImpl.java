package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.LogForLoginEntity;
import com.softserveinc.dokazovi.repositories.LogForLoginRepository;
import com.softserveinc.dokazovi.service.LogForLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogForLoginServiceImpl implements LogForLoginService {

    private final LogForLoginRepository logForLoginRepository;

    @Override
    public LogForLoginEntity save(LogForLoginEntity log) {
        return logForLoginRepository.save(log);
    }

    @Override
    public Page<LogForLoginEntity> findAllLogs(Pageable pageable, LocalDate startDate, LocalDate endDate,
            String status, String login, String ip) {
        if (startDate != null || endDate != null) {
            LocalDate startLocalDate = Optional.ofNullable(startDate).orElse(LocalDate.EPOCH);
            LocalDate endLocalDate = Optional.ofNullable(endDate).orElse(LocalDate.now());
            Timestamp startDateTimestamp = Timestamp.valueOf(startLocalDate.atStartOfDay());
            Timestamp endDateTimestamp = Timestamp.valueOf(endLocalDate.atTime(LocalTime.MAX));
            return logForLoginRepository.findByDateOfLoginBetween(pageable, startDateTimestamp, endDateTimestamp);
        }
        if (status != null && status.trim().length() > 0) {
            return logForLoginRepository.findByLoginStatusContainingIgnoreCase(pageable, status);
        }
        if (login != null && login.trim().length() > 0) {
            return logForLoginRepository.findByLoginContainingIgnoreCase(pageable, login);
        }
        if (ip != null && ip.trim().length() > 0) {
            return logForLoginRepository.findAllByIpContaining(pageable, ip);
        }
        return logForLoginRepository.findAll(pageable);
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 12 * * ?")
    public void deleteOutdatedLogs() {
        logForLoginRepository.deleteLogForLoginEntitiesByDateOfLoginBefore(
                Timestamp.valueOf(LocalDateTime.now().minusMonths(3))
        );
    }

}
