package com.softserveinc.dokazovi.dto.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class RefreshTokenRequest {
	@NotBlank
	private String refreshToken;

}
