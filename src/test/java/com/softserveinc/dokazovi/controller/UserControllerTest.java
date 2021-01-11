package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ALL_EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RANDOM_EXPERTS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserService userService;
	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(userController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	void getRandomExpertPreview_GetWithPagination_isOk() throws Exception {
		String uri = USER + USER_RANDOM_EXPERTS + "/?page=0";
		Pageable pageable = PageRequest.of(0, 12);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(userService).findRandomExpertPreview(eq(null), eq(pageable));
	}

	@Test
	void getRandomExpertPreview_GetWithPaginationByDirections_isOk() throws Exception {
		String uri = USER + USER_RANDOM_EXPERTS + "/?page=0&directions=1,3,5";
		Pageable pageable = PageRequest.of(0, 12);
		Set<Integer> directionsIds = Set.of(1, 3, 5);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(userService).findRandomExpertPreview(eq(directionsIds), eq(pageable));
	}

	@Test
	void getExpertById_WhenExists_isOk() throws Exception {
		Integer existingUserId = 1;
		String uri = USER + "/" + existingUserId;
		UserDTO userDTO = UserDTO.builder()
				.id(existingUserId)
				.build();

		when(userService.findExpertById(any(Integer.class))).thenReturn(userDTO);
		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(userService).findExpertById(eq(existingUserId));
	}

	@Test
	void getExpertById_WhenNotExists_NotFound() throws Exception {
		Integer notExistingUserId = 1;
		String uri = USER + "/" + notExistingUserId;

		when(userService.findExpertById(any(Integer.class))).thenReturn(null);
		mockMvc.perform(get(uri)).andExpect(status().isNotFound());

		verify(userService).findExpertById(eq(notExistingUserId));
	}

	@Test
	void getAllExpertsByDirectionsAndByRegions_NotFiltered_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?page=0";
		Pageable pageable = PageRequest.of(0, 6);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(userService).findAllExpertsByDirectionsAndRegions(eq(null), eq(null), eq(pageable));
	}

	@Test
	void getAllExpertsByDirectionsAndByRegions_FilteredByRegionsOnly_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?page=0&regions=1,4,6";
		Pageable pageable = PageRequest.of(0, 6);
		Set<Integer> regionsIds = Set.of(1, 4, 6);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(userService).findAllExpertsByDirectionsAndRegions(eq(null), eq(regionsIds), eq(pageable));
	}

	@Test
	void getAllExpertsByDirectionsAndByRegions_FilteredByDirectionsOnly_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?page=0&directions=1,4,6";
		Pageable pageable = PageRequest.of(0, 6);
		Set<Integer> directionsIds = Set.of(1, 4, 6);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(userService).findAllExpertsByDirectionsAndRegions(eq(directionsIds), eq(null), eq(pageable));
	}

	@Test
	void getAllExpertsByDirectionsAndByRegions_FilteredByDirectionsAndByRegions_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?page=0&directions=1,4,6&regions=1,4,6";
		Pageable pageable = PageRequest.of(0, 6);
		Set<Integer> directionsIds = Set.of(1, 4, 6);
		Set<Integer> regionsIds = Set.of(1, 4, 6);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		verify(userService).findAllExpertsByDirectionsAndRegions(eq(directionsIds), eq(regionsIds), eq(pageable));
	}
}