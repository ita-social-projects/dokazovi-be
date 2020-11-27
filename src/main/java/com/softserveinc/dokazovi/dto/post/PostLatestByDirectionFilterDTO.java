package com.softserveinc.dokazovi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLatestByDirectionFilterDTO {
	@NotNull
	private Integer direction;
	private Integer type;
	private Set<Integer> tags;
}
