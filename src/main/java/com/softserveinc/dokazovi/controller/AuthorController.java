package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.author.AuthorRequestDTO;
import com.softserveinc.dokazovi.dto.author.AuthorResponseDTO;
import com.softserveinc.dokazovi.dto.payload.ApiResponseMessage;
import com.softserveinc.dokazovi.mapper.AuthorMapper;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import org.springframework.data.domain.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTHOR_GET_AUTHOR_BY_ID;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTHOR;

@RestController
@RequestMapping(AUTHOR)
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping()
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "Create author",
            authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = AuthorRequestDTO.class),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO author,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorMapper.toAuthorResponseDTO(authorService.save(author, userPrincipal)));
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "update author",
            authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = AuthorRequestDTO.class),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    public ResponseEntity<AuthorResponseDTO> updateAuthor(
            @Valid @RequestBody AuthorRequestDTO author,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(200)
                .body(authorMapper.toAuthorResponseDTO(authorService.update(author, userPrincipal)));
    }

    @DeleteMapping(AUTHOR_GET_AUTHOR_BY_ID)
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "remove author",
            authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = ApiResponseMessage.class),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    public ResponseEntity<Integer> deleteAuthor(@PathVariable Integer authorId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(200)
                .body(authorService.delete(authorId, userPrincipal));
    }

    @GetMapping()
    @ApiOperation(value = "get authors",
            authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK, response = ApiResponseMessage.class),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
    })
    public ResponseEntity<Page<AuthorResponseDTO>> getAllAuthors(@PageableDefault(size = 6) Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorService.findAllAuthors(pageable));
    }
}