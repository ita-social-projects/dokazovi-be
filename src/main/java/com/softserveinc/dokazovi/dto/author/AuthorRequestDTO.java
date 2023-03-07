package com.softserveinc.dokazovi.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDTO {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private Integer cityId;
    @NotBlank
    private String avatar;
    @NotBlank
    private String mainWorkingPlace;
    @NotBlank
    private String bio;
    private Set<String> socialNetworks;
}