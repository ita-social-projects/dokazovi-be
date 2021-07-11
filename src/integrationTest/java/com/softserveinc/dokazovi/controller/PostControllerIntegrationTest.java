package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.DokazoviApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DokazoviApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@Testcontainers
public class PostControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void someTest() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt", "id").descending());
		HttpHeaders headers = new HttpHeaders();

		mockMvc.perform(get(POST + POST_LATEST + "/?page=0"))
				.andExpect(status().isOk());

	}
	@Test
	public void someTest1() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt", "id").descending());
		HttpHeaders headers = new HttpHeaders();

		mockMvc.perform(get(POST + POST_LATEST + "/?page=0"))
				.andExpect(status().isOk());

	}

}

