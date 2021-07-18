package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.repositories.VerificationTokenRepository;
import com.softserveinc.dokazovi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


/**
 * The UserServiceImpl is responsible for doing any required logic
 * with the user data received by the User Controller.
 * It provides logic to operate on the data sent to and from the User repository.
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final VerificationTokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;

	private static final String HAS_NO_DIRECTIONS = "hasNoDirections";
	private static final String HAS_NO_REGIONS = "hasNoRegions";
	private static final String HAS_NO_USERNAME = "hasNoUserName";

	/**
	 * Gets user by email.
	 *
	 * @param email email of user that we want to get
	 * @return found user by email from user repository
	 */
	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	/**
	 * Gets user by email.
	 *
	 * @param email email of user that we want to get
	 * @return found user by email from user repository
	 */
	@Override
	public UserEntity findUserEntityByEmail(String email) {
		return userRepository.findUserEntityByEmail(email).orElse(null);
	}

	/**
	 * Gets all users.
	 *
	 * @param pageable received from User controller
	 * @return all found users
	 */
	@Override
	public Page<UserEntity> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	/**
	 * Gets user by id.
	 *
	 * @param userId received from User controller
	 * @return found  doctor by id from user repository
	 */
	@Override
	public UserDTO findExpertById(Integer userId) {
		return userMapper.toUserDTO(userRepository.findById(userId).orElse(null));
	}

	/**
	 * Gets doctors by search criteria.
	 * For example, if directions, regions and user name fields
	 * are empty, the findDoctorsProfiles method without parameters is called
	 *
	 * @param userSearchCriteria received from User controller
	 * @param pageable received from User controller
	 * @return found doctor by criteria
	 */
	@Override
	@Transactional
	public Page<UserDTO> findAllExperts(UserSearchCriteria userSearchCriteria, Pageable pageable) {

		if (validateParameters(userSearchCriteria, HAS_NO_DIRECTIONS, HAS_NO_REGIONS, HAS_NO_USERNAME)) {
			return userRepository.findDoctorsProfiles(pageable).map(userMapper::toUserDTO);
		}

		List<String> userName = userSearchCriteria.getUserNameList();

		if ((validateParameters(userSearchCriteria, HAS_NO_DIRECTIONS, HAS_NO_REGIONS)) && userName.size() == 1) {
			final String name = userName.get(0);
			return userRepository.findDoctorsByName(name, pageable).map(userMapper::toUserDTO);
		}

		if ((validateParameters(userSearchCriteria, HAS_NO_DIRECTIONS, HAS_NO_REGIONS)) && userName.size() == 2) {
			final String firstName = userName.get(0);
			final String lastName = userName.get(1);
			return userRepository.findDoctorsByName(firstName, lastName, pageable).map(userMapper::toUserDTO);
		}

		if ((validateParameters(userSearchCriteria, HAS_NO_DIRECTIONS, HAS_NO_USERNAME))) {
			return userRepository.findDoctorsProfilesByRegionsIds(
					userSearchCriteria.getRegions(), pageable)
					.map(userMapper::toUserDTO);
		}

		if ((validateParameters(userSearchCriteria, HAS_NO_REGIONS, HAS_NO_USERNAME))) {
			return userRepository.findDoctorsProfilesByDirectionsIds(
					userSearchCriteria.getDirections(), pageable)
					.map(userMapper::toUserDTO);
		}

		if ((validateParameters(userSearchCriteria, HAS_NO_USERNAME))) {
			return userRepository
					.findDoctorsProfiles(userSearchCriteria.getDirections(), userSearchCriteria.getRegions(), pageable)
					.map(userMapper::toUserDTO);
		}

		throw new EntityNotFoundException("Wrong search parameters");
	}

	private boolean validateParameters(UserSearchCriteria userSearchCriteria, String... args) {

		if (args.length == 3) {
			return !userSearchCriteria.hasName() && !userSearchCriteria.hasRegions() && !userSearchCriteria
					.hasDirections();
		}

		if (args.length == 2 && args[0].contains(HAS_NO_DIRECTIONS) && args[1].contains(HAS_NO_REGIONS)) {
			return !userSearchCriteria.hasRegions() && !userSearchCriteria.hasDirections();
		}

		if (args.length == 2 && args[0].contains(HAS_NO_DIRECTIONS) && args[1].contains(HAS_NO_USERNAME)) {
			return !userSearchCriteria.hasDirections() && !userSearchCriteria.hasName();
		}

		if (args.length == 2 && args[0].contains(HAS_NO_REGIONS) && args[1].contains(HAS_NO_USERNAME)) {

			return !userSearchCriteria.hasRegions() && !userSearchCriteria.hasName();
		}

		if (args.length == 1 && args[0].contains(HAS_NO_USERNAME)) {
			return !userSearchCriteria.hasName();
		}

		return false;
	}

	/**
	 * Gets random experts by directions.
	 * If directions are empty, gets random experts without filters
	 *
	 * @param directionsIds the directions ids received from User controller
	 * @param pageable received from User controller
	 * @return found doctor by directions
	 */
	@Override
	public Page<UserDTO> findRandomExpertPreview(Set<Integer> directionsIds, Pageable pageable) {
		if (CollectionUtils.isEmpty(directionsIds)) {
			return userRepository.findRandomExperts(pageable)
					.map(userMapper::toUserDTO);
		}

		return userRepository.findRandomExpertsByDirectionsIdIn(directionsIds, pageable)
				.map(userMapper::toUserDTO);
	}

	/**
	 * Sets the user is enabled.
	 *
	 * @param user user received from Auth controller
	 */
	@Override
	public void setEnableTrue(UserEntity user) {
		UserEntity userEntity = userRepository.findById(user.getId()).orElse(null);
		if (userEntity == null) {
			throw new BadRequestException("Something went wrong!!!");
		}
		userEntity.setEnabled(true);
		userRepository.save(userEntity);
	}

	/**
	 * Gets the verification token received from tokenRepository.
	 *
	 * @param verificationToken received from Auth controller
	 * @return found VerificationToken
	 */
	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		return tokenRepository.findByToken(verificationToken);
	}

	/**
	 * Gets the verification token received from tokenRepository.
	 *
	 * @param user user received from Mail Sender
	 * @param token token received from Mail Sender
	 */
	@Override
	public void createVerificationToken(UserEntity user, String token) {
		VerificationToken myToken = VerificationToken.builder()
				.user(user)
				.token(token)
				.build();
		tokenRepository.save(myToken);
	}

	@Override
	public UserEntity getById (Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public UserEntity save (UserEntity user) {
		return userRepository.save(user);
	}
}
