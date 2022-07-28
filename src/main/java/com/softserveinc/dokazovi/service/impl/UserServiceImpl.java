package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.AuthorDTO;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserPromotionLevel;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.repositories.VerificationTokenRepository;
import com.softserveinc.dokazovi.service.MailSenderService;
import com.softserveinc.dokazovi.service.PasswordResetTokenService;
import com.softserveinc.dokazovi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


/**
 * The UserServiceImpl is responsible for doing any required logic with the user data received by the User Controller.
 * It provides logic to operate on the data sent to and from the User repository.
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final DoctorRepository doctorRepository;
	private final VerificationTokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final PasswordResetTokenService passwordResetTokenService;
	private final MailSenderService mailSenderService;

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
	 * Gets doctors by search criteria. For example, if directions, regions and user name fields are empty, the
	 * findDoctorsProfiles method without parameters is called
	 *
	 * @param userSearchCriteria received from User controller
	 * @param pageable           received from User controller
	 * @return found doctor by criteria
	 */
	@Override
	@Transactional
	public Page<UserDTO> findAllExperts(UserSearchCriteria userSearchCriteria, Pageable pageable) {

		if (validateParameters(userSearchCriteria, HAS_NO_DIRECTIONS, HAS_NO_REGIONS, HAS_NO_USERNAME)) {
			return userRepository.findDoctorsProfiles(pageable).map(userMapper::toUserDTO);
		}

		final String name = userSearchCriteria.getUserName();

		if ((validateParameters(userSearchCriteria, HAS_NO_DIRECTIONS, HAS_NO_REGIONS))) {
			return userRepository.findDoctorsByName(name, pageable).map(userMapper::toUserDTO);
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
	 * Gets random experts by directions. If directions are empty, gets random experts without filters
	 *
	 * @param directionsIds the directions ids received from User controller
	 * @param pageable      received from User controller
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
	 * @param user  user received from Mail Sender
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
	public UserEntity getById(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public UserEntity update(UserEntity user) {
		if (user != null) {
			UserEntity oldUser = getById(user.getId());
			if (oldUser != null) {
				return userRepository.save(user);
			}
		}
		throw new BadRequestException("Something went wrong!!!");
	}

	@Override
	public void updatePassword(UserEntity user, String password, PasswordResetTokenEntity token) {
		user.setPassword(passwordEncoder.encode(password));
		update(user);
		passwordResetTokenService.delete(token);
	}

	@Override
	public void sendPasswordResetToken(UserEntity user, String origin) {
		String token = UUID.randomUUID().toString();
		passwordResetTokenService.createPasswordResetTokenForUser(user, token);
		mailSenderService.sendEmailWithToken(origin, token, user);
	}

	@Override
	public boolean isPasswordMatches(UserEntity user, String password) {
		return user != null && passwordEncoder.matches(password, user.getPassword());
	}

	@Override
	public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
		UserEntity userEntity = toUserEntity(authorDTO);
		DoctorEntity doctorEntity = toDoctorEntity(authorDTO);
		userEntity.setDoctor(doctorEntity);
		doctorEntity.setProfile(userEntity);
		DoctorEntity savedDoctor = doctorRepository.save(doctorEntity);
		return toAuthorDTO(savedDoctor.getProfile(),savedDoctor);

	}

	private AuthorDTO toAuthorDTO(UserEntity savedUser, DoctorEntity savedDoctor) {
		return AuthorDTO.builder().id(savedDoctor.getId())
				.socialNetwork(savedDoctor.getSocialNetwork())
				.avatar(savedUser.getAvatar())
				.bio(savedDoctor.getBio())
				.firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName())
				.placeOfWork(savedDoctor.getQualification())
				.email(savedUser.getEmail())
				.build();
	}

	private DoctorEntity toDoctorEntity(AuthorDTO authorDTO) {
		return DoctorEntity.builder()
				.bio(authorDTO.getBio())
				.qualification(authorDTO.getPlaceOfWork())
				.socialNetwork(authorDTO.getSocialNetwork())
				.publishedPosts(0L)
				.promotionScale(1.0)
				.promotionLevel(UserPromotionLevel.BASIC)
				.build();
	}

	private UserEntity toUserEntity(AuthorDTO authorDTO) {
		UserEntity userEntity = userMapper.toUser(authorDTO);
		userEntity.setId(null);
		userEntity.setStatus(UserStatus.ACTIVE);
		userEntity.setEnabled(true);
		userEntity.setRole(RoleEntity.builder()
				.id(3)
				.name("Doctor")
				.permissions(Set.of(RolePermission.SAVE_OWN_PUBLICATION))
				.build());
		userEntity.setPassword(passwordEncoder.encode("Kolala"));
		userEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		return userEntity;
	}
}
