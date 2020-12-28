package com.softserveinc.dokazovi.dto.source;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceDTO {
	private Integer id;
	private String value;
}
