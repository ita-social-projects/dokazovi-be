package com.softserveinc.dokazovi.dto.user;

import com.softserveinc.dokazovi.annotations.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@PasswordMatches
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {

	@NotBlank
	private String token;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String matchPassword;

}
