package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.annotations.ApiPageable;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.user.UserEmailDTO;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.dto.user.UserEmailPasswordDTO;
import com.softserveinc.dokazovi.dto.user.UserEnabledDTO;
import com.softserveinc.dokazovi.dto.user.UserIdEmailDTO;
import com.softserveinc.dokazovi.dto.user.UserIpWhitelistDTO;
import com.softserveinc.dokazovi.dto.user.UserLoginIpResponseDTO;
import com.softserveinc.dokazovi.dto.user.UserPasswordDTO;
import com.softserveinc.dokazovi.dto.user.UserPublicAndPrivateEmailDTO;
import com.softserveinc.dokazovi.dto.user.UserStatusDTO;
import com.softserveinc.dokazovi.dto.user.UserWhitelistStatusDTO;
import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.DirectionService;
import com.softserveinc.dokazovi.service.PasswordResetTokenService;
import com.softserveinc.dokazovi.service.UserIpWhitelistService;
import com.softserveinc.dokazovi.service.UserLoginIpService;
import com.softserveinc.dokazovi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ACTIVATE_ACCOUNT;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ACTIVATE_USER_ACCOUNT;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ALL_EMAILS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ALL_EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_CHANGE_PASSWORD;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_CHANGE_STATUS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_CHECK_TOKEN;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_EXPERT_ALL_POST_DIRECTIONS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_GET_AUTHORITIES;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_GET_CURRENT_USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_GET_USER_BY_ID;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_IP_LIST;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RANDOM_EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RESET_PASSWORD;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_UPDATE_PASSWORD;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_WHITELIST;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_WHITELIST_STATUS;

/**
 * The User controller is responsible for handling requests for users.
 */

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DirectionService directionService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final UserIpWhitelistService userIpWhitelistService;
    private final UserLoginIpService userLoginIpService;

    /**
     * Gets preview of random experts, filtered by directions. Default 12 max per page.
     *
     * @param pageable   interface for pagination information
     * @param directions direction id
     * @return page with found posts and 'OK' httpStatus
     */
    @GetMapping(USER_RANDOM_EXPERTS)
    @ApiPageable
    @ApiOperation(value = "Get preview of random experts, filtered by directions. Default 12 max per page.")
    public ResponseEntity<Page<UserDTO>> getRandomExpertPreview(
            @PageableDefault(size = 12) Pageable pageable,
            @ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
            @RequestParam(required = false) Set<Integer> directions) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findRandomExpertPreview(directions, pageable));
    }

    /**
     * Gets all experts depending on the parameters coming through the request, ordered by relevance. Default 6 max per
     * page.
     *
     * @param pageable           interface for pagination information
     * @param userSearchCriteria binds request parameters to an object
     * @return page with found experts and 'OK' httpStatus
     */
    @GetMapping(USER_ALL_EXPERTS)
    @ApiPageable
    @ApiOperation(value = "Get experts ordered by name, then filtered by directions and/or regions."
            + " Default 6 per page.")
    public ResponseEntity<Page<UserDTO>> getAllExpertsByDirectionsAndByRegionsOrderedByRelevance(
            @PageableDefault(size = 6) Pageable pageable, UserSearchCriteria userSearchCriteria) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAllExperts(userSearchCriteria, pageable));
    }

    @GetMapping(USER_EXPERT_ALL_POST_DIRECTIONS)
    @ApiOperation(value = "Get list of all directions which is used in all posts of user")
    public ResponseEntity<List<DirectionDTO>> getAllDirectionsOfUserPosts(@PathVariable("expertId") Integer userId) {
        var body = directionService.findAllDirectionsOfPostsByUserId(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }

    /**
     * Gets the user by its id. Checks if the user exists. If no - returns HttpStatus 'NOT FOUND'.
     *
     * @param userId id of user that we want to get
     * @return found user and HttpStatus 'OK'
     */
    @GetMapping(USER_GET_USER_BY_ID)
    @ApiOperation(value = "Get expert by Id, as a path variable.")
    public ResponseEntity<UserDTO> getExpertById(@PathVariable("userId") Integer userId) {
        UserDTO userDTO = userService.findExpertById(userId);
        return ResponseEntity
                .status((userDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(userDTO);
    }

    /**
     * Gets current user. Checks if userPrincipal exists via findExpertById method. If no - returns HttpStatus 'NOT
     * FOUND'.
     *
     * @param userPrincipal id of user that we want to get
     * @return found user and HttpStatus 'OK'
     */
    @GetMapping(USER_GET_CURRENT_USER)
    @ApiOperation(value = "Get current user",
            authorizations = {@Authorization(value = "Authorization")})
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDTO userDTO = userService.findExpertByUserId(userPrincipal.getId());
        return ResponseEntity
                .status((userDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(userDTO);
    }

    /**
     * Post request with email for reset user password and send verification email Checks if user with email exists in
     * DB.
     *
     * @param email to find user that we want to reset password
     * @return HttpStatus 'OK'
     */
    @PostMapping(USER_RESET_PASSWORD)
    @ApiOperation(value = "Reset current password")
    public ResponseEntity<UserEmailDTO> resetPassword(
            @Valid @RequestBody UserEmailDTO email,
            @RequestHeader HttpHeaders headers) {
        UserEntity user = userService.findUserEntityByEmail(email.getEmail());
        if (user != null) {
            userService.sendPasswordResetToken(user, headers.getOrigin());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Get request with token for validate one Checks if token exists in DB and not expired.
     *
     * @param token token which we have to validate or not
     * @return HttpStatus 'OK' and token value if token is available and not expired
     * @return HttpStatus 'NOT_FOUND' if token isn`t available or expired
     */
    @GetMapping(USER_CHECK_TOKEN)
    @ApiOperation(value = "Validate token by availability in the DB and by expiration date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<String> checkToken(
            @RequestParam String token) {
        CacheControl cacheControl = CacheControl.noCache();
        if (passwordResetTokenService.validatePasswordResetToken(token)) {
            return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).cacheControl(cacheControl).build();
    }

    @PostMapping(USER_UPDATE_PASSWORD)
    @ApiOperation(value = "Update current password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<String> updatePassword(
            @RequestBody @Valid UserPasswordDTO passwordDTO) {
        PasswordResetTokenEntity tokenEntity = passwordResetTokenService.getByToken(passwordDTO.getToken());
        UserEntity userEntity = null;
        if (tokenEntity != null) {
            userEntity = tokenEntity.getUserEntity();
        }
        if (userEntity != null) {
            userService.updatePassword(userEntity, passwordDTO.getNewPassword(), tokenEntity);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(USER_CHANGE_PASSWORD)
    @ApiOperation(value = "Change current password")
    public ResponseEntity<String> changePassword(
            @RequestHeader HttpHeaders headers,
            @Valid @RequestBody UserEmailPasswordDTO userEmailPasswordDTO) {
        UserEntity user = userService.findUserEntityByEmail(userEmailPasswordDTO.getEmail());
        if (userService.isPasswordMatches(user, userEmailPasswordDTO.getPassword())) {
            userService.sendPasswordResetToken(user, headers.getOrigin());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(USER_ACTIVATE_ACCOUNT)
    @ApiOperation(value = "Send activation token to user")
    public ResponseEntity<String> activateUser(
            @RequestHeader HttpHeaders headers,
            @Valid @RequestBody UserIdEmailDTO userIdEmailDTO) {
        userService.sendActivationToken(userIdEmailDTO.getId(), userIdEmailDTO.getEmail(), headers.getOrigin());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(USER_ACTIVATE_USER_ACCOUNT)
    @ApiOperation(value = "Activate user account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<String> activateUserAccount(
            @RequestBody UserPasswordDTO passwordDTO) {
        userService.activateUser(passwordDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(USER_GET_USER_BY_ID)
    @ApiOperation(value = "Change enabled of user",
            authorizations = {@Authorization(value = "Authorization")})
    public ResponseEntity<UserEntity> changeEnabled(
            @RequestBody UserEnabledDTO userEnabledDTO) {
        userService.setEnabled(userEnabledDTO.getId(), userEnabledDTO.isEnabled());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(USER_ALL_EMAILS)
    @ApiOperation(value = "Get all public and private emails")
    public ResponseEntity<UserPublicAndPrivateEmailDTO> getAllPublicAndPrivateEmails() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllPublicAndPrivateEmails());
    }

    /**
     * Gets current user's authorities. Checks if userPrincipal not null. If no - returns HttpStatus 'NOT FOUND'.
     *
     * @param userPrincipal authorities of user that we want to get
     * @return found user's authorities and HttpStatus 'OK'
     */

    @GetMapping(USER_GET_AUTHORITIES)
    @ApiOperation(value = "Get user's authorities",
            authorizations = {@Authorization(value = "Authorization")})
    public ResponseEntity<Collection<? extends GrantedAuthority>> getAuthorities(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Collection<? extends GrantedAuthority> authorities = null;
        if (userPrincipal != null) {
            authorities = userPrincipal.getAuthorities();
        }
        return ResponseEntity.status(authorities != null
                ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(authorities);
    }

    @PutMapping(USER_CHANGE_STATUS)
    @ApiOperation(value = "Change user status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<UserStatusDTO> changeUserStatus(
            @RequestBody UserStatusDTO userStatusDTO) {
        userService.changeStatus(userStatusDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(USER_WHITELIST_STATUS)
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "Enables ot disables whitelist option for a specific user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<UserWhitelistStatusDTO> changeUserWhitelistStatus(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UserWhitelistStatusDTO userWhitelistStatusDTO) {
        userService.changeWhitelistStatus(userPrincipal, userWhitelistStatusDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(USER_WHITELIST)
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "Updates whitelist for a specific user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<UserIpWhitelistDTO> updateUserWhitelist(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UserIpWhitelistDTO userIpWhitelistDTO
    ) {
        userIpWhitelistService.updateUserIpWhitelist(userPrincipal, userIpWhitelistDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(USER_GET_USER_BY_ID + USER_IP_LIST)
    @PreAuthorize("hasAuthority('EDIT_AUTHOR')")
    @ApiOperation(value = "Gets all IPs that user used to log in")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
    })
    public ResponseEntity<UserLoginIpResponseDTO> getUserLoginIps(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Integer userId
    ) {
        List<String> ips = userLoginIpService.getAllUserIps(userPrincipal, userId);
        UserLoginIpResponseDTO responseDTO = new UserLoginIpResponseDTO(ips);
        return ResponseEntity.ok(responseDTO);
    }
}