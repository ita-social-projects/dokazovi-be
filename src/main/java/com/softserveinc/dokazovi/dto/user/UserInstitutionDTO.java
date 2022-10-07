package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

/**
 * The UserInstitutionDTO is responsible for passing latest user institution city data from server to the client.
 */


@Data
@Builder
public class UserInstitutionDTO {

    private Integer id;

    private UserInstitutionCityDTO city;

    private String name;

}
