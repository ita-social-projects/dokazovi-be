package com.softserveinc.dokazovi.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostUserInstitutionCityDTO {
    private Integer id;
    private String name;
}
