package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.dto.post.LatestPostDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.softserveinc.dokazovi.controller.EndPoints.IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;

@RestController
@RequestMapping(EndPoints.POST)
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@ApiOperation(value = "Find latest published posts")
	@ApiPageable
	@GetMapping(POST_LATEST)
	public ResponseEntity<Page<LatestPostDTO>> findLatestPublished(
			@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByStatus(PostStatus.PUBLISHED, pageable));
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
