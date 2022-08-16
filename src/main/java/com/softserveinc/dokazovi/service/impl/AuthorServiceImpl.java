package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.author.AuthorForAdminDTO;
import com.softserveinc.dokazovi.dto.author.AuthorDTO;
import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserPromotionLevel;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.InstitutionMapper;
import com.softserveinc.dokazovi.repositories.CityRepository;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final DoctorRepository doctorRepository;
	private final CityRepository cityRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final InstitutionMapper institutionMapper;


	@Override
	public AuthorDTO save(AuthorDTO authorDTO, UserPrincipal userPrincipal) {
		if (doctorRepository.findById(authorDTO.getId()).isPresent()) {
			update(authorDTO, userPrincipal);
		}
		AuthorDTO dto;
		if (userPrincipal.getAuthorities().stream()
				.anyMatch(
						grantedAuthority -> grantedAuthority.getAuthority()
								.equals(RolePermission.CREATE_AUTHOR.getAuthority()))) {
			DoctorEntity doctorEntity = createDoctorEntity(authorDTO);
			DoctorEntity doctor = doctorRepository.save(doctorEntity);
			dto = toAuthorDTO(doctor.getProfile(), doctor);
			return dto;
		}
		throw new ForbiddenPermissionsException();
	}

	private AuthorDTO toAuthorDTO(UserEntity savedUser, DoctorEntity savedDoctor) {
		return AuthorDTO.builder().id(savedDoctor.getId())
				.socialNetwork(savedDoctor.getSocialNetwork())
				.avatar(savedUser.getAvatar())
				.bio(savedDoctor.getBio())
				.firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName())
				.mainInstitution(institutionMapper.toExpertInstitutionDTO(savedDoctor.getMainInstitution()))
				.email(savedUser.getEmail())
				.build();
	}

	private DoctorEntity createDoctorEntity(AuthorDTO authorDTO) {
		CityEntity city = cityRepository.getOne(authorDTO.getMainInstitution().getCity().getId());
		return DoctorEntity.builder()
				.bio(authorDTO.getBio())
				.qualification(authorDTO.getQualification())
				.socialNetwork(authorDTO.getSocialNetwork())
				.publishedPosts(0L)
				.promotionScale(1.0)
				.profile(toUserEntity(authorDTO))
				.promotionLevel(UserPromotionLevel.BASIC)
				.city(city)
				.build();
	}

	private UserEntity toUserEntity(AuthorDTO authorDTO) {
		return UserEntity.builder()
				.id(null)
				.firstName(authorDTO.getFirstName())
				.lastName(authorDTO.getLastName())
				.email(authorDTO.getEmail())
				.status(UserStatus.ACTIVE)
				.enabled(true)
				.role(RoleEntity.builder()
						.id(3)
						.name("Doctor")
						.permissions(Set.of(RolePermission.SAVE_OWN_PUBLICATION))
						.build())
				.password(passwordEncoder.encode("Kolala"))
				.createdAt(Timestamp.valueOf(LocalDateTime.now()))
				.build();
	}

	@Override
	public AuthorDTO update(AuthorDTO authorDTO, UserPrincipal userPrincipal) {
		if (userPrincipal.getAuthorities().stream()
				.anyMatch(
						grantedAuthority -> grantedAuthority.getAuthority()
								.equals(RolePermission.UPDATE_AUTHOR.getAuthority()))) {
			DoctorEntity doctor = doctorRepository.getOne(authorDTO.getId());
			doctor.setBio(authorDTO.getBio());
			doctor.setQualification(authorDTO.getQualification());
			doctor.setCity(cityRepository.getOne(authorDTO.getMainInstitution().getCity().getId()));
			doctor.setSocialNetwork(authorDTO.getSocialNetwork());
			UserEntity user = userRepository.getOne(doctor.getProfile().getId());
			user.setEmail(authorDTO.getEmail());
			user.setFirstName(authorDTO.getFirstName());
			user.setLastName(authorDTO.getLastName());
			user.setDoctor(doctor);
			doctor.setProfile(user);
			DoctorEntity savedDoctor = doctorRepository.save(doctor);
			return toAuthorDTO(savedDoctor.getProfile(), savedDoctor);
		}
		throw new ForbiddenPermissionsException();
	}

	@Override
	public Boolean delete(Integer authorId, UserPrincipal userPrincipal) {
		if (userPrincipal.getAuthorities().stream()
				.anyMatch(
						grantedAuthority -> grantedAuthority.getAuthority()
								.equals(RolePermission.DELETE_AUTHOR.getAuthority()))) {
			Integer userId = doctorRepository.getOne(authorId).getProfile().getId();
			doctorRepository.deleteById(authorId);
			userRepository.deleteById(userId);
			return true;
		}
		throw new ForbiddenPermissionsException();
	}

	@Override
	public Page<AuthorForAdminDTO> getAuthors(Pageable pageable) {
		Page<DoctorEntity> all = doctorRepository.findAll(pageable);
		return all.map(doctorEntity -> {
			UserEntity one = userRepository.getOne(doctorEntity.getProfile().getId());
			String regionName = doctorEntity.getCity().getRegion().getName();
			String cityName = doctorEntity.getCity().getName();
			return AuthorForAdminDTO.builder()
					.id(doctorEntity.getId())
					.firstName(one.getFirstName())
					.lastName(one.getLastName())
					.cityName(cityName)
					.regionName(regionName)
					.creationDate(one.getCreatedAt())
					.updateTime(one.getEditedAt())
					.build();

		});
	}
}
