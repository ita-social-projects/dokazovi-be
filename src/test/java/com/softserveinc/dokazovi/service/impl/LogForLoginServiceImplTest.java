package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.LogForLoginEntity;
import com.softserveinc.dokazovi.repositories.LogForLoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LogForLoginServiceImplTest {
    @Mock
    private LogForLoginRepository logForLoginRepository;
    @Mock
    private Pageable pageable;
    @InjectMocks
    private LogForLoginServiceImpl logForLoginService;

    @Test
    void findAllLogs() {
        Page<LogForLoginEntity> logs = new PageImpl<>(List.of(new LogForLoginEntity(), new LogForLoginEntity()));

        when(logForLoginRepository.findAll(pageable)).thenReturn(logs);
        logForLoginService.findAllLogs(pageable, null, null, null, null, null);

        verify(logForLoginRepository).findAll(any(Pageable.class));
    }

    @Test
    void findAllLogsByDate() {
        Page<LogForLoginEntity> logs = new PageImpl<>(List.of(new LogForLoginEntity(), new LogForLoginEntity()));

        when(logForLoginRepository.findByDateOfLoginBetween(
                any(Pageable.class), any(Timestamp.class), any(Timestamp.class))).thenReturn(logs);
        logForLoginService.findAllLogs(pageable, LocalDate.of(1995, 6, 7), LocalDate.now(), null, null, null);

        verify(logForLoginRepository).findByDateOfLoginBetween(
                any(Pageable.class), any(Timestamp.class), any(Timestamp.class));
    }

    @Test
    void findAllLogsByStatus() {
        Page<LogForLoginEntity> logs = new PageImpl<>(List.of(new LogForLoginEntity(), new LogForLoginEntity()));

        when(logForLoginRepository.findByLoginStatusContainingIgnoreCase(
                any(Pageable.class), anyString())).thenReturn(logs);
        logForLoginService.findAllLogs(pageable, null, null, "null", null, null);

        verify(logForLoginRepository).findByLoginStatusContainingIgnoreCase(any(Pageable.class), anyString());
    }

    @Test
    void findAllLogsByLogin() {
        Page<LogForLoginEntity> logs = new PageImpl<>(List.of(new LogForLoginEntity(), new LogForLoginEntity()));

        when(logForLoginRepository.findByLoginContainingIgnoreCase(any(Pageable.class), anyString())).thenReturn(logs);
        logForLoginService.findAllLogs(pageable, null, null, null, "null", null);

        verify(logForLoginRepository).findByLoginContainingIgnoreCase(any(Pageable.class), anyString());
    }

    @Test
    void findAllLogsByIp() {
        Page<LogForLoginEntity> logs = new PageImpl<>(List.of(new LogForLoginEntity(), new LogForLoginEntity()));

        when(logForLoginRepository.findAllByIpContaining(any(Pageable.class), anyString())).thenReturn(logs);
        logForLoginService.findAllLogs(pageable, null, null, null, null, "null");

        verify(logForLoginRepository).findAllByIpContaining(any(Pageable.class), anyString());
    }
}
