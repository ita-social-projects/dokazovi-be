package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.exception.ResourceNotFoundException;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.CurrentUser;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ALL_EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RANDOM_EXPERTS;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;

	private final UserService userService;

	@ApiOperation(value = "Get preview of random experts, filtered by directions. Default 12 max per page.")
	@ApiPageable
	@GetMapping(USER_RANDOM_EXPERTS)
	public ResponseEntity<Page<UserDTO>> getRandomExpertPreview(
			@PageableDefault(size = 12) Pageable pageable,
			@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> directions) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.findRandomExpertPreview(directions, pageable));
	}

	@ApiOperation(value = "Get experts ordered by firstName then lastName, filtered by directions and regions."
			+ " Default 6 per page.")
	@ApiPageable
	@GetMapping(USER_ALL_EXPERTS)
	public ResponseEntity<Page<UserDTO>> getAllExpertsByDirectionsAndByRegions(
			@PageableDefault(size = 6, sort = {"firstName", "lastName", "id"}) Pageable pageable,
			@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> directions,
			@ApiParam(value = "Multiple comma-separated region IDs, e.g. ?regions=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> regions) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.findAllExpertsByDirectionsAndRegions(directions, regions, pageable));
	}

	@ApiOperation(value = "Get expert by Id, as a path variable.")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getExpertById(@PathVariable("userId") Integer userId) {
		UserDTO userDTO = userService.findExpertById(userId);
		return ResponseEntity
				.status((userDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(userDTO);
	}

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	public UserEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}

}
