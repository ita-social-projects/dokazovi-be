package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.util.BuildVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.WEB_API)
public class VersionController {

	private final BuildVersion buildVersion;

	@Autowired
	public VersionController(BuildVersion buildVersion) {
		this.buildVersion = buildVersion;
	}

	@GetMapping(EndPoints.VERSION)
	public ResponseEntity<BuildVersion> getBuildVersion() {
		return new ResponseEntity<>(buildVersion, HttpStatus.OK);
	}
}
