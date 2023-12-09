package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.payload.AuthResponse;
import com.softserveinc.dokazovi.dto.payload.LoginRequest;
import com.softserveinc.dokazovi.dto.payload.RefreshToken;
import com.softserveinc.dokazovi.dto.payload.RefreshTokenRequest;
import com.softserveinc.dokazovi.entity.LogForLoginEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.exception.TokenRefreshException;
import com.softserveinc.dokazovi.security.RefreshTokenService;
import com.softserveinc.dokazovi.security.TokenProvider;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.LogForLoginService;
import com.softserveinc.dokazovi.service.ProviderService;
import com.softserveinc.dokazovi.service.UserIpWhitelistService;
import com.softserveinc.dokazovi.service.UserLoginIpService;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.service.impl.MailSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;
import static com.softserveinc.dokazovi.controller.EndPoints.REFRESH_TOKEN;

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
    private final RefreshTokenService refreshTokenService;
    private final LogForLoginService logForLoginService;
    private final UserLoginIpService userLoginIpService;
    private final UserIpWhitelistService userIpWhitelistService;

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
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
            HttpServletResponse response, HttpServletRequest request) {
        String status = null;
        UserEntity userEntity = userService.findByEmail(loginRequest.getEmail());
        try {
            if (userEntity == null) {
                status = "User doesn't exist";
                throw new BadCredentialsException("Bad credentials");
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String userIp = request.getRemoteAddr();
            userLoginIpService.saveUserIP(userEntity.getId(), userIp);

            if (userEntity.getWhitelist() && !userIpWhitelistService.isIpWhitelisted(userEntity.getId(), userIp)) {
                status = "The IP wasn't found";
                throw new BadCredentialsException("You are not allowed to log in from this device");
            }

            if (!userEntity.getEnabled()) {
                throw new BadRequestException("Please confirm your email!");
            } else if (userEntity.getStatus() != UserStatus.ACTIVE) {
                if (userEntity.getStatus() == UserStatus.DELETED) {
                    throw new BadRequestException("User is blocked!");
                } else {
                    throw new BadRequestException("Activate your account!");
                }
            } else {
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(userEntity.getId());
                ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
                        .httpOnly(true)
                        .secure(true)
                        .domain("dokazovi-fe-release.herokuapp.com")
                        .sameSite("none")
                        .build();
                response.setHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
                String token = tokenProvider.createToken(authentication);
                AuthResponse authResponse = new AuthResponse(token, refreshToken.getToken());
                authResponse.setAccessToken(token);
                status = "Successful";
                return ResponseEntity.ok(authResponse);
            }
        } catch (AuthenticationException e) {
           if (status == null) {
               status = "Failed";
           }
           throw new BadCredentialsException(e.getMessage());
        } finally {
            logForLoginService.save(LogForLoginEntity.builder()
                    .login(loginRequest.getEmail())
                    .dateOfLogin(Timestamp.valueOf(LocalDateTime.now()))
                    .ip(request.getRemoteAddr())
                    .loginStatus(status)
                    .build());
        }
    }

    @PostMapping(REFRESH_TOKEN)
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = tokenProvider.createToken(UserPrincipal.create(user));
                    return ResponseEntity.ok(new AuthResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
