package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.log.PostLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface LogService {

    Page<PostLogDTO> findAllPostLogs(Pageable pageable, String surname, String title,
            LocalDate startDate, LocalDate endDate);

    PostLogDTO getLogById(Integer id);
}