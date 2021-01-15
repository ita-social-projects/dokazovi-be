package com.softserveinc.dokazovi.entity.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 3, max = 20, message = "Password length must be from 4 to 16 symbols")
	private String password;
}
