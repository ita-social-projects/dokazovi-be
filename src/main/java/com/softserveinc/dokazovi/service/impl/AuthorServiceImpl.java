package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.author.AuthorRequestDTO;
import com.softserveinc.dokazovi.dto.author.AuthorResponseDTO;
import com.softserveinc.dokazovi.entity.AuthorEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.AuthorMapper;
import com.softserveinc.dokazovi.repositories.AuthorRepository;
import com.softserveinc.dokazovi.repositories.CityRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorEntity findAuthorById(Integer authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Unable to find author with id: " + authorId));
    }

    @Override
    public AuthorEntity save(AuthorRequestDTO authorRequestDTO, UserPrincipal userPrincipal) {
        if (!hasEnoughAuthorities(userPrincipal)) {
            throw new ForbiddenPermissionsException("Not enough authority");
        }
        UserEntity user = UserEntity.builder()
                .firstName(authorRequestDTO.getFirstName())
                .lastName(authorRequestDTO.getLastName())
                .avatar(authorRequestDTO.getAvatar())
                .enabled(false)
                .status(UserStatus.NEW)
                .publicEmail(authorRequestDTO.getPublicEmail())
                .socialNetworks(authorRequestDTO.getSocialNetworks())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        userRepository.save(user);
        AuthorEntity author = AuthorEntity.builder()
                .publishedPosts(0L)
                .promotionScale(1.0)
                .mainWorkingPlace(authorRequestDTO.getMainWorkingPlace())
                .city(cityRepository.findById(authorRequestDTO.getCityId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Unable to find city with id: " + authorRequestDTO.getCityId())))
                .profile(user)
                .bio(authorRequestDTO.getBio())
                .build();
        return authorRepository.save(author);
    }

    @Override
    public AuthorEntity update(Integer authorId, AuthorRequestDTO authorRequestDTO, UserPrincipal userPrincipal) {
        if (!hasEnoughAuthorities(userPrincipal)) {
            throw new ForbiddenPermissionsException("Not enough authority");
        }
        AuthorEntity oldAuthor = findAuthorById(authorId);
        UserEntity oldUser = userRepository.getOne(oldAuthor.getProfile().getId());
        UserEntity newUser = UserEntity.builder()
                .id(oldUser.getId())
                .firstName(authorRequestDTO.getFirstName())
                .lastName(authorRequestDTO.getLastName())
                .avatar(authorRequestDTO.getAvatar())
                .socialNetworks(authorRequestDTO.getSocialNetworks())
                .enabled(oldUser.getEnabled())
                .status(oldUser.getStatus())
                .email(oldUser.getEmail())
                .password(oldUser.getPassword())
                .phone(oldUser.getPhone())
                .publicEmail(authorRequestDTO.getPublicEmail())
                .role(oldUser.getRole())
                .createdAt(oldUser.getCreatedAt())
                .editedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        userRepository.save(newUser);
        AuthorEntity newAuthor = AuthorEntity.builder()
                .id(authorId)
                .publishedPosts(oldAuthor.getPublishedPosts())
                .promotionScale(oldAuthor.getPromotionScale())
                .mainWorkingPlace(authorRequestDTO.getMainWorkingPlace())
                .city(cityRepository.findById(authorRequestDTO.getCityId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Unable to find city with id: " + authorRequestDTO.getCityId())))
                .profile(newUser)
                .bio(authorRequestDTO.getBio())
                .promotionScale(oldAuthor.getPromotionScale())
                .qualification(oldAuthor.getQualification())
                .institutions(oldAuthor.getInstitutions())
                .build();
        authorRepository.save(newAuthor);
        return newAuthor;
    }

    @Override
    public Integer delete(Integer authorId, UserPrincipal userPrincipal) {
        if (!hasEnoughAuthorities(userPrincipal)) {
            throw new ForbiddenPermissionsException("Not enough authority");
        }
        AuthorEntity author = findAuthorById(authorId);
        authorRepository.delete(author);
        return authorId;
    }

    @Override
    @Transactional
    public Page<AuthorResponseDTO> findAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(authorMapper::toAuthorResponseDTO);
    }

    private boolean hasEnoughAuthorities(UserPrincipal userPrincipal) {
        return userPrincipal.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(RolePermission.EDIT_AUTHOR.getAuthority()));
    }
}