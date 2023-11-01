package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.entity.LogForLoginEntity;
import com.softserveinc.dokazovi.service.LogForLoginService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.LOG_FOR_LOGIN;

@RestController
@RequestMapping(LOG_FOR_LOGIN)
@RequiredArgsConstructor
public class LogForLoginController {
    private final LogForLoginService logForLoginService;

    @GetMapping()
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiPageable
    @ApiOperation(value = "get all logs",
            authorizations = {@Authorization(value = "Authorization")})
    public ResponseEntity<Page<LogForLoginEntity>> getLogList(
            @PageableDefault(size = 24, direction = Sort.Direction.DESC) Pageable pageable,
            @ApiParam(value = "yyyy-MM-dd")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @ApiParam(value = "yyyy-MM-dd")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @ApiParam(value = "Logs by status", type = "string")
            @RequestParam(required = false, defaultValue = "") String status,
            @ApiParam(value = "Logs by login", type = "string")
            @RequestParam(required = false, defaultValue = "") String login,
            @ApiParam(value = "Logs by ip", type = "string")
            @RequestParam(required = false, defaultValue = "") String ip
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logForLoginService.findAllLogs(pageable, startDate, endDate, status, login, ip));
    }
}
