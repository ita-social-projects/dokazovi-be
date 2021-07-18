package com.softserveinc.dokazovi.dto.user;

import com.softserveinc.dokazovi.annotations.PasswordMatches;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@PasswordMatches
public class UserPasswordDTO {

	private String token;
	private String oldPassword;
	private String newPassword;
	private String matchPassword;

}
