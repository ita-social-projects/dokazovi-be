package com.softserveinc.dokazovi.dto.origin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The OriginDTO is responsible for passing origin data from server to the client.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OriginDTO {

    @NotNull(message = "Origin id are required")
    private Integer id;

    @NotBlank(message = "Origin name cannot be empty")
    private String name;

    @NotBlank(message = "Origin parameters cannot be empty")
    private String parameters;
}
