package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.TAG;
import static com.softserveinc.dokazovi.controller.EndPoints.TAG_FIND_BY_VALUE;

@RestController
@RequestMapping(TAG)
@RequiredArgsConstructor
public class TagController {

	private final TagService tagService;

	@ApiOperation(value = "Find tag by value",
			authorizations = {@Authorization(value = "Authorization")})
	@GetMapping(TAG_FIND_BY_VALUE)
	public ResponseEntity<List<TagDTO>> findByValue(
			@RequestParam String value,
			@RequestParam(defaultValue = "5") Integer limit) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(tagService.findTagsByValue(value, limit));
	}

	@ApiOperation(value = "Save tag",
			authorizations = {@Authorization(value = "Authorization")})
	@PostMapping
	public ResponseEntity<TagDTO> saveTag(@RequestBody TagSaveDTO tagSaveDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(tagService.save(tagSaveDTO));
	}
}
