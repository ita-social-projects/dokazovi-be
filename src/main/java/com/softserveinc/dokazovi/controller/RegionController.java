package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.service.RegionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.REGION;

@RestController
@RequestMapping(REGION)
@RequiredArgsConstructor
public class RegionController {

	private final RegionService regionService;

	@ApiOperation(value = "Get all regions",
			authorizations = {@Authorization(value = "Authorization")})
	@GetMapping
	public ResponseEntity<List<RegionDTO>> getAllRegions() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(regionService.findAllRegions());
	}
}
