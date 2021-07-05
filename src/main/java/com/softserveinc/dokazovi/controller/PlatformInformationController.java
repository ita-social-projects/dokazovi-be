package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.PlatformInformationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.softserveinc.dokazovi.controller.EndPoints.PLATFORM_INFORMATION;
import static com.softserveinc.dokazovi.controller.EndPoints.PLATFORM_INFORMATION_BY_ID;

/**
 * The PlatformInformationController class is responsible for the handling HTTP requests and responses on an
 * "PlatformInformation" API endpoint. Requests to the current endpoint can manipulate the data in regard to the general
 * information about the platform (site): terms of service, legal issues, contact details et cetera.
 */
@RestController
@RequestMapping(PLATFORM_INFORMATION)
@RequiredArgsConstructor
public class PlatformInformationController {

	private final PlatformInformationService platformInformationService;

	/**
	 * Gets by id a chunk of info (represented as a chapter that has a title and a text) regarding the platform.
	 *
	 * <p> Checks if the requested info exists, if it doesn't then returns HttpStatus 'NOT FOUND'.</p>
	 *
	 * @param infoId id of the information we want to receive
	 * @return the found info and HttpStatus 'OK'
	 */
	@GetMapping(PLATFORM_INFORMATION_BY_ID)
	@ApiOperation(value = "Get a chunk of information about the platform")
	public ResponseEntity<PlatformInformationDTO> getInfoById(@PathVariable("infoId") Integer infoId) {
		PlatformInformationDTO platformInformationDTO = platformInformationService.getInfoById(infoId);
		return ResponseEntity
				.status((platformInformationDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(platformInformationDTO);
	}

	/**
	 * Saves (creates) a chapter of information about the platform.
	 *
	 * <p>Verifies whether a user possesses the authority to add information.</p>
	 *
	 * @param userPrincipal          the authorized user's data, in the current case it must be an administrator in
	 *                               order to be able to perform the operation
	 * @param platformInformationDTO a DTO that contains the information to be saved
	 * @return the saved info and HttpStatus 'CREATED' and saves new info to DB
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('SAVE_PLATFORM_INFORMATION')")
	@ApiOperation(value = "Save a chapter of information about the platform",
			authorizations = {@Authorization(value = "Authorization")})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = HttpStatuses.CREATED, response = PlatformInformationDTO.class),
			@ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
	})
	public ResponseEntity<PlatformInformationDTO> saveInfo(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@Valid @RequestBody PlatformInformationDTO platformInformationDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(platformInformationService.saveInfo(userPrincipal, platformInformationDTO));
	}

	/**
	 * Updates a chapter of information about the platform by id.
	 *
	 * <p>Verifies whether a user possesses the authority to update information.</p>
	 *
	 * @param userPrincipal          the authorized user's data, in the current case it must be an administrator in
	 *                               order to be able to perform the operation
	 * @param platformInformationDTO a DTO that contains the information to be updated
	 * @return the updated info and HttpStatus 'OK' and updates info in DB
	 */
	@PutMapping()
	@PreAuthorize("hasAnyAuthority('UPDATE_PLATFORM_INFORMATION')")
	@ApiOperation(value = "Update a chapter of information about the platform",
			authorizations = {@Authorization(value = "Authorization")})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = HttpStatuses.OK, response = PlatformInformationDTO.class),
			@ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
	})
	public ResponseEntity<PlatformInformationDTO> updateInfo(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@Valid @RequestBody PlatformInformationDTO platformInformationDTO) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(platformInformationService.updateInfo(userPrincipal, platformInformationDTO));
	}
}
