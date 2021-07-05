package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.payload.ApiResponseMessage;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostMainPageDTO;
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
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_POST_TYPES_AND_ORIGINS;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_SET_IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_TYPE;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_VIEW_COUNT;

/**
 * The Post controller responsible for handling requests for posts.
 */
@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final PostTypeService postTypeService;

	/**
	 * Saves(creates) new post.
	 *
	 * <p>Checks if user has authority to create own post.</p>
	 *
	 * @param postSaveFromUserDTO DTO of new post created by authorized user
	 * @param userPrincipal       authorized user data
	 * @return HttpStatus 'CREATED' and saves new post to db
	 */
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

	/**
	 * Finds latest published published posts.
	 *
	 * @param pageable interface for pagination information
	 * @return page with found posts and 'OK' httpStatus
	 */
	@GetMapping(POST_LATEST)
	@ApiPageable
	@ApiOperation(value = "Find latest published posts")
	public ResponseEntity<Page<PostDTO>> findLatestPublished(
			@PageableDefault(sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByStatus(PostStatus.PUBLISHED, pageable));
	}

	/**
	 * Finds important posts.
	 *
	 * @param pageable interface for pagination information
	 * @return page with all posts with important status and HttpStatus 'OK'
	 */
	@GetMapping(POST_IMPORTANT)
	@ApiPageable
	@ApiOperation(value = "Find important posts")
	public ResponseEntity<Page<PostDTO>> findImportant(
			@PageableDefault(size = 3, sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findImportantPosts(pageable));
	}

	@GetMapping(POST_SET_IMPORTANT)
	@PreAuthorize("hasAuthority('SET_IMPORTANCE')")
	@ApiPageable
	@ApiOperation(value = "Set posts as important with order and remove previous order",
			authorizations = {@Authorization(value = "Authorization")})
	public ResponseEntity<ApiResponseMessage> setPostAsImportant(
			@ApiParam(value = "Multiple comma-separated posts IDs (new order), e.g. ?posts=1,2,3,4...",
					type = "string")
			@RequestParam(required = true) Set<Integer> posts) {
		ApiResponseMessage apiResponseMessage;
		try {
			apiResponseMessage = ApiResponseMessage.builder()
					.success(postService.setPostsAsImportantWithOrder(posts))
					.message("Posts updated successfully")
					.build();
		} catch (Exception e) {
			apiResponseMessage = ApiResponseMessage.builder()
					.success(false)
					.message(e.getMessage())
					.build();
		}
		return ResponseEntity.ok().body(apiResponseMessage);
	}

	/**
	 * Finds latest posts by direction id.
	 *
	 * @param pageable  interface for pagination information
	 * @param direction direction id
	 * @param type      type ids
	 * @param tag       tag ids
	 * @return page with found latest posts and HttpStatus 'OK'
	 */
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

	/**
	 * findLatestByExpert method returns latest post by expert id.
	 *
	 * @param pageable interface for pagination information
	 * @param expert   expert id
	 * @param type     post type id
	 * @return page with found posts and HttpStatus 'OK'
	 */
	@GetMapping(POST_LATEST_BY_EXPERT)
	@ApiPageable
	@ApiOperation(value = "Find latest posts by some expert")
	public ResponseEntity<Page<PostDTO>> findLatestByExpert(
			@PageableDefault Pageable pageable,
			@ApiParam(value = "Expert's id")
			@RequestParam Integer expert,
			@ApiParam(value = "Post type id")
			@RequestParam(required = false) Set<Integer> type,
			@ApiParam(value = "Direction id")
			@RequestParam(required = false) Set<Integer> direction) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByExpertAndTypeAndDirections(expert, type, direction, pageable));
	}

	/**
	 * Gets all post types from db.
	 *
	 * @return list with all post types and HttpStatus 'OK'
	 */
	@GetMapping(POST_TYPE)
	@ApiOperation(value = "Find all types of posts")
	public ResponseEntity<List<PostTypeDTO>> findAllPostType() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postTypeService.findAll());
	}

	/**
	 * Gets post by its id.
	 *
	 * <p> Checks if found post exists, if no - returns HttpStatus 'NOT FOUND'.</p>
	 *
	 * @param postId id of post that we want to get
	 * @return found post and HttpStatus 'OK'
	 */
	@GetMapping(POST_GET_POST_BY_ID)
	@ApiOperation(value = "Get post by Id, as a path variable.")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId) {
		PostDTO postDTO = postService.findPostById(postId);
		return ResponseEntity
				.status((postDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(postDTO);
	}

	/**
	 * Gets all posts by directions by post types and by origins.
	 *
	 * <p>If something went wrong returns HttpStatus 'NO CONTENT'.</p>
	 *
	 * @param pageable   interface for pagination information
	 * @param directions direction's ids by which the search is performed
	 * @param types      the type's ids by which the search is performed
	 * @param origins    the origin's ids by which the search is performed
	 * @return page with all posts filtered by directions, by post types and by origins and HttpStatus 'OK'
	 */
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

	/**
	 * Deletes post by id (marks post as archived).
	 *
	 * <p>Checks if user has authority to delete found post.
	 * If something went wrong returns unsuccessful response message.</p>
	 *
	 * @param userPrincipal authorized user data
	 * @param postId        the post id
	 * @return marks found post as archived and prints that post was successfully deleted
	 */
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

	/**
	 * Updates post by id.
	 *
	 * <p>Checks if user has authority to update found post.
	 * If something went wrong returns unsuccessful response message.</p>
	 *
	 * @param userPrincipal       authorized user data
	 * @param postSaveFromUserDTO DTO of updated post
	 * @return updates post in db and prints message 'post updated successfully'
	 */
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

	/**
	 * Gets posts filtered by author id and directions.
	 *
	 * <p>If something went wrong returns 'NOT FOUND' HttpStatus.</p>
	 *
	 * @param pageable   interface for pagination information
	 * @param authorId   the author id
	 * @param directions directions ids
	 * @return found posts filtered by author id and directions and HttpStatus 'OK'
	 */
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

	/**
	 * Gets latest posts by post types and origin with names of fields
	 *
	 * @param pageable interface for pagination information
	 * @return found posts filtered by Post Types and origins with names of main page fields and HttpStatus 'OK'
	 */
	@GetMapping(POST_LATEST_BY_POST_TYPES_AND_ORIGINS)
	@ApiPageable
	@ApiOperation(value = "Find latest published posts by post types and origins")
	public ResponseEntity<Page<PostMainPageDTO>> findLatestByPostTypesAndOrigins(
			@PageableDefault(size = 16) Pageable pageable) {
		Page<PostMainPageDTO> posts = postService.findLatestByPostTypesAndOrigins(pageable);
		return ResponseEntity
				.status((posts.getTotalElements() != 0) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
				.body(posts);
	}

	@ApiPageable
	@ApiOperation(value = "Get Post View Count")
	@GetMapping(POST_VIEW_COUNT)
	public Integer getPostViewCount(@RequestParam String url) {
		return postService.getPostViewCount(url);
	}
}
