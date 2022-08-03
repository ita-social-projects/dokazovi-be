package com.softserveinc.dokazovi.dto.author;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorForAdminDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private String cityName;

	private String regionName;

	private Timestamp creationDate;

	private Timestamp updateTime;
}


