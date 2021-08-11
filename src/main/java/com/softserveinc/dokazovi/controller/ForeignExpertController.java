package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSearchResultDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.ForeignExpertService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(EndPoints.FOREIGN_EXPERT)
@RequiredArgsConstructor
public class ForeignExpertController {
	private final ForeignExpertService foreignExpertService;

	/**
	 * Saves (creates) a foreign expert entry.
	 *
	 * <p>Checks if user has authority to perform the request</p>
	 * @param saveDTO       DTO of the foreign expert
	 * @param userPrincipal authorized user data
	 *
	 * @return HttpStatus 'CREATED' and saves the foreign expert entry to the DB
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('SAVE_FOREIGN_EXPERT')")
	@ApiOperation(value = "Save a foreign expert", authorizations = {@Authorization(value = "Authorization")})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = HttpStatuses.CREATED, response = ForeignExpertDTO.class),
			@ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
	})
	public ResponseEntity<ForeignExpertDTO> save(
			@Valid @RequestBody ForeignExpertSearchResultDTO saveDTO,
			@AuthenticationPrincipal UserPrincipal userPrincipal
	) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(foreignExpertService.save(saveDTO, userPrincipal));
	}

	@GetMapping(EndPoints.FOREIGN_EXPERT_SEARCH)
	@ApiOperation(value = "Search for foreign experts")
	@ApiPageable()
	public ResponseEntity<Page<ForeignExpertSearchResultDTO>> search(
			@RequestParam String query,
			@PageableDefault(sort = {"full_name"}, direction = Sort.Direction.ASC)
			Pageable pageable
	) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(foreignExpertService.search(query, pageable));
	}
}
