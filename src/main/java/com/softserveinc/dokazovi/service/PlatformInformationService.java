package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;

public interface PlatformInformationService {

    PlatformInformationDTO getInfoById(Integer infoId);

    PlatformInformationDTO saveInfo(UserPrincipal userPrincipal, PlatformInformationDTO infoDTO);

    PlatformInformationDTO updateInfo(UserPrincipal userPrincipal, PlatformInformationDTO infoDTO);
}
