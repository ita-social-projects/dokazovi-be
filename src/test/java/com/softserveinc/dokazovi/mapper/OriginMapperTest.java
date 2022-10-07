package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.entity.OriginEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OriginMapperTest {

    private final OriginMapper mapper = Mappers.getMapper(OriginMapper.class);
    private OriginEntity originEntity;

    @BeforeEach
    void init() {
        originEntity = OriginEntity.builder()
                .id(1)
                .name("Some name")
                .parameters(null)
                .build();
    }

    @Test
    void toOriginDTO_whenMaps_thenCorrect() {
        OriginDTO originDTO = mapper.toOriginDTO(originEntity);

        assertEquals(originDTO.getId(), originEntity.getId());
        assertEquals(originDTO.getName(), originEntity.getName());
        assertEquals(originDTO.getParameters(), originEntity.getParameters());
    }
}
