package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.CityEntity;
import com.softserveinc.dokazovi.mapper.CityMapper;
import com.softserveinc.dokazovi.repositories.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private CityMapper cityMapper;
    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void findAllCities() {
        List<CityEntity> cities = List.of(new CityEntity(), new CityEntity());

        when(cityRepository.findAll()).thenReturn(cities);
        cityService.findAllCities();

        verify(cityMapper, times(cities.size())).toCityDTO(any(CityEntity.class));
    }

    @Test
    void findAllCitiesByRegion() {
        List<CityEntity> cities = List.of(new CityEntity(), new CityEntity());

        when(cityRepository.findAllByRegionId(any(Integer.class))).thenReturn(cities);
        cityService.findAllCitiesByRegion(any(Integer.class));

        verify(cityMapper, times(cities.size())).toCityDTO(any(CityEntity.class));
    }
}