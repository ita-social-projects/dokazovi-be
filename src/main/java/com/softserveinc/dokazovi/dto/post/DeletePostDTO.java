package com.softserveinc.dokazovi.dto.post;

import com.softserveinc.dokazovi.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeletePostDTO {

    private String title;
    private UserPrincipal userPrincipal;
    private Integer postId;
}
