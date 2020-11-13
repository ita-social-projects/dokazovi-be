package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.util.BuildVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class VersionController {

	private final BuildVersion buildVersion;

	@GetMapping(EndPoints.VERSION)
	public ResponseEntity<BuildVersion> getBuildVersion() {
		return new ResponseEntity<>(buildVersion, HttpStatus.OK);
	}
}
