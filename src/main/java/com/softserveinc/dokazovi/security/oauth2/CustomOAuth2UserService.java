package com.softserveinc.dokazovi.security.oauth2;


import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.AuthProvider;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.OAuth2AuthenticationProcessingException;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.security.oauth2.user.OAuth2UserInfo;
import com.softserveinc.dokazovi.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
				.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
						oAuth2User.getAttributes());
		if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		Optional<UserEntity> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
		UserEntity user;
		if (userOptional.isPresent()) {
			user = userOptional.get();
			if (!user.getProvider()
					.equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
						user.getProvider() + " account. Please use your " + user.getProvider() +
						" account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}

		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}

	private UserEntity registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		UserEntity user = new UserEntity();
		user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		user.setProviderId(oAuth2UserInfo.getId());

		List<String> strings = Arrays.asList(oAuth2UserInfo.getName().split(" "));
		if (strings.isEmpty()) {
			user.setFirstName("user");
		}
		if (strings.size() == 2) {
			user.setFirstName(strings.get(0));
			user.setLastName(strings.get(1));
		}
		if (strings.size() != 2) {
			user.setFirstName(oAuth2UserInfo.getName());
		}
		user.setEmail(oAuth2UserInfo.getEmail());
		user.setAvatar(oAuth2UserInfo.getImageUrl());
		user.setStatus(UserStatus.NEW);
		return userRepository.save(user);
	}

	private UserEntity updateExistingUser(UserEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setFirstName(oAuth2UserInfo.getName().split(" ")[0]);
		existingUser.setLastName(oAuth2UserInfo.getName().split(" ")[1]);
		existingUser.setAvatar(oAuth2UserInfo.getImageUrl());
		return userRepository.save(existingUser);
	}

}
