package com.softserveinc.dokazovi.dto.author;

import com.softserveinc.dokazovi.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private UserEntity profile;
    private Integer cityId;
    private Integer regionId;
    private String avatar;
    private String mainWorkingPlace;
    private String bio;
    private Set<String> socialNetworks;
}
