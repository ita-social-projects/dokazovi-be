package com.softserveinc.dokazovi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEnabledDTO {

    private Integer id;

    private boolean enabled;
}
