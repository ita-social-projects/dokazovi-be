package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.dto.user.RandomExpertFilteringDTO;
import com.softserveinc.dokazovi.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RANDOM_EXPERTS;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "Get preview of random experts,"
			+ " accepts sorting parameters in request body. Default 12 max per page.")
	@ApiPageable
	@PostMapping(USER_RANDOM_EXPERTS)
	public ResponseEntity<Page<ExpertPreviewDTO>> getRandomExpertPreview(
			@PageableDefault(size = 12) Pageable pageable, @RequestBody RandomExpertFilteringDTO requestBody) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.getRandomExpertPreview(pageable, requestBody));
	}

}
