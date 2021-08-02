package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.DokazoviApplication;
import com.softserveinc.dokazovi.dto.payload.LoginRequest;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
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

import javax.annotation.PostConstruct;
import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.AUTH;
import static com.softserveinc.dokazovi.controller.EndPoints.AUTH_LOGIN;
import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_ALL_POSTS;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_GET_POST_BY_ID;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_IMPORTANT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_DIRECTION;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_EXPERT;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST_BY_EXPERT_AND_STATUS;
import static com.softserveinc.dokazovi.controller.RequestsBodies.BODY_FOR_POST_SAVE;
import static com.softserveinc.dokazovi.controller.RequestsBodies.BODY_FOR_POST_UPDATE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
				.body("success", is(false))
				.body("message", is("Entity not found"));
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
				.body("success", is(true));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void updateNotOwnPostByAdmin() {
		given()
				.auth().oauth2(getAccessToken("admin@mail.com","admin"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
		.when()
				.put(POST)
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("success", equalTo(true));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void updateNotOwnPost() {
		given()
				.auth().oauth2(getAccessToken("fedot@mail.com","fedot"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
		.when()
				.put(POST)
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("success", equalTo(false))
				.body("message", equalTo("Forbidden permission"));
	}

	@Test
	public void updatePostWithoutAuthentication() {
		given()
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
		.when()
				.put(POST)
		.then()
				.statusCode(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	public void deletePostWithoutAuthentication() {
		given()
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
				.pathParam("postId",1)
		.when()
				.delete(POST + POST_GET_POST_BY_ID)
		.then()
				.statusCode(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void deleteNotOwnPost() {
		given()
				.auth().oauth2(getAccessToken("fedot@mail.com","fedot"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
				.pathParam("postId",1)
		.when()
				.delete(POST + POST_GET_POST_BY_ID)
		.then()
				.statusCode(HttpStatus.FORBIDDEN.value());
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void deleteNotOwnPostByAdmin() {
		given()
				.auth().oauth2(getAccessToken("admin@mail.com","admin"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
				.pathParam("postId",1)
		.when()
				.delete(POST + POST_GET_POST_BY_ID)
		.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void deleteOwnPost() {
		given()
				.auth().oauth2(getAccessToken("ivan@mail.com","ivan"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
				.pathParam("postId",1)
		.when()
				.delete(POST + POST_GET_POST_BY_ID)
		.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deleteNotExistingPost() {
		given()
				.auth().oauth2(getAccessToken("fedot@mail.com","fedot"))
				.contentType("application/json")
				.body(BODY_FOR_POST_UPDATE)
				.pathParam("postId",1)
		.when()
				.delete(POST + POST_GET_POST_BY_ID)
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("success", is(false))
				.body("message", is("Post with 1 not found"));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getPostById() {
		PostDTO post = given()
				.pathParam("postId", 2)
		.when()
				.get(POST + POST_GET_POST_BY_ID)
		.then()
				.statusCode(HttpStatus.OK.value())
		.extract()
				.jsonPath().getObject("", PostDTO.class);

		assertThat(post, allOf(
				hasProperty("id", is(2)),
				hasProperty("title", is("Another title"))));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getLatestPostsByExpertAndStatus() {
		List<PostDTO> posts = given()
				.param("expert", 1)
				.param("status", PostStatus.PUBLISHED)
		.when()
				.get(POST + POST_LATEST_BY_EXPERT_AND_STATUS)
		.then()
				.statusCode(HttpStatus.OK.value())
		.extract()
				.jsonPath().getList("content", PostDTO.class);

		assertThat(posts, everyItem(allOf(
				hasProperty("author", hasProperty("id", is(1))),
				hasProperty("publishedAt", not(emptyOrNullString())))));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getAllPostsWithDirectionsThreeAndFive() {
		List<PostDTO> posts = given()
				.param("directions", 3, 5)
				.param("types", 1, 2, 3)
				.param("origins", 1, 2, 3)
		.when()
				.get(POST + POST_ALL_POSTS)
		.then()
				.statusCode(HttpStatus.OK.value())
		.extract()
				.jsonPath().getList("content", PostDTO.class);

		assertThat(posts, not(empty()));
		assertThat(posts,
				everyItem(hasProperty("directions",
						hasItem(hasProperty("id", either(is(3)).or(is(5)))))));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getLatestPostsByDirection() {
		List<PostDTO> posts = given()
				.param("direction", 3)
		.when()
				.get(POST + POST_LATEST_BY_DIRECTION)
		.then()
				.statusCode(HttpStatus.OK.value())
		.extract()
				.jsonPath().getList("content", PostDTO.class);

		assertThat(posts,
				everyItem(hasProperty("directions",	hasItem(hasProperty("id", is(3))))));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getLatestPostsByExpert() {
		List<PostDTO> posts = given()
				.param("expert", 1)
		.when()
				.get(POST + POST_LATEST_BY_EXPERT)
		.then()
				.statusCode(HttpStatus.OK.value())
		.extract()
				.jsonPath().getList("content", PostDTO.class);

		assertThat(posts, everyItem(hasProperty("author", hasProperty("id", is(1)))));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getAllLatestPosts() {
		List<PostDTO> posts = when()
				.get(POST + POST_ALL_POSTS)
		.then()
				.statusCode(HttpStatus.OK.value())
		.extract()
				.jsonPath().getList("content", PostDTO.class);

		assertThat(posts, not(empty()));
		assertThat(posts, everyItem(hasProperty("publishedAt", not(emptyOrNullString()))));
	}

	@Test
	@Sql(value = {"/db/insertBasicInformation.sql", "/db/postsData.sql"})
	public void getAllImportantPosts() {
		List<PostDTO> posts = when()
				.get(POST + POST_IMPORTANT)
		.then()
				.statusCode(HttpStatus.OK.value())
		.extract()
				.jsonPath().getList("content", PostDTO.class);

		assertThat(posts, contains(hasProperty("id", is(1))));
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

