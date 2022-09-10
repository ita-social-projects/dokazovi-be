package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.entity.RegionEntity;
import com.softserveinc.dokazovi.mapper.RegionMapper;
import com.softserveinc.dokazovi.repositories.RegionRepository;
import com.softserveinc.dokazovi.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The RegionServiceImpl is responsible for doing any required logic
 *  with the user data received by the Region Controller.
 * It provides logic to operate on the data sent to and from the Region repository.
 */

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

	private final RegionRepository regionRepository;
	private final RegionMapper regionMapper;

	/**
	 * Gets all regions.
	 *
	 * @return all found regions
	 */
	@Override
	public List<RegionDTO> findAllRegions() {
		return regionRepository.findAll().stream()
				.map(regionMapper::toRegionDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Updates the region status depending on the availability of doctors in it.
	 * Runs every four hours
	 */
	@Override
	@Transactional
	@Scheduled(cron = "0 0 */4 * * *")
	public void updateRegionsStatus() {
		regionRepository.updateRegionsStatus();
	}

    @Override
    public RegionDTO findRegionByCity(Integer cityId) {
		RegionEntity regionEntity = regionRepository.findByCitiesId(cityId);
		return regionMapper.toRegionDTO(regionEntity);
    }
}
