package com.softserveinc.dokazovi.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorResponseDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer cityId;
    private Integer regionId;
    private String avatar;
    private String mainWorkingPlace;
    private String bio;
    private Set<String> socialNetworks;

}
