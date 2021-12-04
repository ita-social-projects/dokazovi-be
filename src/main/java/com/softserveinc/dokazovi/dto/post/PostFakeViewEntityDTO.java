package com.softserveinc.dokazovi.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import lombok.ToString;

import java.util.Optional;



@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostFakeViewEntityDTO {
	private Integer views;

	public Integer getViews() {
		return Optional.of(views).orElse(0);
	}
	public void setViews(Integer views) {
		this.views = Optional.ofNullable(views).orElse(0);
	}
}
