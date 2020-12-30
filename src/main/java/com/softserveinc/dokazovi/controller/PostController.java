package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.service.PostService;
import com.softserveinc.dokazovi.service.PostTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.POST_IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_DIRECTION;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_EXPERT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_TYPE;

@RestController
@RequestMapping(EndPoints.POST)
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final PostTypeService postTypeService;

	@ApiOperation(value = "Save post of user")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = HttpStatuses.CREATED, response = PostDTO.class),
			@ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
	})
	@PostMapping
	public ResponseEntity<PostDTO> save(@Valid @RequestBody PostSaveFromUserDTO postSaveFromUserDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(postService.saveFromUser(postSaveFromUserDTO, null));
	}

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
	@ApiOperation(value = "Find latest posts by direction")
	@GetMapping(POST_LATEST_BY_DIRECTION)
	public ResponseEntity<Page<PostDTO>> findLatestByDirection(
			@PageableDefault(size = 6, sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable,
			@ApiParam(value = "Direction id")
			@RequestParam Integer direction,
			@ApiParam(value = "You can use multiple comma-separated type IDs, e.g. ?type=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> type,
			@ApiParam(value = "You can use multiple comma-separated tag IDs, e.g. ?tag=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> tag) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByDirection(direction, type, tag, PostStatus.PUBLISHED, pageable));
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

	@ApiOperation(value = "Find all types of posts")
	@GetMapping(POST_TYPE)
	public ResponseEntity<List<PostTypeDTO>> findAllPostType() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postTypeService.findAll());
	}
}
