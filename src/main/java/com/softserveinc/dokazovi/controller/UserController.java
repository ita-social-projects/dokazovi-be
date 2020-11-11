package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.DoctorPreviewDTO;
import com.softserveinc.dokazovi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Get preview of random experts")
	@ApiPageable
	@GetMapping("/experts")
	public ResponseEntity<Page<DoctorPreviewDTO>> getExpertPreview(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.getExpertsPreview(pageable));
	}
}
