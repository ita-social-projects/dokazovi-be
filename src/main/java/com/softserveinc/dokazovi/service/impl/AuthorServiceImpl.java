package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.AuthorDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

	@Override
	public AuthorDTO save(AuthorDTO authorDTO, UserPrincipal userPrincipal) {

		return null;
	}

	@Override
	public AuthorDTO update(AuthorDTO authorDTO) {
		return null;
	}

	@Override
	public Boolean delete(AuthorDTO authorDTO) {
		return null;
	}
}
