package com.softserveinc.dokazovi.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

	private Integer id;

	private String email;

	private String firstName;

	private String lastName;

	private String placeOfWork;

	private String avatar;

	private String bio;

	private String socialNetwork;

	private Integer city;
}
