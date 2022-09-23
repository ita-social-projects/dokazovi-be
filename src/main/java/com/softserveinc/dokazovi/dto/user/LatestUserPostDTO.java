package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

/**
 * The LatestUserPostDTO is responsible for passing latest user post data from server to the client.
 */

@Data
@Builder
public class LatestUserPostDTO {

    private Integer id;

    private String title;

}
