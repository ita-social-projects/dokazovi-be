package com.softserveinc.dokazovi.dto.foreignexpert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeignExpertDTO {
	private Integer id;
	private String fullName;
	private String bio;
	private String avatar;
}
