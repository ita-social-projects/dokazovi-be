package com.softserveinc.dokazovi.dto.tag;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDTO {
	private int id;
	private String tag;
}
