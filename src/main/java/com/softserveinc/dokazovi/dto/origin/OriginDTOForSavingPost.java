package com.softserveinc.dokazovi.dto.origin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The OriginDTOForSavingPost is responsible for passing origin data for saving post
 * from server to the client.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OriginDTOForSavingPost {
    @NotNull(message = "Origin id are required")
    private Integer id;
}
