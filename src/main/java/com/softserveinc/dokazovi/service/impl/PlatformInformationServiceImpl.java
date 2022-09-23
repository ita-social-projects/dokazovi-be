package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.entity.PlatformInformationEntity;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.PlatformInformationMapper;
import com.softserveinc.dokazovi.repositories.PlatformInformationRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.PlatformInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformInformationServiceImpl implements PlatformInformationService {

    private final PlatformInformationRepository platformInformationRepository;
    private final PlatformInformationMapper platformInformationMapper;

    @Override
    public PlatformInformationDTO getInfoById(Integer infoId) {
        return platformInformationMapper
                .toPlatformInformationDTO(platformInformationRepository.findById(infoId).orElse(null));
    }

    @Override
    public PlatformInformationDTO saveInfo(UserPrincipal userPrincipal, PlatformInformationDTO infoDTO) {
        PlatformInformationEntity upToDateInfoEntity = getUpToDatePlatformInfoEntityFromPlatformInfoDTO(infoDTO);
        if (verifyAuthority(userPrincipal, "SAVE_PLATFORM_INFORMATION")) {
            return platformInformationMapper
                    .toPlatformInformationDTO(platformInformationRepository.save(upToDateInfoEntity));
        } else {
            throw new ForbiddenPermissionsException();
        }
    }

    @Override
    public PlatformInformationDTO updateInfo(UserPrincipal userPrincipal, PlatformInformationDTO infoDTO) {
        PlatformInformationEntity upToDateInfoEntity = getUpToDatePlatformInfoEntityFromPlatformInfoDTO(infoDTO);
        if (verifyAuthority(userPrincipal, "UPDATE_PLATFORM_INFORMATION")) {
            return platformInformationMapper
                    .toPlatformInformationDTO(platformInformationRepository.save(upToDateInfoEntity));
        } else {
            throw new ForbiddenPermissionsException();
        }
    }

    /**
     * Verifies whether a user possesses the required authority
     *
     * @param userPrincipal the authorized user's data
     * @param authority     a String value from the RolePermission enum
     * @return true in case the user has appropriate authority and false if not
     */
    private boolean verifyAuthority(UserPrincipal userPrincipal, String authority) {
        return userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals(authority));
    }

    /**
     * Updates PlatformInformationEntity for further saving it in the DB. In case the received information (DTO) is new
     * - creates a new PlatformInformationEntity and fills in it with the data
     *
     * @param infoDTO a DTO that holds some new data
     * @return the updated instance of PlatformInformationEntity
     */
    private PlatformInformationEntity getUpToDatePlatformInfoEntityFromPlatformInfoDTO(PlatformInformationDTO infoDTO) {
        Integer infoID = infoDTO.getId();
        PlatformInformationEntity upToDateEntity;
        if (infoID == null) {
            upToDateEntity = platformInformationMapper.toPlatformInformationEntity(infoDTO);
        } else {
            PlatformInformationEntity foundInfo = platformInformationRepository.findById(infoID)
                    .orElseThrow(EntityNotFoundException::new);
            upToDateEntity = platformInformationMapper.updatePlatformInformationEntity(infoDTO, foundInfo);
        }
        return upToDateEntity;
    }
}
