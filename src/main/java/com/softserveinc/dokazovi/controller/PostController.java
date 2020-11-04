package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.entity.Post;
import com.softserveinc.dokazovi.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@ApiOperation(value = "Get posts by page")
	@ApiPageable
	@GetMapping("/")
	public ResponseEntity<Page<Post>> findAll(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAll(pageable));
	}
}
