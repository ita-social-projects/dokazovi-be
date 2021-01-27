package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.service.DirectionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.DIRECTION;

@RestController
@RequestMapping(DIRECTION)
@RequiredArgsConstructor
public class DirectionController {

	private final DirectionService directionService;

	@GetMapping
	@ApiOperation(value = "Get all directions")
	public ResponseEntity<List<DirectionDTO>> getAllDirections() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(directionService.findAllDirections());
	}
}
