package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.service.DirectionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.DIRECTION;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_GET_USER_BY_ID;

/**
 * The Direction controller responsible for handling requests for posts.
 */
@RestController
@RequestMapping(DIRECTION)
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;

    /**
     * Gets all directions from the database.
     *
     * @return list of all directions and 'OK' HttpStatus
     */
    @GetMapping
    @ApiOperation(value = "Get all directions")
    public ResponseEntity<List<DirectionDTO>> getAllDirections() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(directionService.findAllDirections());
    }

    /**
     * Gets all posts directions by user id.
     *
     * @param userId user id
     * @return list of all found post directions by user id and 'OK' HttpStatus
     */
    @GetMapping(POST_GET_USER_BY_ID)
    @ApiOperation(value = "Get all directions by user id")
    public ResponseEntity<List<DirectionDTO>> getAllDirectionsByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(directionService.findAllDirectionsByUserId(userId));
    }
}
