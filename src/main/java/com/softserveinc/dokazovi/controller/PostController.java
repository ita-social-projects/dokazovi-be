package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@ApiOperation(value = "Find all posts")
	@ApiPageable
	@GetMapping("/")
	public ResponseEntity<Page<PostDTO>> findAll(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAll(pageable));
	}

	@ApiOperation(value = "Find posts by status")
	@ApiPageable
	@GetMapping("/status")
	public ResponseEntity<Page<PostDTO>> findAllByStatus(@RequestParam PostStatus postStatus, Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findAllByStatus(postStatus, pageable));
	}
}
