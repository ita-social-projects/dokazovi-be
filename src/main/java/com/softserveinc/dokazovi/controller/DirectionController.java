package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.service.DirectionService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.DIRECTION;
import static com.softserveinc.dokazovi.controller.EndPoints.DIRECTION_ALL;

@RestController
@RequestMapping(DIRECTION)
@RequiredArgsConstructor
public class DirectionController {

	private final DirectionService directionService;

	@ApiOperation("Get all directions")
	@ApiPageable
	@GetMapping(DIRECTION_ALL)
	public ResponseEntity<Page<DirectionDTO>> getAllDirections(
			@PageableDefault(size = 2000) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(directionService.findAllDirections(pageable));
	}
}
