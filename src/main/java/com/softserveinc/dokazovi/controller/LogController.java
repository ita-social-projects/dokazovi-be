package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.log.PostLogDTO;
import com.softserveinc.dokazovi.service.LogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;

import static com.softserveinc.dokazovi.controller.EndPoints.LOG;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LOGS;

@RestController
@RequestMapping(LOG)
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping(POST_LOGS)
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiPageable
    @ApiOperation(value = "get all post logs",
            authorizations = {@Authorization(value = "Authorization")})
    public ResponseEntity<Page<PostLogDTO>> getPostLogList(
            @PageableDefault(size = 12, direction = Sort.Direction.DESC) Pageable pageable,
            @ApiParam(value = "Logs by surname", type = "string")
            @RequestParam(required = false, defaultValue = "") String surname,
            @ApiParam(value = "Logs by title", type = "string")
            @RequestParam(required = false, defaultValue = "") String title,
            @ApiParam(value = "yyyy-MM-dd")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @ApiParam(value = "yyyy-MM-dd")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logService.findAllPostLogs(pageable, surname, title, startDate, endDate));
    }
}