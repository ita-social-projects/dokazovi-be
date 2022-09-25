package com.softserveinc.dokazovi.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private Integer authorId;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String qualification;

    @NotBlank
    private Integer mainInstitutionId;

    @NotBlank
    private String avatar;

    @NotBlank
    private String bio;

    @NotBlank
    private Set<String> socialNetwork;
}