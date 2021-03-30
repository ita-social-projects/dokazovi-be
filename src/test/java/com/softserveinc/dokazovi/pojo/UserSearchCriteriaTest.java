package com.softserveinc.dokazovi.pojo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserSearchCriteriaTest {

	@InjectMocks
	private UserSearchCriteria userSearchCriteria;

	@Test
	void hasDirections() {

		Set<Integer> directionsIds = Set.of(1, 4, 6);

		userSearchCriteria.setDirections(directionsIds);

		assertEquals(true, userSearchCriteria.hasDirections());
	}

	@Test
	void hasNotDirections() {

		assertEquals(false, userSearchCriteria.hasDirections());
	}

	@Test
	void hasRegions() {

		Set<Integer> regionsIds = Set.of(1, 4, 6);

		userSearchCriteria.setRegions(regionsIds);

		assertEquals(true, userSearchCriteria.hasRegions());
	}

	@Test
	void hasNotRegions() {

		assertEquals(false, userSearchCriteria.hasRegions());
	}
}