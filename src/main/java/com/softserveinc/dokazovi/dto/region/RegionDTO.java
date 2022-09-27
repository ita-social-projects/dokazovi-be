package com.softserveinc.dokazovi.dto.region;

import lombok.Builder;
import lombok.Data;

/**
 * The Region DTO is responsible for passing region data from the server to the client.
 */

@Data
@Builder
public class RegionDTO {

    private Integer id;
    private String name;
    private Boolean usersPresent;
}
