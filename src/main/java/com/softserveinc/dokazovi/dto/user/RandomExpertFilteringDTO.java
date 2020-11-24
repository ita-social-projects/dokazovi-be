package com.softserveinc.dokazovi.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class RandomExpertFilteringDTO {
	Set<Integer> directionsIds;
}
