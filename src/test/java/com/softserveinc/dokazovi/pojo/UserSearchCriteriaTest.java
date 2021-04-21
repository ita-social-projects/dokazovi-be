package com.softserveinc.dokazovi.pojo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserSearchCriteriaTest {

	@InjectMocks
	private UserSearchCriteria userSearchCriteria;

	@Test
	void hasDirections() {

		Set<Integer> directionsIds = Set.of(1, 4, 6);

		userSearchCriteria.setDirections(directionsIds);

		assertTrue(userSearchCriteria.hasDirections());
	}

	@Test
	void hasNotDirections() {

		assertFalse(userSearchCriteria.hasDirections());
	}

	@Test
	void hasRegions() {

		Set<Integer> regionsIds = Set.of(1, 4, 6);

		userSearchCriteria.setRegions(regionsIds);

		assertTrue(userSearchCriteria.hasRegions());
	}

	@Test
	void hasNotRegions() {

		assertFalse(userSearchCriteria.hasRegions());
	}
}
