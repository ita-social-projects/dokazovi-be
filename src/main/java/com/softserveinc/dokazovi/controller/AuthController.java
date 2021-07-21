package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.payload.AuthResponse;
import com.softserveinc.dokazovi.dto.payload.LoginRequest;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.security.TokenProvider;
import com.softserveinc.dokazovi.service.ProviderService;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.service.impl.MailSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;

/**
 * The Auth controller responsible for handling requests for authentication.
 */
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;
	private final MailSenderServiceImpl mailSenderServiceImpl;
	private final UserService userService;
	private final ProviderService providerService;

	/**
	 * Authenticates user using email and password.
	 *
	 * <p>Creates new authentication token for user. Checks if user has confirmed email (enabled == true),
	 * if not - throws BadRequestException to confirm it.</p>
	 *
	 * @param loginRequest data class that stores user email and password
	 * @return authorizes user and sets access token
	 */
	@PostMapping(AUTH_LOGIN)
	public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.createToken(authentication);
		UserEntity userEntity = userService.findByEmail(loginRequest.getEmail());
		if (!userEntity.getEnabled()) {
			throw new BadRequestException("Please confirm your email!");
		} else {
			AuthResponse authResponse = new AuthResponse(token);
			authResponse.setAccessToken(token);
			return ResponseEntity.ok(authResponse);
		}
	}
}
