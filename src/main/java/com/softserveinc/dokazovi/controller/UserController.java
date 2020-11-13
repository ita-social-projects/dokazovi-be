package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.service.UserService;
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

import static com.softserveinc.dokazovi.controller.EndPoints.EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "Get preview of random experts. Default 11 max per page.")
	@ApiPageable
	@GetMapping(EXPERTS)
	public ResponseEntity<Page<ExpertPreviewDTO>> getExpertPreview(
			@PageableDefault(size = 11) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.getExpertsPreview(pageable));
	}
}
