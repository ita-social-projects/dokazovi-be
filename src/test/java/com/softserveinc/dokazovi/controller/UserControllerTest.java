package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.pojo.UserSearchCriteria;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.MailSenderService;
import com.softserveinc.dokazovi.service.PasswordResetTokenService;
import com.softserveinc.dokazovi.service.UserService;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_ALL_EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_CHANGE_PASSWORD;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_CHECK_TOKEN;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_GET_AUTHORITIES;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_GET_CURRENT_USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RANDOM_EXPERTS;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_RESET_PASSWORD;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_UPDATE_PASSWORD;
import static com.softserveinc.dokazovi.entity.enumerations.RolePermission.DELETE_POST;
import static com.softserveinc.dokazovi.entity.enumerations.RolePermission.SAVE_OWN_PUBLICATION;
import static com.softserveinc.dokazovi.entity.enumerations.RolePermission.SAVE_TAG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserPrincipal userPrincipal;
	@Mock
	private UserService userService;
	@Mock
	private PasswordResetTokenService passwordResetTokenService;
	@Mock
	private MailSenderService mailSenderService;
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

		Set<Integer> directionsIds = Set.of(1, 3, 5);

		Pageable pageable = PageRequest.of(0, 12);

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

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		Pageable pageable = PageRequest.of(0, 6);

		verify(userService).findAllExperts(userSearchCriteria, pageable);
	}

	@Test
	void getAllExpertsByDirectionsAndByRegions_FilteredByRegionsOnly_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?page=0&regions=1,4,6";

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();

		Set<Integer> regionsIds = Set.of(1, 4, 6);

		userSearchCriteria.setRegions(regionsIds);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		Pageable pageable = PageRequest.of(0, 6);

		verify(userService).findAllExperts(userSearchCriteria, pageable);
	}

	@Test
	void getAllExpertsByDirectionsAndByRegions_FilteredByDirectionsOnly_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?page=0&directions=1,4,6";

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();

		Set<Integer> directionsIds = Set.of(1, 4, 6);

		userSearchCriteria.setDirections(directionsIds);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		Pageable pageable = PageRequest.of(0, 6);

		verify(userService).findAllExperts(userSearchCriteria, pageable);
	}

	@Test
	void getAllExpertsByDirectionsAndByRegions_FilteredByDirectionsAndByRegions_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?page=0&directions=1,4,6&regions=1,4,6";

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();

		Set<Integer> directionsIds = Set.of(1, 4, 6);
		Set<Integer> regionsIds = Set.of(1, 4, 6);

		userSearchCriteria.setDirections(directionsIds);
		userSearchCriteria.setRegions(regionsIds);

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		Pageable pageable = PageRequest.of(0, 6);

		verify(userService).findAllExperts(userSearchCriteria, pageable);
	}

	@Test
	void getAllExpertsByName_isOk() throws Exception {
		String uri = USER + USER_ALL_EXPERTS + "/?userName=Ivan Ivanov";

		UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
		userSearchCriteria.setUserName("Ivan Ivanov");

		mockMvc.perform(get(uri)).andExpect(status().isOk());

		Pageable pageable = PageRequest.of(0, 6);

		verify(userService).findAllExperts(userSearchCriteria, pageable);
	}

	@Test
	void getCurrentUser_notFound() throws Exception {
		Integer existingUserId = 9;
		String uri = USER + USER_GET_CURRENT_USER;
		UserDTO userDTO = UserDTO.builder()
				.id(existingUserId)
				.build();

		when(userService.findExpertById(any(Integer.class))).thenReturn(userDTO);
		when(userPrincipal.getId()).thenReturn(9);
		mockMvc.perform(get(uri)).andExpect(status().isNotFound());
	}

	@Test
	void resetPasswordTest() throws Exception {
		String emailContent = "{\n"
				+ "  \"email\": \"igor.zaharko@gmail.com\"\n"
				+ "}";
		UserEntity userEntity = UserEntity.builder()
				.email("igor.zaharko@gmail.com")
				.build();
		when(userService.findUserEntityByEmail(any(String.class)))
				.thenReturn(userEntity);
		mockMvc.perform(post(USER + USER_RESET_PASSWORD)
				.contentType(MediaType.APPLICATION_JSON)
				.content(emailContent))
				.andExpect(status().isOk());
		verify(userService).findUserEntityByEmail(any(String.class));
		verify(userService).sendPasswordResetToken(userEntity, null);
	}

	@Test
	void updatePasswordTestIsOk () throws Exception {
		String content = "{\n"
				+ "  \"matchPassword\": \"qwerty12345\",\n"
				+ "  \"newPassword\": \"qwerty12345\",\n"
				+ "  \"token\": \"ef590bd8-e993-4153-8206-b963732bfeb9\"\n"
				+ "}";
		UserEntity user = UserEntity.builder()
				.id(1)
				.email("igor.zaharko@gmail.com")
				.build();
		String token = "ef590bd8-e993-4153-8206-b963732bfeb9";
		PasswordResetTokenEntity tokenEntity = PasswordResetTokenEntity.builder()
				.token("ef590bd8-e993-4153-8206-b963732bfeb9")
				.dateExpiration(LocalDateTime.now().plusMinutes(60))
				.userEntity(user)
				.build();
		when(passwordResetTokenService.getByToken(token)).thenReturn(tokenEntity);
		mockMvc.perform(post(USER + USER_UPDATE_PASSWORD)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isOk());
	}

	@Test
	void changePasswordTest() throws Exception {
		String content = "{\n"
				+ "  \"email\": \"igor.zaharko@gmail.com\",\n"
				+ "  \"password\": \"qwerty12345\"\n"
				+ "}";
		UserEntity userEntity = UserEntity.builder()
				.email("igor.zaharko@gmail.com")
				.password("qwerty12345")
				.build();
		when(userService.findUserEntityByEmail(any(String.class)))
				.thenReturn(userEntity);
		mockMvc.perform(post(USER + USER_CHANGE_PASSWORD)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isOk());
		verify(userService).findUserEntityByEmail(any(String.class));
	}

	@Test
	void updatePasswordTestNotFound () throws Exception {
		String content = "{\n"
				+ "  \"matchPassword\": \"qwerty12345\",\n"
				+ "  \"newPassword\": \"qwerty12345\",\n"
				+ "  \"token\": \"ef590bd8-e993-4153-8206-b963732bfeb9\"\n"
				+ "}";
		String token = "ef590bd8-e993-4153-8206-b963732bfeb9";
		when(passwordResetTokenService.getByToken(token)).thenReturn(null);
		mockMvc.perform(post(USER + USER_UPDATE_PASSWORD)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isNotFound());
	}

	@Test
	void checkTokenTest() throws Exception {
		String token = "ef590bd8-e993-4153-8206-b963732bfeb9";
		String uri = USER + USER_CHECK_TOKEN + "?token=" + token;
		mockMvc.perform(get(uri)).andExpect(status().isNotFound());
		when(passwordResetTokenService.validatePasswordResetToken(token)).thenReturn(true);
		mockMvc.perform(get(uri)).andExpect(status().isOk());
	}

	@Test
	void getAuthoritiesTestIsOk() throws Exception {
		Collection<? extends GrantedAuthority> expected = Set.of(SAVE_OWN_PUBLICATION,
				SAVE_TAG,
				DELETE_POST);
		String uri = USER + USER_GET_AUTHORITIES;
		doReturn(expected).when(userPrincipal).getAuthorities();
		Collection<? extends GrantedAuthority> actual = userPrincipal.getAuthorities();
		mockMvc.perform(get(uri)).andExpect(status().isOk());
		Assertions.assertNotNull(actual);
		Assertions.assertNotNull(userPrincipal);
		Assertions.assertEquals(expected, actual);
	}
}
