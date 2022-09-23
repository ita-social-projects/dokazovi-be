package com.softserveinc.dokazovi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostTypeIdOnlyDTO {

    @NotNull(message = "PostType id are required")
    private Integer id;

}
