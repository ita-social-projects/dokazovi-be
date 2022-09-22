package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.author.AuthorDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;

public interface AuthorService {

	AuthorDTO save(AuthorDTO authorDTO, UserPrincipal userPrincipal);

	AuthorDTO update(AuthorDTO authorDTO, Integer doctorId, UserPrincipal userPrincipal);

	Boolean delete(Integer authorId, UserPrincipal userPrincipal);
}