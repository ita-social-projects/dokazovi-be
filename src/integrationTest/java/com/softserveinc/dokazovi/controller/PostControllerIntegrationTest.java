package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.DokazoviApplication;
import com.softserveinc.dokazovi.dto.payload.LoginRequest;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.PostConstruct;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;
import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static com.softserveinc.dokazovi.controller.RequestsBodies.BODY_FOR_POST_SAVE;
import static com.softserveinc.dokazovi.controller.RequestsBodies.BODY_FOR_POST_UPDATE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DokazoviApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = {"/db/insertBasicInformation.sql"})
public class PostControllerIntegrationTest {

	@Autowired
	Flyway flyway;

	@LocalServerPort
	private int port;

	@PostConstruct
	void init() {
		RestAssured.port = port;
	}

	@AfterEach
	void cleanDatabase() {
		flyway.clean();
		flyway.migrate();
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getAllLatestPosts() {
		when()
				.get(POST + POST_LATEST + "/?page=0")
				.then()
				.assertThat().statusCode(HttpStatus.OK.value())
				.assertThat().contentType("application/json");
	}

	@Test
	public void savePostWithoutAuthentication() {
		given()
				.contentType("application/json")
				.body(BODY_FOR_POST_SAVE)
		.when()
				.post(POST)
		.then()
				.statusCode(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	public void saveOwnPostWithAuthentication() {
		given()
				.auth().oauth2(getAccessToken("ivan@mail.com","ivan"))
				.contentType("application/json")
				.body(BODY_FOR_POST_SAVE)
		.when()
				.post(POST)
		.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void saveNotOwnPost() {
		given()
				.auth().oauth2(getAccessToken("fedot@mail.com","fedot"))
				.contentType("application/json")
				.body(BODY_FOR_POST_SAVE)
		.when()
				.post(POST)
		.then()
				.statusCode(HttpStatus.FORBIDDEN.value());
	}

	@Test
	public void saveNotOwnPostByAdmin() {
		given()
				.auth().oauth2(getAccessToken("admin@mail.com","admin"))
				.contentType("application/json")
				.body(BODY_FOR_POST_SAVE)
		.when()
				.post(POST)
		.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void updateNotExistingPost() {
		given()
				.auth().oauth2(getAccessToken("admin@mail.com","admin"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
		.when()
				.put(POST)
		.then()
				.statusCode(HttpStatus.OK.value())
				.assertThat()
				.body("success", equalTo(false));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void updateExistingPostByAuthor() {
		given()
				.auth().oauth2(getAccessToken("ivan@mail.com","ivan"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
		.when()
				.put(POST)
		.then()
				.statusCode(HttpStatus.OK.value())
				.assertThat()
				.body("success", equalTo(true));
	}

	String getAccessToken(String testEmail, String testPassword) {
		LoginRequest request = new LoginRequest();
		request.setEmail(testEmail);
		request.setPassword(testPassword);

		return given()
						.contentType("application/json")
						.body(request)
				.when()
						.post(AUTH + AUTH_LOGIN)
				.then()
						.contentType("application/json")
				.extract()
						.path("accessToken");
		}

}

