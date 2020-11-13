package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.user.LatestExpertPostDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostMapperTest {

	private final Timestamp createdAt = Timestamp.valueOf("1991-05-22 10:10:10.0");
	private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

	private PostEntity post;
	private UserEntity author;
	private DirectionEntity mainDirection;
	private PostTypeEntity type;
	private InstitutionEntity mainInstitution;

	@BeforeEach
	void init() {
		mainInstitution = InstitutionEntity.builder()
				.id(1)
				.name("Some institution")
				.build();

		author = UserEntity.builder()
				.id(1)
				.firstName("Some firstname")
				.lastName("Some lastname")
				.avatar("Some avatar url")
				.mainInstitution(mainInstitution)
				.build();

		mainDirection = DirectionEntity.builder()
				.id(1)
				.name("Some main direction")
				.build();

		type = PostTypeEntity.builder()
				.id(1)
				.name("Article")
				.build();

		post = PostEntity.builder()
				.id(1)
				.title("Post title")
				.content("Post content")
				.author(author)
				.mainDirection(mainDirection)
				.type(type)
				.createdAt(createdAt)
				.build();
	}

	@Test
	void toLatestExpertPostDTO_whenMaps_thenCorrect() {
		LatestExpertPostDTO latestExpertPostDTO = postMapper.toLatestExpertPostDTO(post);

		assertEquals(latestExpertPostDTO.getId(), post.getId());
		assertEquals(latestExpertPostDTO.getTitle(), post.getTitle());
	}
}