package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.author.AuthorDTO;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserPromotionLevel;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.repositories.InstitutionRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public AuthorDTO save(AuthorDTO authorDTO, UserPrincipal userPrincipal) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(authorDTO.getEmail());
        if (optionalUser.isPresent()) {
            DoctorEntity doctorEntity = doctorRepository.getByProfileId(optionalUser.get().getId());
            return update(authorDTO, doctorEntity.getId(), userPrincipal);
        }
        AuthorDTO dto;
        if (hasEnoughAuthorities(userPrincipal)) {
            UserEntity userEntity = toUserEntity(authorDTO);
            userEntity = userRepository.save(userEntity);
            DoctorEntity doctorEntity = createDoctorEntity(authorDTO, userEntity);
            DoctorEntity doctor = doctorRepository.save(doctorEntity);
            dto = toAuthorDTO(doctor.getProfile(), doctor);
            return dto;
        } else {
            throw new ForbiddenPermissionsException();
        }
    }

    private AuthorDTO toAuthorDTO(UserEntity savedUser, DoctorEntity savedDoctor) {
        return AuthorDTO.builder()
                .avatar(savedUser.getAvatar())
                .password("setted")
                .bio(savedDoctor.getBio())
                .firstName(savedUser.getFirstName())
                .qualification(savedDoctor.getQualification())
                .lastName(savedUser.getLastName())
                .mainInstitutionId(savedDoctor.getMainInstitution().getId())
                .email(savedUser.getEmail())
                .socialNetwork(savedUser.getSocialNetworks())
                .build();
    }

    private DoctorEntity createDoctorEntity(AuthorDTO authorDTO, UserEntity userEntity) {
        return DoctorEntity.builder()
                .bio(authorDTO.getBio())
                .qualification(authorDTO.getQualification())
                .mainInstitution(institutionRepository.getOne(authorDTO.getMainInstitutionId()))
                .publishedPosts(0L)
                .promotionScale(1.0)
                .profile(userEntity)
                .promotionLevel(UserPromotionLevel.BASIC)
                .build();
    }

    private UserEntity toUserEntity(AuthorDTO authorDTO) {
        return UserEntity.builder()
                .id(null)
                .firstName(authorDTO.getFirstName())
                .lastName(authorDTO.getLastName())
                .avatar(authorDTO.getAvatar())
                .email(authorDTO.getEmail())
                .socialNetworks(authorDTO.getSocialNetwork())
                .status(UserStatus.ACTIVE)
                .enabled(true)
                .role(RoleEntity.builder()
                        .id(3)
                        .name("Doctor")
                        .permissions(Set.of(RolePermission.SAVE_OWN_PUBLICATION))
                        .build())
                .password(passwordEncoder.encode(authorDTO.getPassword()))
                .build();
    }

    @Override
    public AuthorDTO update(AuthorDTO authorDTO, Integer doctorId, UserPrincipal userPrincipal) {
        if (hasEnoughAuthorities(userPrincipal)) {
            Optional<DoctorEntity> doctor = doctorRepository.findById(doctorId);
            if (doctor.isPresent()) {
                DoctorEntity doctorToSave = doctor.get();
                UserEntity userToSave = doctorToSave.getProfile();
                if (authorDTO.getEmail() != null) {
                    userToSave.setEmail(authorDTO.getEmail());
                }
                if (authorDTO.getFirstName() != null) {
                    userToSave.setFirstName(authorDTO.getFirstName());
                }
                if (authorDTO.getLastName() != null) {
                    userToSave.setLastName(authorDTO.getLastName());
                }
                if (authorDTO.getQualification() != null) {
                    doctorToSave.setQualification(authorDTO.getQualification());
                }
                if (authorDTO.getMainInstitutionId() != null) {
                    doctorToSave.setMainInstitution(institutionRepository.getOne(authorDTO.getMainInstitutionId()));
                }
                if (authorDTO.getAvatar() != null) {
                    userToSave.setAvatar(authorDTO.getAvatar());
                }
                if (authorDTO.getBio() != null) {
                    doctorToSave.setBio(authorDTO.getBio());
                }
                if (authorDTO.getSocialNetwork() != null) {
                    userToSave.setSocialNetworks(authorDTO.getSocialNetwork());
                }
                userRepository.save(userToSave);
                doctorRepository.save(doctorToSave);
                return toAuthorDTO(userToSave, doctorToSave);
            } else {
                throw new NoSuchElementException("Doctor with id " + doctorId + " not exists");
            }
        } else {
            throw new ForbiddenPermissionsException();
        }
    }

    @Override
    public Boolean delete(Integer authorId, UserPrincipal userPrincipal) {
        if (hasEnoughAuthorities(userPrincipal)) {
            Integer userId = doctorRepository.getOne(authorId).getProfile().getId();
            doctorRepository.deleteById(authorId);
            userRepository.deleteById(userId);
            return true;
        }
        throw new ForbiddenPermissionsException();
    }

    private boolean hasEnoughAuthorities(UserPrincipal userPrincipal) {
        return userPrincipal.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals(RolePermission.EDIT_AUTHOR.getAuthority()));
    }
}