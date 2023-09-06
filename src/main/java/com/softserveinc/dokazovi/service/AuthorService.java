package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.author.AuthorRequestDTO;
import com.softserveinc.dokazovi.dto.author.AuthorResponseDTO;
import com.softserveinc.dokazovi.entity.AuthorEntity;
import com.softserveinc.dokazovi.security.UserPrincipal;

import java.util.List;

public interface AuthorService {

    AuthorEntity findAuthorById(Integer authorId);

    AuthorEntity save(AuthorRequestDTO authorRequestDTO, UserPrincipal userPrincipal);

    AuthorEntity update(Integer authorId, AuthorRequestDTO authorRequestDTO, UserPrincipal userPrincipal);

    Integer delete(Integer authorId, UserPrincipal userPrincipal);

    List<AuthorResponseDTO> findAllAuthors();
}