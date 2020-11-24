package com.softserveinc.dokazovi.payload;

import lombok.Data;

import java.util.Set;

@Data
public class RandomExpertRequestBody {
	Set<Integer> directionsIds;
}
