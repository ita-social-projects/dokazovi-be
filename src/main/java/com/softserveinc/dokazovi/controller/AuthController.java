package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.AuthProvider;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.payload.ApiResponse;
import com.softserveinc.dokazovi.payload.AuthResponse;
import com.softserveinc.dokazovi.payload.LoginRequest;
import com.softserveinc.dokazovi.payload.SignUpRequest;
import com.softserveinc.dokazovi.security.TokenProvider;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.util.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_SIGNUP;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_VERIFICATION;

@RestController
@RequestMapping(AUTH)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MailSenderUtil mailSenderUtil;

    @Autowired
    private UserService userService;

    @PostMapping(AUTH_LOGIN)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
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
            return ResponseEntity.ok(new AuthResponse(token));
        }
    }

    @PostMapping(AUTH_SIGNUP)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        UserEntity user = new UserEntity();
        user.setFirstName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setProvider(AuthProvider.local);

        UserEntity result = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        mailSenderUtil.sendMessage(user);
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully! Please confirm your email!"));
    }

    @GetMapping(AUTH_VERIFICATION)
    public ResponseEntity<?> registrationComplete(
            @RequestParam(value = "token") String token) {
        userService.setEnableTrue(userService.getVerificationToken(token).getUser());
        return ResponseEntity.ok().body(new ApiResponse(true, "Email confirmed! redirect to login page!"));
    }
}
