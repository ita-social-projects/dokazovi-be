package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.service.PostTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.POST_TYPES;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_TYPES_ALL_TYPES_BY_USER;

/**
 * Class PostTypesController responsible for handling requests for post types
 */
@RestController
@RequestMapping(POST_TYPES)
@RequiredArgsConstructor
public class PostTypesController {

	private final PostTypeService postTypeService;

	/**
	 * getAllPostTypesByUserId method returns all post types of posts published by certain user
	 *
	 * @param userId  Id of user whose materials we want to receive
	 * @return 'OK' HttpStatus and method call from service
	 */
	@GetMapping(POST_TYPES_ALL_TYPES_BY_USER)
	@ApiOperation(value = "Get all types of materials by userId")
	public ResponseEntity<List<PostTypeDTO>> getAllPostTypesByUserId(@PathVariable("userId") Integer userId) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postTypeService.findAllPostTypesByUserId(userId));
	}
}
