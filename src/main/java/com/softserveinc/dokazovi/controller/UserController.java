package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.USERS)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "Get preview of random experts")
	@ApiPageable
	@GetMapping(EndPoints.EXPERTS)
	public ResponseEntity<Page<ExpertPreviewDTO>> getExpertPreview(Pageable pageable,Integer numberOfUsers) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.getExpertsPreview(pageable,numberOfUsers));
	}
}
