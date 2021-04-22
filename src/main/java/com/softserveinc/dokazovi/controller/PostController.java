package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.payload.ApiResponseMessage;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.PostService;
import com.softserveinc.dokazovi.service.PostTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_ALL_POSTS;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_GET_POST_BY_AUTHOR_ID_AND_DIRECTIONS;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_GET_POST_BY_ID;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_DIRECTION;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_EXPERT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_TYPE;

@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final PostTypeService postTypeService;

	@PostMapping
	@PreAuthorize("hasAuthority('SAVE_OWN_PUBLICATION')")
	@ApiOperation(value = "Save post of user",
			authorizations = {@Authorization(value = "Authorization")})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = HttpStatuses.CREATED, response = PostDTO.class),
			@ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
	})
	public ResponseEntity<PostDTO> save(@Valid @RequestBody PostSaveFromUserDTO postSaveFromUserDTO,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(postService.saveFromUser(postSaveFromUserDTO, userPrincipal));
	}

	@GetMapping(POST_LATEST)
	@ApiPageable
	@ApiOperation(value = "Find latest published posts")
	public ResponseEntity<Page<PostDTO>> findLatestPublished(
			@PageableDefault(sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByStatus(PostStatus.PUBLISHED, pageable));
	}

	@GetMapping(POST_IMPORTANT)
	@ApiPageable
	@ApiOperation(value = "Find important posts")
	public ResponseEntity<Page<PostDTO>> findImportant(
			@PageableDefault(size = 3, sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findImportantPosts(pageable));
	}

	@GetMapping(POST_LATEST_BY_DIRECTION)
	@ApiPageable
	@ApiOperation(value = "Find latest posts by direction")
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

	@GetMapping(POST_LATEST_BY_EXPERT)
	@ApiPageable
	@ApiOperation(value = "Find latest posts by some expert")
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

	@GetMapping(POST_TYPE)
	@ApiOperation(value = "Find all types of posts")
	public ResponseEntity<List<PostTypeDTO>> findAllPostType() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postTypeService.findAll());
	}

	@GetMapping(POST_GET_POST_BY_ID)
	@ApiOperation(value = "Get post by Id, as a path variable.")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId) {
		PostDTO postDTO = postService.findPostById(postId);
		return ResponseEntity
				.status((postDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(postDTO);
	}

	@GetMapping(POST_ALL_POSTS)
	@ApiOperation(value = "Get posts, filtered by directions, post types and origins.")
	public ResponseEntity<Page<PostDTO>> getAllPostsByDirectionsByPostTypesAndByOrigins(
			@PageableDefault Pageable pageable,
			@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> directions,
			@ApiParam(value = "Multiple comma-separated post types IDs, e.g. ?post types=1,2,3,4", type = "string")
			@RequestParam(required = false) Set<Integer> types,
			@ApiParam(value = "Multiple comma-separated origins IDs, e.g. ?origins=1,2,3,4...", type = "string")
			@RequestParam(required = false) Set<Integer> origins) {
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(postService
							.findAllByDirectionsAndByPostTypesAndByOrigins(directions, types, origins, pageable));
		} catch (EntityNotFoundException e) {
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(null);
		}
	}

	@DeleteMapping(POST_GET_POST_BY_ID)
	@PreAuthorize("hasAnyAuthority('DELETE_POST', 'DELETE_OWN_POST')")
	@ApiOperation(value = "Delete post by Id, as a path variable.",
			authorizations = {@Authorization(value = "Authorization")})
	public ResponseEntity<ApiResponseMessage> archivePostById(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("postId") Integer postId
	) {
		ApiResponseMessage apiResponseMessage;
		try {
			apiResponseMessage = ApiResponseMessage.builder()
					.success(postService.archivePostById(userPrincipal, postId))
					.message(String.format("post %s deleted successfully", postId))
					.build();
		} catch (EntityNotFoundException e) {
			apiResponseMessage = ApiResponseMessage.builder()
					.success(false)
					.message(e.getMessage())
					.build();
		}
		return ResponseEntity.ok().body(apiResponseMessage);
	}

	@PutMapping()
	@PreAuthorize("hasAnyAuthority('UPDATE_POST', 'UPDATE_OWN_POST')")
	@ApiOperation(value = "Update post by Id, as a path variable.",
			authorizations = {@Authorization(value = "Authorization")})
	public ResponseEntity<ApiResponseMessage> updatePostById(
			@AuthenticationPrincipal UserPrincipal userPrincipal,
			@Valid @RequestBody PostSaveFromUserDTO postSaveFromUserDTO) {

		ApiResponseMessage apiResponseMessage;
		try {
			apiResponseMessage = ApiResponseMessage.builder()
					.success(postService.updatePostById(userPrincipal, postSaveFromUserDTO))
					.message(String.format("post %s updated successfully", postSaveFromUserDTO.getId()))
					.build();
		} catch (EntityNotFoundException | ForbiddenPermissionsException e) {
			apiResponseMessage = ApiResponseMessage.builder()
					.success(false)
					.message(e.getMessage())
					.build();
		}
		return ResponseEntity.ok().body(apiResponseMessage);
	}

	@GetMapping(POST_GET_POST_BY_AUTHOR_ID_AND_DIRECTIONS)
	@ApiOperation(value = "Get post by author Id, as a path variable, and directions.")
	public ResponseEntity<Page<PostDTO>> getPostsByAuthorIdAndDirections(
			@PageableDefault(size = 12) Pageable pageable, @NotNull Integer authorId,
			@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
			@RequestParam(required = false) @NotNull Set<Integer> directions) {
		Page<PostDTO> posts = postService
				.findPostsByAuthorIdAndDirections(pageable, authorId, directions);
		return ResponseEntity
				.status((posts.getTotalElements() != 0) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(posts);
	}
}
