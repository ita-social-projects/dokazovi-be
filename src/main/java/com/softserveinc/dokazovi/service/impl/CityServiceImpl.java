package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.city.CityDTO;
import com.softserveinc.dokazovi.mapper.CityMapper;
import com.softserveinc.dokazovi.repositories.CityRepository;
import com.softserveinc.dokazovi.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDTO> findAllCities() {
        return cityRepository.findAll().stream()
                .map(cityMapper::toCityDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDTO> findAllCitiesByRegion(Integer regionId) {
        return cityRepository.findAllByRegionId(regionId).stream()
                .map(cityMapper::toCityDTO)
                .collect(Collectors.toList());
    }
}