package com.softserveinc.dokazovi.dto.post;

import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostStatusDTO {

    private PostStatus status;
}
