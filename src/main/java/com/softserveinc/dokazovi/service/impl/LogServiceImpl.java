package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.log.PostLogDTO;
import com.softserveinc.dokazovi.entity.LogEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.mapper.LogMapper;
import com.softserveinc.dokazovi.repositories.LogRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogMapper logMapper;
    private final UserRepository userRepository;

    @Override
    public Page<PostLogDTO> findAllPostLogs(Pageable pageable, String username, String title,
            LocalDate startDate, LocalDate endDate) {
        if (startDate != null || endDate != null) {
            LocalDate startLocalDate = Optional.ofNullable(startDate).orElse(LocalDate.EPOCH);
            LocalDate endLocalDate = Optional.ofNullable(endDate).orElse(LocalDate.now());
            Timestamp startDateTimestamp = Timestamp.valueOf(startLocalDate.atStartOfDay());
            Timestamp endDateTimestamp = Timestamp.valueOf(endLocalDate.atTime(LocalTime.MAX));
            return logRepository.findByDateOfChangeBetween(pageable, startDateTimestamp, endDateTimestamp)
                    .map(logMapper::toPostLogDTO);
        }
        if (title != null && title.trim().length() > 0) {
            return logRepository.findAllByTitleContainingIgnoreCase(pageable, title)
                    .map(logMapper::toPostLogDTO);
        }
        if (username != null && username.trim().length() > 0) {
            return logRepository.findAllByNameOfChangerContainingIgnoreCase(pageable, username)
                    .map(logMapper::toPostLogDTO);
        }
        return logRepository.findAll(pageable)
                .map(logMapper::toPostLogDTO);
    }

    @Override
    public PostLogDTO getLogById(Integer id) {
        return logMapper.toPostLogDTO(logRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Unable to find log with id:" + id)));
    }

    @Override
    public void makeEntryInLogs(String title, UserPrincipal userPrincipal, String changes, Integer postId) {
        UserEntity userEntity = userRepository.findByEmail(userPrincipal.getEmail()).get();
        LogEntity log = LogEntity.builder()
                .title(title)
                .changes(changes)
                .idOfChangedPost(postId)
                .nameOfChanger(userEntity.getLastName() + " " + userEntity.getFirstName())
                .build();
        logRepository.save(log);
    }
}