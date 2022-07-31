package com.softserveinc.dokazovi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.dokazovi.dto.user.AuthorDTO;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthorControllerTest {

	private MockMvc mockMvc;

	@Mock
	private AuthorController authorController;

	@Mock
	private AuthorService authorService;

	@Test
	void createAuthor() throws Exception {
		String content = "{\n"
				+ "  \"id\": 2,\n"
				+ "  \"email\": \"mmaksry@gmail.com\",\n"
				+ "  \"firstName\": \"MAKS\",\n"
				+ "  \"lastName\": \"LUKIANEN\",\n"
				+ "  \"placeOfWork\": \"ANYWHERE\",\n"
				+ "  \"avatar\": \"avatar\",\n"
				+ "  \"bio\": \"BIO\",\n"
				+ "  \"socialNetwork\": \"facebook.com\",\n"
				+ "  \"city\": 1\n"
				+ "}";
		mockMvc.perform(post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isCreated());
	}

	@Test
	void updateAuthor() throws Exception {
		String content = "{\n"
				+ "  \"id\": 2,\n"
				+ "  \"email\": \"mmaksry@GMAIL.com\",\n"
				+ "  \"firstName\": \"MAKS\",\n"
				+ "  \"lastName\": \"LUKIANEN\",\n"
				+ "  \"placeOfWork\": \"ANYWHERE\",\n"
				+ "  \"avatar\": \"avatar\",\n"
				+ "  \"bio\": \"BIO\",\n"
				+ "  \"socialNetwork\": \"facebook.com\",\n"
				+ "  \"city\": 1\n"
				+ "}";
		ObjectMapper mapper = new ObjectMapper();
		AuthorDTO author = mapper.readValue(content, AuthorDTO.class);
		mockMvc.perform(put("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isOk());
	}

	@Test
	void removeAuthor() throws Exception {
		String content = "{\n"
				+ "  \"id\": 1,\n"
				+ "  \"email\": \"mmaksry@GMAIL.com\",\n"
				+ "  \"firstName\": \"MAKS\",\n"
				+ "  \"lastName\": \"LUKIANEN\",\n"
				+ "  \"placeOfWork\": \"ANYWHERE\",\n"
				+ "  \"avatar\": \"avatar\",\n"
				+ "  \"bio\": \"BIO\",\n"
				+ "  \"socialNetwork\": \"facebook.com\",\n"
				+ "  \"city\": 1\n"
				+ "}";
		Mockito.when(authorService.delete(any(Integer.class), any(UserPrincipal.class)))
				.thenReturn(true);
		mockMvc.perform(delete("/doctor/1").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk()).andExpect(result ->
						Assertions.assertEquals("{\"success\":true,\"message\":\"Doctor 1 deleted successfully\"}",
								result.getResponse().getContentAsString()));
	}
}
