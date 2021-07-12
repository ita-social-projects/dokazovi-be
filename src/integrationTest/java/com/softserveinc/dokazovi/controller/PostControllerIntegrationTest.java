package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.DokazoviApplication;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO add deletion script and several init scripts, write composed test

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DokazoviApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class PostControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	Flyway flyway;

	@AfterEach
	void init() {
		flyway.clean();
		flyway.migrate();
	}

	@Test
	public void without() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt", "id").descending());

		mockMvc.perform(get(POST + POST_LATEST + "/?page=0"))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	@Sql(value = {"classpath:db/testdata/V1.1__add_demo_data.sql"})
	public void withInjection() throws Exception {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt", "id").descending());

		mockMvc.perform(get(POST + POST_LATEST + "/?page=0"))
				.andExpect(status().isOk()).andDo(print());

	}

}

