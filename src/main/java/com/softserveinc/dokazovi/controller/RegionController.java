package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.service.RegionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.REGION;

/**
 * The Class RegionController responsible for handling requests for regions
 */
@RestController
@RequestMapping(REGION)
@RequiredArgsConstructor
public class RegionController {

	private final RegionService regionService;

	/**
	 * Gets all regions method returns all regions from db.
	 *
	 * @return all regions and HttpStatus 'OK'
	 */
	@GetMapping
	@ApiOperation(value = "Get all regions")
	public ResponseEntity<List<RegionDTO>> getAllRegions() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(regionService.findAllRegions());
	}
}
