package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.DoctorPreviewDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface UserService {
	Page<DoctorPreviewDTO> getExpertsPreview(Pageable pageable);
}
