package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.DokazoviApplication;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static io.restassured.RestAssured.when;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DokazoviApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostControllerIntegrationTest {

	@Autowired
	Flyway flyway;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		RestAssured.port = port;
	}

	@AfterEach
	void cleanDatabase() {
		flyway.clean();
		flyway.migrate();
	}

	@Test
	@Sql(value = {"classpath:db/testdata/V1.1__add_demo_data.sql"})
	public void wit() {
		when().get(POST + POST_LATEST).then().log().body().statusCode(200);
	}

	@Test
	@Sql(value = {"classpath:db/testdata/V1.1__add_demo_data.sql"})
	public void ho() {
		when().get(POST + POST_LATEST).then().log().body().statusCode(200);
	}

	@Test
	@Sql(value = {"classpath:db/testdata/V1.1__add_demo_data.sql"})
	public void ut() {
		when().get(POST + POST_LATEST).then().log().body().statusCode(200);
	}

	@Test
	@Sql(value = {"classpath:db/testdata/V1.1__add_demo_data.sql"})
	public void without() {
		when().get(POST + POST_LATEST).then().log().body().statusCode(200);
	}
}

