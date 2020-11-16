package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.dto.post.LatestPostDTO;
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
	void toLatestPostDTO_whenMaps_thenCorrect() {
		LatestPostDTO latestPostDTO = postMapper.toLatestPostDTO(post);
		assertEquals(latestPostDTO.getId(), post.getId());
		assertEquals(latestPostDTO.getTitle(), post.getTitle());
		assertEquals(latestPostDTO.getAuthor().getId(), author.getId());
		assertEquals(latestPostDTO.getAuthor().getFirstName(), author.getFirstName());
		assertEquals(latestPostDTO.getAuthor().getLastName(), author.getLastName());
		assertEquals(latestPostDTO.getAuthor().getAvatar(), author.getAvatar());
		assertEquals(latestPostDTO.getAuthor().getMainInstitution().getId(), author.getMainInstitution().getId());
		assertEquals(latestPostDTO.getAuthor().getMainInstitution().getName(), author.getMainInstitution().getName());
		assertEquals(latestPostDTO.getDirection().getId(), mainDirection.getId());
		assertEquals(latestPostDTO.getDirection().getName(), mainDirection.getName());
		assertEquals(latestPostDTO.getType().getId(), type.getId());
		assertEquals(latestPostDTO.getType().getName(), type.getName());
		assertEquals(latestPostDTO.getCreatedAt(), createdAt);
	}

	@Test
	void toImportantPostDTO_whenMaps_thenCorrect() {
		ImportantPostDTO importantPostDTO = postMapper.toImportantPostDTO(post);
		assertEquals(importantPostDTO.getId(), post.getId());
		assertEquals(importantPostDTO.getTitle(), post.getTitle());
		assertEquals(importantPostDTO.getAuthor().getId(), author.getId());
		assertEquals(importantPostDTO.getAuthor().getFirstName(), author.getFirstName());
		assertEquals(importantPostDTO.getAuthor().getLastName(), author.getLastName());
		assertEquals(importantPostDTO.getAuthor().getAvatar(), author.getAvatar());
		assertEquals(importantPostDTO.getAuthor().getMainInstitution().getId(), author.getMainInstitution().getId());
		assertEquals(importantPostDTO.getAuthor().getMainInstitution().getName(),
				author.getMainInstitution().getName());
		assertEquals(importantPostDTO.getDirection().getId(), mainDirection.getId());
		assertEquals(importantPostDTO.getDirection().getName(), mainDirection.getName());
		assertEquals(importantPostDTO.getType().getId(), type.getId());
		assertEquals(importantPostDTO.getType().getName(), type.getName());
		assertEquals(importantPostDTO.getCreatedAt(), createdAt);
	}
}