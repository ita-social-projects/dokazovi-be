package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.POST_IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_DIRECTION;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_EXPERT;

@RestController
@RequestMapping(EndPoints.POST)
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@ApiOperation(value = "Find latest published posts")
	@ApiPageable
	@GetMapping(POST_LATEST)
	public ResponseEntity<Page<PostDTO>> findLatestPublished(
			@PageableDefault(sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByStatus(PostStatus.PUBLISHED, pageable));
	}

	@ApiPageable
	@ApiOperation(value = "Find important posts")
	@GetMapping(POST_IMPORTANT)
	public ResponseEntity<Page<PostDTO>> findImportant(
			@PageableDefault(size = 3, sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findImportantPosts(pageable));
	}

	@ApiPageable
	@ApiOperation(value = "Find latest posts by main direction")
	@GetMapping(POST_LATEST_BY_DIRECTION)
	public ResponseEntity<Page<PostDTO>> findLatestByDirection(
			@PageableDefault(size = 6, sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable,
			@ApiParam(value = "Direction id")
			@RequestParam Integer direction,
			@ApiParam(value = "You can use multiple comma-separated type IDs, e.g. ?types=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> type,
			@ApiParam(value = "You can use multiple comma-separated tag IDs, e.g. ?tags=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> tag) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByMainDirection(direction, type, tag, PostStatus.PUBLISHED, pageable));
	}

	@ApiPageable
	@ApiOperation(value = "Find latest posts by some expert")
	@GetMapping(POST_LATEST_BY_EXPERT)
	public ResponseEntity<Page<PostDTO>> findLatestByExpert(
			@PageableDefault(size = 9, sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable,
			@ApiParam(value = "Expert's id")
			@RequestParam Integer expert,
			@ApiParam(value = "Post type id")
			@RequestParam(required = false) Set<Integer> type) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByExpert(expert, type, PostStatus.PUBLISHED, pageable));
	}
}
