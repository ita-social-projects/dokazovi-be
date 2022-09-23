package com.softserveinc.dokazovi.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Version controller responsible for handling requests about project build version.
 */
@RestController
@RequestMapping(EndPoints.VERSION)
@RequiredArgsConstructor
public class VersionController {

    private final BuildProperties buildProperties;

    /**
     * Returns all build-related information of project version.
     *
     * @return build properties and 'OK' HttpStatus
     */
    @GetMapping
    @ApiOperation(value = "Get build version")
    public ResponseEntity<BuildProperties> getBuildVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildProperties);
    }
}
