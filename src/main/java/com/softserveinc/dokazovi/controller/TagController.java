package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.TAG;
import static com.softserveinc.dokazovi.controller.EndPoints.TAG_FIND_BY_VALUE;

/**
 * The Class TagController responsible for handling requests for regions
 */
@RestController
@RequestMapping(TAG)
@RequiredArgsConstructor
public class TagController {

	private final TagService tagService;

	/**
	 * Find tag by its name (or by several letters it contains).
	 *
	 * @param value the name of tag
	 * @param limit the limit of results amount
	 * @return list with limited amount of found tags and 'OK' http status
	 */
	@GetMapping(TAG_FIND_BY_VALUE)
	public ResponseEntity<List<TagDTO>> findByValue(
			@RequestParam String value,
			@RequestParam(defaultValue = "5") Integer limit) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(tagService.findTagsByValue(value, limit));
	}

	/**
	 * Saves (creates) new tag.
	 *
	 * <p>Checks if user has authority to save new tag.</p>
	 *
	 * @param tagSaveDTO DTO that contains new tag info.
	 * @return saved tag and 'CREATED' http status.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('SAVE_TAG')")
	@ApiOperation(value = "Save tag",
			authorizations = {@Authorization(value = "Authorization")})
	public ResponseEntity<TagDTO> saveTag(@Valid @RequestBody TagSaveDTO tagSaveDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(tagService.save(tagSaveDTO));
	}
}
