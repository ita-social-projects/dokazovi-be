package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoints.IMPORTANT)
public class ImportantController {

	private final PostService postService;

	@ApiPageable
	@GetMapping("/")
	//TODO: change pagination mode to "3 posts per page"
	public ResponseEntity<Page<ImportantPostDTO>> findImportant(Pageable pageable) {
		System.out.println("I'm here");
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(postService.findImportantPosts(pageable));
	}
}
