package com.softserveinc.dokazovi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import lombok.ToString;

import java.util.Optional;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostFakeViewEntityDTO {
	private Integer views;
}
