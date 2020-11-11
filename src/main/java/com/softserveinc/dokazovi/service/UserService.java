package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface UserService {
	Page<ExpertPreviewDTO> getExpertsPreview(Pageable pageable);
}
