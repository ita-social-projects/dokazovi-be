package com.softserveinc.dokazovi.dto.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLogDTO {

    private Integer id;
    private Integer idOfChangedPost;
    private String title;
    private Timestamp dateOfChange;
    private String changes;
    private String nameOfChanger;
}