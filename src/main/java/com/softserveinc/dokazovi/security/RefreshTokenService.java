package com.softserveinc.dokazovi.security;

import com.softserveinc.dokazovi.config.AppProperties;
import com.softserveinc.dokazovi.dto.payload.RefreshToken;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.exception.TokenRefreshException;
import com.softserveinc.dokazovi.repositories.RefreshTokenRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

	private AppProperties appProperties;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	@Autowired
	private UserRepository userRepository;

	public RefreshTokenService(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	public Optional<RefreshToken> findByToken(String token){
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Integer userId){
		RefreshToken refreshToken = new RefreshToken();

		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if(userEntity.isPresent()){
			refreshToken.setUser(userEntity.get());
			refreshToken.setExpiryDate(Instant.now().plusMillis(appProperties.getAuth().getRefreshTokenExpirationMsec()));
			refreshToken.setToken(UUID.randomUUID().toString());
		}

		refreshToken = refreshTokenRepository.save(refreshToken);

		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token){
		if(token.getExpiryDate().compareTo(Instant.now()) < 0){
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
		}
		return token;
	}


}
