package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.service.PostService;
import static com.softserveinc.dokazovi.controller.EndPoints.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@ApiOperation(value = "Get posts by page")
	@ApiPageable
	@GetMapping()
	public ResponseEntity<Page<PostEntity>> findAll(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAll(pageable));
	}

	@ApiPageable
	@ApiOperation(value = "Find important posts")
	@GetMapping(IMPORTANT)
	public ResponseEntity<Page<ImportantPostDTO>> findImportant(
			@PageableDefault(size = 3, sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findImportantPosts(pageable));
	}
}
