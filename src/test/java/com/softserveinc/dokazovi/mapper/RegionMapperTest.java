package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.entity.RegionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegionMapperTest {

    private final RegionMapper mapper = Mappers.getMapper(RegionMapper.class);
    private RegionEntity regionEntity;

    @BeforeEach
    void init() {
        regionEntity = RegionEntity.builder()
                .id(1)
                .name("Region 1")
                .build();
    }

    @Test
    void toRegionDTO_whenMaps_thenCorrect() {
        RegionDTO regionDTO = mapper.toRegionDTO(regionEntity);

        assertEquals(regionDTO.getId(), regionEntity.getId());
        assertEquals(regionDTO.getName(), regionEntity.getName());
    }
}
