package com.softserveinc.dokazovi.security.oauth2;


import com.softserveinc.dokazovi.entity.ProviderEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.OAuth2AuthenticationProcessingException;
import com.softserveinc.dokazovi.repositories.ProviderRepository;
import com.softserveinc.dokazovi.repositories.RoleRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.security.oauth2.user.OAuth2UserInfo;
import com.softserveinc.dokazovi.security.oauth2.user.OAuth2UserInfoFactory;
import com.softserveinc.dokazovi.util.StringToNameParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	private final ProviderRepository userProviderRepository;

	private final RoleRepository roleRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oauth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(oauth2UserRequest);

		try {
			return processOAuth2User(oauth2UserRequest, oauth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oauth2UserRequest, OAuth2User oauth2User) {
		OAuth2UserInfo oauth2UserInfo = OAuth2UserInfoFactory
				.getOAuth2UserInfo(oauth2UserRequest.getClientRegistration().getRegistrationId(),
						oauth2User.getAttributes());
		if (StringUtils.isEmpty(oauth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		Optional<UserEntity> userOptional = userRepository.findByEmail(oauth2UserInfo.getEmail());

		UserEntity user;
		if (userOptional.isPresent()) {
			user = userOptional.get();
			user = updateExistingUser(user, oauth2UserInfo);
		} else {
			user = registerNewUser(oauth2UserRequest, oauth2UserInfo);
		}

		return UserPrincipal.create(user, oauth2User.getAttributes());
	}

	private UserEntity registerNewUser(OAuth2UserRequest oauth2UserRequest, OAuth2UserInfo oauth2UserInfo) {
		ProviderEntity provider = new ProviderEntity();
		Set<RoleEntity> roleEntities = new HashSet<>();
		Optional<RoleEntity> roleEntity = roleRepository.getRoleEntityByName("ROLE_DOCTOR");
		roleEntity.ifPresent(roleEntities::add);
		provider.setName(oauth2UserRequest.getClientRegistration().getRegistrationId());
		provider.setUserIdByProvider(oauth2UserInfo.getId());
		UserEntity user = new UserEntity();
		StringToNameParser.setUserNameFromRequest(oauth2UserInfo, user);
		provider.setEmail(oauth2UserInfo.getEmail());
		user.setEmail(oauth2UserInfo.getEmail());
		user.setAvatar(oauth2UserInfo.getImageUrl());
		user.setStatus(UserStatus.NEW);
		user.setRoles(roleEntities);
		user.setEnabled(true);
		UserEntity savedUser = userRepository.save(user);
		provider.setUser(savedUser);
		userProviderRepository.save(provider);
		return savedUser;
	}

	private UserEntity updateExistingUser(UserEntity existingUser, OAuth2UserInfo oauth2UserInfo) {
		existingUser.setFirstName(oauth2UserInfo.getName().split(" ")[0]);
		existingUser.setLastName(oauth2UserInfo.getName().split(" ")[1]);
		existingUser.setAvatar(oauth2UserInfo.getImageUrl());
		return userRepository.save(existingUser);
	}

}
