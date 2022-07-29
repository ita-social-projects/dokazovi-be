package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.AuthorDTO;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
	private final DoctorRepository doctorRepository;

	@Override
	public AuthorDTO save(AuthorDTO authorDTO, UserPrincipal userPrincipal) {
		if (doctorRepository.findById(authorDTO.getId()).isPresent()){
			update(authorDTO, userPrincipal);
		}
		DoctorEntity doctorEntity = createDoctorEntity(authorDTO);
		return null;
	}

	private DoctorEntity createDoctorEntity(AuthorDTO authorDTO) {
		UserEntity userEntity = UserEntity.builder()
				.firstName(authorDTO.getFirstName())
				.lastName(authorDTO.getLastName())
				.avatar(authorDTO.getAvatar())
				.email(authorDTO.getEmail())
				.password("")
				.build();
		return DoctorEntity.builder()
				.build();
	}

	@Override
	public AuthorDTO update(AuthorDTO authorDTO, UserPrincipal userPrincipal) {
		return null;
	}

	@Override
	public Boolean delete(AuthorDTO authorDTO, UserPrincipal userPrincipal) {
		return null;
	}
}
