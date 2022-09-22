package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.city.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> findAllCities();

    List<CityDTO> findAllCitiesByRegion(Integer regionId);
}