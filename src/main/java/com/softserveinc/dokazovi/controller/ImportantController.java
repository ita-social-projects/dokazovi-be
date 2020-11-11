package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportantController {

	private final PostService postService;

	public ImportantController(PostService postService) {
		this.postService = postService;
	}

	@ApiPageable
	@ApiOperation(value = "Find important posts")
	@GetMapping(EndPoints.IMPORTANT)
	public ResponseEntity<Page<ImportantPostDTO>> findImportant(
			@PageableDefault(size = 3)
			@SortDefault.SortDefaults({
					@SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
			}) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findImportantPosts(pageable));
	}
}
