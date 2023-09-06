package com.softserveinc.dokazovi.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTOForUpdatingPost {

    @NotNull(message = "Author id is required")
    private Integer id;


}
