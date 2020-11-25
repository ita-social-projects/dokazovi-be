package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.user.RandomExpertFilteringDTO;
import com.softserveinc.dokazovi.service.UserService;
import org.assertj.core.util.Sets;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RANDOM_EXPERTS;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
		RandomExpertFilteringDTO requestBody = new RandomExpertFilteringDTO();

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isOk());

		verify(userService).getRandomExpertPreview(eq(pageable), eq(requestBody));
	}

	@Test
	void getRandomExpertPreview_GetWithPaginationByDirections_isOk() throws Exception {
		String uri = USER + USER_RANDOM_EXPERTS + "/?page=0";
		Pageable pageable = PageRequest.of(0, 12);
		RandomExpertFilteringDTO requestBody = new RandomExpertFilteringDTO();
		requestBody.setDirectionsIds(Sets.newHashSet(Arrays.asList(1, 3, 5)));

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content("{\"directionsIds\": [1,3,5]}"))
				.andExpect(status().isOk());

		verify(userService).getRandomExpertPreview(eq(pageable), eq(requestBody));
	}
}