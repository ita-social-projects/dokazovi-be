package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.IMPORTANT)
public class ImportantController {

	private final PostService postService;
	@Autowired
	public ImportantController(PostService postService) {
		this.postService = postService;
	}

	@ApiPageable
	@GetMapping("/")
	//TODO: change pagination mode to "3 posts per page"
	public ResponseEntity<Page<PostEntity>> findAll(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findImportantPosts(pageable));
	}
}
