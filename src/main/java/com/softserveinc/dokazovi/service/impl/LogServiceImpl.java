package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.log.PostLogDTO;
import com.softserveinc.dokazovi.mapper.LogMapper;
import com.softserveinc.dokazovi.repositories.LogRepository;
import com.softserveinc.dokazovi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogMapper logMapper;
    @Override
    public Page<PostLogDTO> findAllPostLogs(Pageable pageable, String username, String title, LocalDate startDate, LocalDate endDate) {
        if (startDate != null || endDate != null) {
            LocalDate startLocalDate = Optional.ofNullable(startDate).orElse(LocalDate.EPOCH);
            LocalDate endLocalDate = Optional.ofNullable(endDate).orElse(LocalDate.now());
            Timestamp startDateTimestamp = Timestamp.valueOf(startLocalDate.atStartOfDay());
            Timestamp endDateTimestamp = Timestamp.valueOf(endLocalDate.atTime(LocalTime.MAX));
            return logRepository.findByDateOfChangeBetween(pageable, startDateTimestamp, endDateTimestamp)
                    .map(logMapper::toPostLogDTO);
        }
        if (title.trim().length() > 0) {
            return logRepository.findAllByTitleContainingIgnoreCase(pageable, username)
                    .map(logMapper::toPostLogDTO);
        }
        if (username.trim().length() > 0) {
            return logRepository.findAllByNameOfChangerContainingIgnoreCase(pageable, username)
                    .map(logMapper::toPostLogDTO);
        }
        return logRepository.findAll(pageable)
                .map(logMapper::toPostLogDTO);
    }
}