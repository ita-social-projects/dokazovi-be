package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPasswordDTO {

	private String token;
	private String oldPassword;
	private String newPassword;

}
