package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.service.OriginService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.softserveinc.dokazovi.controller.EndPoints.ORIGIN;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_GET_USER_BY_ID;

@RestController
@RequestMapping(ORIGIN)
@RequiredArgsConstructor
public class OriginController {
	private final OriginService originService;

	@GetMapping
	@ApiOperation(value = "Get all origins")
	public ResponseEntity<List<OriginDTO>> getAllOrigins() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(originService.findAllOrigins());
	}

	@GetMapping(POST_GET_USER_BY_ID)
	@ApiOperation(value = "Get all origins by user id")
	public ResponseEntity<List<OriginDTO>> getAllOriginsByUserId(@PathVariable("userId") Integer userId) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(originService.findAllOriginsByUserId(userId));
	}
}