package com.softserveinc.dokazovi.controller;


import com.softserveinc.dokazovi.dto.payload.ApiResponseMessage;
import com.softserveinc.dokazovi.dto.user.AuthorDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.softserveinc.dokazovi.controller.EndPoints.DOCTOR;
import static com.softserveinc.dokazovi.controller.EndPoints.DOCTORS;
import static com.softserveinc.dokazovi.controller.EndPoints.DOCTOR_GET_DOCTOR_BY_ID;

@RestController
@RequestMapping(DOCTOR)
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorService authorService;


	@PostMapping
	@PreAuthorize("hasAuthority('CREATE_AUTHOR')")
	@ApiOperation(value = "Сreate author",
			authorizations = {@Authorization(value = "Authorization")})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = HttpStatuses.CREATED, response = AuthorDTO.class),
			@ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
	})
	public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO author,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(authorService.save(author, userPrincipal));
	}

	@PutMapping
	@ApiOperation(value = "update author")
	public ResponseEntity<AuthorDTO> updateAuthor(AuthorDTO authorDTO) {
		return ResponseEntity
				.status(200)
				.body(userService.updateAuthor(authorDTO));
	}

	@DeleteMapping(DOCTOR_GET_DOCTOR_BY_ID)
	@ApiOperation(value = "remove author")
	public ResponseEntity<ApiResponseMessage> deleteAuthor(Integer authorId) {
		ApiResponseMessage apiResponseMessage;

		apiResponseMessage = ApiResponseMessage.builder()
				.success(userService.removeDoctorById(authorId))
				.message(String.format("Doctor %s deleted successfully", authorId))
				.build();
		return ResponseEntity.ok().body(apiResponseMessage);
	}

	@GetMapping(DOCTORS)
	@ApiOperation(value = "get all authors")
	public ResponseEntity<Page<AuthorDTO>> getAuthors(@PageableDefault Pageable pageable) {
		Page<AuthorDTO> authors = userService.getDoctors(pageable);
		return ResponseEntity.status(200).body(authors);
	}
}
