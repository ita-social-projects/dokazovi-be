package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.entity.PlatformInformationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlatformInformationMapperTest {

    private final PlatformInformationMapper mapper = Mappers.getMapper(PlatformInformationMapper.class);

    private PlatformInformationEntity infoEntity;
    private PlatformInformationDTO infoDTO;

    @BeforeEach
    void init() {
        infoEntity = PlatformInformationEntity.builder()
                .id(1)
                .title("Entity Info title")
                .text("Entity Info text")
                .build();

        infoDTO = PlatformInformationDTO.builder()
                .id(1)
                .title("DTO info title")
                .text("DTO info text")
                .build();
    }

    @Test
    void toPlatformInformationDTO() {
        PlatformInformationDTO infoDTO = mapper.toPlatformInformationDTO(infoEntity);
        assertEquals(infoDTO.getId(), infoEntity.getId());
        assertEquals(infoDTO.getTitle(), infoEntity.getTitle());
        assertEquals(infoDTO.getText(), infoEntity.getText());
    }

    @Test
    void toPlatformInformationEntity() {
        PlatformInformationEntity infoEntity = mapper.toPlatformInformationEntity(infoDTO);
        assertEquals(infoEntity.getId(), infoDTO.getId());
        assertEquals(infoEntity.getTitle(), infoDTO.getTitle());
        assertEquals(infoEntity.getText(), infoDTO.getText());
    }

    @Test
    void updatePlatformInformationEntity() {
        PlatformInformationEntity infoEntity = new PlatformInformationEntity();
        infoEntity.setText("Some Old Text");
        mapper.updatePlatformInformationEntity(infoDTO, infoEntity);
        assertEquals(infoEntity.getId(), infoDTO.getId());
        assertEquals(infoEntity.getTitle(), infoDTO.getTitle());
        assertEquals(infoEntity.getText(), infoDTO.getText());
    }
}
