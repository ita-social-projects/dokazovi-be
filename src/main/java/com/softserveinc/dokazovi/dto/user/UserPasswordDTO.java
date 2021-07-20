package com.softserveinc.dokazovi.dto.user;

import com.softserveinc.dokazovi.annotations.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@PasswordMatches
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {

	private String token;
	private String newPassword;
	private String matchPassword;

}
