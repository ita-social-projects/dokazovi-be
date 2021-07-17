package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.DokazoviApplication;
import com.softserveinc.dokazovi.dto.direction.DirectionDTOForSavingPost;
import com.softserveinc.dokazovi.dto.origin.OriginDTOForSavingPost;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeIdOnlyDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.PostConstruct;
import java.util.Set;

import static com.softserveinc.dokazovi.controller.EndPoints.POST;
import static com.softserveinc.dokazovi.controller.EndPoints.POST_LATEST;
import static io.restassured.RestAssured.given;
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
	@Sql(value = {"/db/insertBasicInformation.sql"})
	public void putPostWithoutAuthorization() {
		PostSaveFromUserDTO post = PostSaveFromUserDTO.builder()
				.authorId(1)
				.title("Foo")
				.videoUrl("Foo_url")
				.previewImageUrl("Foo_review_url")
				.importantImageUrl("Foo_important_url")
				.preview("Foo_preview")
				.content("Foo_content")
				.type(PostTypeIdOnlyDTO.builder().id(1).build())
				.directions(Set.of(DirectionDTOForSavingPost.builder().id(1).build()))
				.origins(Set.of(OriginDTOForSavingPost.builder().id(1).build()))
				.build();
		given()
				.contentType("application/json")
				.body(post)
		.when()
				.post(POST)
		.then()
				.statusCode(HttpStatus.UNAUTHORIZED.value());
	}

//	@Test
//	@Sql(value = {"classpath:db/testdata/V1.1__add_demo_data.sql"})
//	public void ut() {
//		when().get(POST + POST_LATEST).then().log().body().statusCode(200);
//	}
//
//	@Test
//	@Sql(value = {"classpath:db/testdata/V1.1__add_demo_data.sql"})
//	public void without() {
//		when().get(POST + POST_LATEST).then().log().body().statusCode(200);
//	}
}

