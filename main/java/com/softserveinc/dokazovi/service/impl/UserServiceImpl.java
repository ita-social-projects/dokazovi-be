package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.payload.SignUpRequest;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.repositories.VerificationTokenRepository;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.util.StringToNameParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final VerificationTokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;

	public static final String PATTERN_NAME = "[A-Z,А-Я,a-z,а-я\\s\\-]{1,}";

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public Page<UserEntity> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public UserDTO findExpertById(Integer userId) {
		return userMapper.toUserDTO(userRepository.findById(userId).orElse(null));
	}

	@Override
	@Transactional
	public Page<UserDTO> findAllExperts(UserSearchCriteria userSearchCriteria, Pageable pageable) {

		boolean directionsIsEmpty = userSearchCriteria.getDirections().isEmpty();
		boolean regionsIsEmpty = userSearchCriteria.getRegions().isEmpty();
		boolean nameIsEmpty = userSearchCriteria.getUserName().isEmpty();

		if (directionsIsEmpty && regionsIsEmpty && nameIsEmpty) {
			return findAllExpertsWithoutConditions(pageable);
		}

		if (directionsIsEmpty && regionsIsEmpty) {
			return findAllExpertsByName(userSearchCriteria.getUserName(), pageable);
		}

		if (directionsIsEmpty && nameIsEmpty) {
			return findAllExpertsByRegions(userSearchCriteria.getRegions(), pageable);
		}

		if (regionsIsEmpty && nameIsEmpty) {
			return findAllExpertsByDirections(userSearchCriteria.getDirections(), pageable);
		}

		if (nameIsEmpty) {
			return findAllExpertsByDirectionsAndRegions(userSearchCriteria.getDirections(),
					userSearchCriteria.getRegions(), pageable);
		}

		throw new EntityNotFoundException("Wrong search parameters");
	}

	@Override
	@Transactional
	public Page<UserDTO> findAllExpertsWithoutConditions(Pageable pageable) {
		return userRepository.findDoctorsProfiles(pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	@Transactional
	public Page<UserDTO> findAllExpertsByDirections(Set<Integer> directionsIds, Pageable pageable) {
		return userRepository
				.findDoctorsProfilesByDirectionsIds(
						directionsIds, pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	@Transactional
	public Page<UserDTO> findAllExpertsByRegions(Set<Integer> regionsIds, Pageable pageable) {
		return userRepository
				.findDoctorsProfilesByRegionsIds(
						regionsIds, pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	@Transactional
	public Page<UserDTO> findAllExpertsByDirectionsAndRegions(Set<Integer> directionsIds,
			Set<Integer> regionsIds, Pageable pageable) {

		return userRepository
				.findDoctorsProfilesByDirectionsIdsAndRegionsIds(directionsIds, regionsIds, pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	@Transactional
	public Page<UserDTO> findAllExpertsByName(String userName, Pageable pageable) {

		userName = userName.trim();

		if (Pattern.matches(PATTERN_NAME, userName)) {
			if (userName.contains(" ")) {
				String[] searchCriterias = userName.split(" ");
				Arrays.sort(searchCriterias, Collections.reverseOrder());

				return userRepository
						.findDoctorsByFirstNameAndLastName(searchCriterias[0], searchCriterias[1], pageable)
						.map(userMapper::toUserDTO);
			} else {

				return userRepository
						.findDoctorsByName(userName, pageable).map(userMapper::toUserDTO);
			}
		}
		throw new IllegalArgumentException("Wrong Name");
	}

	@Override
	public Page<UserDTO> findRandomExpertPreview(Set<Integer> directionsIds, Pageable pageable) {
		if (CollectionUtils.isEmpty(directionsIds)) {
			return userRepository.findRandomExperts(pageable)
					.map(userMapper::toUserDTO);
		}

		return userRepository.findRandomExpertsByDirectionsIdIn(directionsIds, pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	public void setEnableTrue(UserEntity user) {
		UserEntity userEntity = userRepository.findById(user.getId()).orElse(null);
		if (userEntity == null) {
			throw new BadRequestException("Something went wrong!!!");
		}
		userEntity.setEnabled(true);
		userRepository.save(userEntity);
	}

	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		return tokenRepository.findByToken(verificationToken);
	}

	@Override
	public void createVerificationToken(UserEntity user, String token) {
		VerificationToken myToken = VerificationToken.builder()
				.user(user)
				.token(token)
				.build();
		tokenRepository.save(myToken);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserEntity saveUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity registerNewUser(SignUpRequest signUpRequest) {
		UserEntity user = new UserEntity();
		StringToNameParser.setUserNameFromRequest(signUpRequest, user);
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setStatus(UserStatus.NEW);
		user.setEnabled(false);
		return userRepository.save(user);
	}
}
