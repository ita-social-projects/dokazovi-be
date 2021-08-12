package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSaveDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSearchResultDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ForeignExpertService {

	ForeignExpertDTO save(ForeignExpertSaveDTO saveDTO, UserPrincipal userPrincipal);

	Page<ForeignExpertSearchResultDTO> search(String query, Pageable pageable);
}
