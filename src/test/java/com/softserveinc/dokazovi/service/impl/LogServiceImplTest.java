package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.LogEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.mapper.LogMapper;
import com.softserveinc.dokazovi.repositories.LogRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LogServiceImplTest {

    @Mock
    private LogRepository logRepository;
    @Mock
    private LogMapper logMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Pageable pageable;
    @InjectMocks
    private LogServiceImpl logService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllPostLogsWithoutParameters() {
        Page<LogEntity> postLogDTOS = new PageImpl<>(List.of(new LogEntity(), new LogEntity()));

        when(logRepository.findAll(any(Pageable.class))).thenReturn(postLogDTOS);
        logService.findAllPostLogs(pageable, null, null, null, null);

        verify(logRepository).findAll(any(Pageable.class));
        verify(logMapper, times(2)).toPostLogDTO(any(LogEntity.class));
    }

    @Test
    void findAllPostLogsWithUsername() {
        Page<LogEntity> postLogDTOS = new PageImpl<>(List.of(new LogEntity(), new LogEntity()));

        when(logRepository.findAllByNameOfChangerContainingIgnoreCase(any(Pageable.class), anyString()))
                .thenReturn(postLogDTOS);
        logService.findAllPostLogs(pageable, "testUsername", null, null, null);

        verify(logRepository).findAllByNameOfChangerContainingIgnoreCase(any(Pageable.class), anyString());
        verify(logMapper, times(2)).toPostLogDTO(any(LogEntity.class));
    }

    @Test
    void findAllPostLogsWithTitle() {
        Page<LogEntity> postLogDTOS = new PageImpl<>(List.of(new LogEntity(), new LogEntity()));

        when(logRepository.findAllByTitleContainingIgnoreCase(any(Pageable.class), anyString()))
                .thenReturn(postLogDTOS);
        logService.findAllPostLogs(pageable, null, "testTitle", null, null);

        verify(logRepository).findAllByTitleContainingIgnoreCase(any(Pageable.class), anyString());
        verify(logMapper, times(2)).toPostLogDTO(any(LogEntity.class));
    }

    @Test
    void findAllPostLogsWithTimestamps() {
        Page<LogEntity> postLogDTOS = new PageImpl<>(List.of(new LogEntity(), new LogEntity()));

        when(logRepository.findByDateOfChangeBetween(any(Pageable.class), any(Timestamp.class), any(Timestamp.class)))
                .thenReturn(postLogDTOS);
        logService.findAllPostLogs(pageable, null, null, LocalDate.now(), null);

        verify(logRepository).findByDateOfChangeBetween(any(Pageable.class),
                any(Timestamp.class), any(Timestamp.class));
        verify(logMapper, times(2)).toPostLogDTO(any(LogEntity.class));
    }

    @Test
    void makeEntryInLogs() {
        UserEntity expected = UserEntity.builder()
                .id(1)
                .firstName("First Name")
                .lastName("Last Name")
                .email("admin@mail.com")
                .password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
                .build();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(expected));
        LogEntity log = LogEntity.builder()
                .title("Test")
                .changes("Матеріал видалено")
                .idOfChangedPost(1)
                .nameOfChanger(expected.getLastName() + " " + expected.getFirstName())
                .build();
        when(logRepository.save(any(LogEntity.class))).thenReturn(log);
        logService.makeEntryInLogs(log.getTitle(), UserPrincipal.create(expected), log.getChanges(),
                log.getIdOfChangedPost());
        verify(logRepository, times(1))
                .save(any(LogEntity.class));
        assertEquals(1, log.getIdOfChangedPost());
        assertEquals(expected.getLastName() + " " + expected.getFirstName(), log.getNameOfChanger());
    }
}