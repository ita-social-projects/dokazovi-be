package com.softserveinc.dokazovi.dto.city;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDTO {

    Integer id;
    String name;
    Integer regionId;
}