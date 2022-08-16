package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.author.AuthorDTO;
import com.softserveinc.dokazovi.dto.author.AuthorForAdminDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

	AuthorDTO save(AuthorDTO authorDTO, UserPrincipal userPrincipal);

	AuthorDTO update(AuthorDTO authorDTO, UserPrincipal userPrincipal);

	Boolean delete(Integer authorId, UserPrincipal userPrincipal);

	Page<AuthorForAdminDTO> getAuthors(Pageable pageable);
}
