package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.service.RegionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.softserveinc.dokazovi.controller.EndPoints.REGION;
import static com.softserveinc.dokazovi.controller.EndPoints.REGION_ALL;

@RestController
@RequestMapping(REGION)
@RequiredArgsConstructor
public class RegionController {

	private final RegionService regionService;

	@ApiOperation("Get all regions")
	@ApiPageable
	@GetMapping(REGION_ALL)
	public ResponseEntity<Page<RegionDTO>> getAllRegions(
			@PageableDefault(size = 2000) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(regionService.findAllRegions(pageable));
	}
}
