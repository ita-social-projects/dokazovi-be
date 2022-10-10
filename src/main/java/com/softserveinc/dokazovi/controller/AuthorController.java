package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.author.AuthorDTO;
import com.softserveinc.dokazovi.dto.payload.ApiResponseMessage;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTHOR_GET_AUTHOR_BY_ID;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTHOR;

@RestController
@RequestMapping(AUTHOR)
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping()
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "Create author",
            authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = AuthorDTO.class),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO author,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorService.save(author, userPrincipal));
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "update author",
            authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = AuthorDTO.class),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO author,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(200)
                .body(authorService.update(author, userPrincipal));
    }

    @DeleteMapping(AUTHOR_GET_AUTHOR_BY_ID)
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "remove author",
            authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = ApiResponseMessage.class),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    public ResponseEntity<ApiResponseMessage> deleteAuthor(@PathVariable("authorId") Integer authorId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        ApiResponseMessage apiResponseMessage;
        apiResponseMessage = ApiResponseMessage.builder()
                .success(authorService.delete(authorId, userPrincipal))
                .message(String.format("Doctor %s deleted successfully", authorId))
                .build();
        return ResponseEntity.ok().body(apiResponseMessage);
    }
}