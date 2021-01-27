package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ALL_EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_GET_CURRENT_USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_GET_USER_BY_ID;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RANDOM_EXPERTS;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping(USER_RANDOM_EXPERTS)
	@ApiPageable
	@ApiOperation(value = "Get preview of random experts, filtered by directions. Default 12 max per page.")
	public ResponseEntity<Page<UserDTO>> getRandomExpertPreview(
			@PageableDefault(size = 12) Pageable pageable,
			@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> directions) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.findRandomExpertPreview(directions, pageable));
	}

	@GetMapping(USER_ALL_EXPERTS)
	@ApiPageable
	@ApiOperation(value = "Get experts ordered by firstName then lastName, filtered by directions and regions."
			+ " Default 6 per page.")
	public ResponseEntity<Page<UserDTO>> getAllExpertsByDirectionsAndByRegionsOrderedByRelevance(
			@PageableDefault(size = 6) Pageable pageable,
			@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> directions,
			@ApiParam(value = "Multiple comma-separated region IDs, e.g. ?regions=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> regions) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.findAllExpertsByDirectionsAndRegions(directions, regions, pageable));
	}

	@GetMapping(USER_GET_USER_BY_ID)
	@ApiOperation(value = "Get expert by Id, as a path variable.")
	public ResponseEntity<UserDTO> getExpertById(@PathVariable("userId") Integer userId) {
		UserDTO userDTO = userService.findExpertById(userId);
		return ResponseEntity
				.status((userDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(userDTO);
	}

	@GetMapping(USER_GET_CURRENT_USER)
	@ApiOperation(value = "Get current user",
			authorizations = {@Authorization(value = "Authorization")})
	public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		UserDTO userDTO = userService.findExpertById(userPrincipal.getId());
		return ResponseEntity
				.status((userDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(userDTO);
	}
}
