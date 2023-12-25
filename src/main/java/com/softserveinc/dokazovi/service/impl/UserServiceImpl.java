package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.dto.user.UserPasswordDTO;
import com.softserveinc.dokazovi.dto.user.UserPublicAndPrivateEmailDTO;
import com.softserveinc.dokazovi.dto.user.UserStatusDTO;
import com.softserveinc.dokazovi.dto.user.UserWhitelistStatusDTO;
import com.softserveinc.dokazovi.entity.AuthorEntity;
import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.VerificationToken;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.BadRequestException;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.UserMapper;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.repositories.AuthorRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.CheckAuthorityService;
import com.softserveinc.dokazovi.service.MailSenderService;
import com.softserveinc.dokazovi.service.PasswordResetTokenService;
import com.softserveinc.dokazovi.service.ProviderService;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * The UserServiceImpl is responsible for doing any required logic with the user data received by the User Controller.
 * It provides logic to operate on the data sent to and from the User repository.
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final VerificationTokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenService passwordResetTokenService;
    private final MailSenderService mailSenderService;
    private final AuthorRepository authorRepository;
    private final CheckAuthorityService checkAuthorityService;
    private final ProviderService providerService;

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
        AuthorEntity author = authorRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Author not found"));
        return userMapper.toUserDTO(userRepository.findById(author.getProfile().getId()).orElseThrow(
                () -> new EntityNotFoundException("User not found")));

    }

    @Override
    public UserDTO findExpertByUserId(Integer userId) {
        return userMapper.toUserDTO(userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found")));
    }

    /**
     * Gets doctors by search criteria. For example, if directions, regions and username fields are empty, the
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
            return userRepository.findAll(pageable).map(userMapper::toUserDTO);
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
     * Sets enabled status for user.
     *
     * @param authorId  received from User controller
     * @param isEnabled received from User controller
     */
    @Override
    public void setEnabled(Integer authorId, boolean isEnabled) {
        UserEntity userEntity = getById(authorId);
        if (userEntity == null) {
            throw new EntityNotFoundException("User not found");
        }
        userEntity.setEnabled(isEnabled);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getById(Integer authorId) {
        AuthorEntity author = authorRepository.findById(authorId).orElse(null);
        return userRepository.findById(author.getProfile().getId()).orElse(null);
    }

    @Override
    public UserEntity getByUserId(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserEntity update(UserEntity user) {
        if (user != null) {
            UserEntity oldUser = getByUserId(user.getId());
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
    public void sendActivationToken(Integer userId, String email, String origin) {
        UserEntity user = getById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        user.setEmail(email);
        user.setStatus(UserStatus.NEW);
        user.setEnabled(false);
        update(user);
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationTokenForUser(user, token);
        mailSenderService.sendEmailWithActivationToken(origin, token, user);
    }

    @Override
    public void activateUser(UserPasswordDTO userPasswordDTO) {
        VerificationToken token = tokenService.getByToken(userPasswordDTO.getToken());
        if (!tokenService.validateVerificationToken(userPasswordDTO.getToken())) {
            throw new BadRequestException("Token is not valid");
        }
        UserEntity user = token.getUser();
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        user.setEnabled(true);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));
        update(user);
        providerService.createLocalProviderEntityForUser(user, user.getEmail());
        tokenService.delete(token);
    }

    @Override
    public UserPublicAndPrivateEmailDTO getAllPublicAndPrivateEmails() {
        List<UserEntity> users = userRepository.findAll();
        return UserPublicAndPrivateEmailDTO.builder()
                .publicEmail(Arrays.stream(users.toArray())
                        .map(user -> ((UserEntity) user).getPublicEmail())
                        .collect(Collectors.toList()))
                .privateEmail(Arrays.stream(users.toArray())
                        .map(user -> ((UserEntity) user).getEmail())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void changeStatus(UserStatusDTO userStatusDTO) {
        AuthorEntity author = authorRepository.findById(userStatusDTO.getId()).orElse(null);
        if (author == null) {
            throw new EntityNotFoundException("Author not found");
        }
        UserEntity user = userRepository.findById(userStatusDTO.getId()).orElse(null);
        if (user != null) {
            switch (userStatusDTO.getStatus()) {
                case "ACTIVE":
                    user.setStatus(UserStatus.ACTIVE);
                    break;
                case "DELETED":
                    user.setStatus(UserStatus.DELETED);
                    break;
                case "NEW":
                    user.setStatus(UserStatus.NEW);
                    break;
                default:
                    throw new BadRequestException("Wrong status");
            }
        }
    }

    @Override
    public void changeWhitelistStatus(UserPrincipal userPrincipal, UserWhitelistStatusDTO userWhitelistStatusDTO) {
        if (checkAuthorityService.checkAuthority(userPrincipal, "EDIT_AUTHOR")) {
            UserEntity user = userRepository.findById(userWhitelistStatusDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "User with id " + userWhitelistStatusDTO.getId() + " not found"));

            user.setWhitelist(userWhitelistStatusDTO.isWhitelistStatus());
            userRepository.save(user);
        } else {
            throw new ForbiddenPermissionsException();
        }
    }
}