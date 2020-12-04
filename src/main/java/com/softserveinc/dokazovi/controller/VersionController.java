package com.softserveinc.dokazovi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.VERSION)
@RequiredArgsConstructor
public class VersionController {

	private final BuildProperties buildProperties;

	@GetMapping
	public ResponseEntity<BuildProperties> getBuildVersion() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(buildProperties);
	}
}
