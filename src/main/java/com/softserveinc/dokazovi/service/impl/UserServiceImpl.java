package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.entity.payload.SignUpRequest;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
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
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final UserMapper userMapper;
	private final VerificationTokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;

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
	public Page<UserDTO> findAllExpertsByDirectionsAndRegions(Set<Integer> directionsIds, Set<Integer> regionsIds,
			Pageable pageable) {
		Double allPublishedPostsCount = Double.valueOf(this.getAllPostsCountByStatus(PostStatus.PUBLISHED));
		Double allAuthorsCount = Double.valueOf(this.getActiveUsersCountHavingPostWithStatus(PostStatus.PUBLISHED));

		Double averagePublishedPostsPerAuthor = (allAuthorsCount == 0.0) ? 1.0 :
				Math.ceil(allPublishedPostsCount / allAuthorsCount);

		if (CollectionUtils.isEmpty(directionsIds) && CollectionUtils.isEmpty(regionsIds)) {
			return userRepository.findAllActiveUsersOrderByRating(
					allPublishedPostsCount, averagePublishedPostsPerAuthor, pageable)
					.map(userMapper::toUserDTO);
		} else if (CollectionUtils.isEmpty(directionsIds)) {
			return userRepository.findAllActiveUsersByRegionsIdsInOrderByRating(regionsIds,
					allPublishedPostsCount, averagePublishedPostsPerAuthor, pageable)
					.map(userMapper::toUserDTO);
		} else if (CollectionUtils.isEmpty(regionsIds)) {
			return userRepository.findAllActiveUsersByDirectionsIdsInOrderByDirectionsMatchesThenByRating(
					directionsIds, allPublishedPostsCount, averagePublishedPostsPerAuthor, pageable)
					.map(userMapper::toUserDTO);
		}

		return userRepository.findAllActiveUsersByDirectionsIdsInAndRegionsIdsInOrderByDirectionsMatchesThenByRating(
				directionsIds, regionsIds, allPublishedPostsCount, averagePublishedPostsPerAuthor, pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	public Page<UserDTO> findRandomExpertPreview(Set<Integer> directionsIds, Pageable pageable) {
		if (CollectionUtils.isEmpty(directionsIds)) {
			return userRepository.findRandomUsersByStatus(UserStatus.ACTIVE, pageable)
					.map(userMapper::toUserDTO);
		}

		return userRepository.findRandomUsersByDirectionsAndStatus(directionsIds, UserStatus.ACTIVE, pageable)
				.map(userMapper::toUserDTO);
	}

	@Override
	public Integer getActiveUsersCountHavingPostWithStatus(PostStatus postsStatus) {
		return userRepository.countUsersWhereExistsPostWithStatus(postsStatus);
	}

	@Override
	public Integer getAllPostsCountByStatus(PostStatus postStatus) {
		return postRepository.countAllByStatus(postStatus);
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
