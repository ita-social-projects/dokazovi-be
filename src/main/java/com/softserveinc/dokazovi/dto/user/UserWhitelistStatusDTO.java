package com.softserveinc.dokazovi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWhitelistStatusDTO {

    private Integer id;

    @NotBlank
    private boolean whitelistStatus;
}
