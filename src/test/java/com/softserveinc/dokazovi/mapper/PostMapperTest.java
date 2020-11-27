package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.user.LatestUserPostDTO;
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
	private final Timestamp modifiedAt = Timestamp.valueOf("1991-05-22 10:10:10.0");
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
				.modifiedAt(modifiedAt)
				.build();
	}

	@Test
	void toPostDTO() {
		PostDTO postDTO = postMapper.toPostDTO(post);
		assertEquals(postDTO.getId(), post.getId());
		assertEquals(postDTO.getTitle(), post.getTitle());
		assertEquals(postDTO.getAuthor().getId(), author.getId());
		assertEquals(postDTO.getAuthor().getFirstName(), author.getFirstName());
		assertEquals(postDTO.getAuthor().getLastName(), author.getLastName());
		assertEquals(postDTO.getAuthor().getAvatar(), author.getAvatar());
		assertEquals(postDTO.getAuthor().getMainInstitution().getId(), author.getMainInstitution().getId());
		assertEquals(postDTO.getAuthor().getMainInstitution().getName(), author.getMainInstitution().getName());
		assertEquals(postDTO.getMainDirection().getId(), mainDirection.getId());
		assertEquals(postDTO.getMainDirection().getName(), mainDirection.getName());
		assertEquals(postDTO.getType().getId(), type.getId());
		assertEquals(postDTO.getType().getName(), type.getName());
		assertEquals(postDTO.getCreatedAt(), post.getCreatedAt());
		assertEquals(postDTO.getModifiedAt(), post.getModifiedAt());
	}

	@Test
	void toLatestExpertPostDTO_whenMaps_thenCorrect() {
		LatestUserPostDTO latestUserPostDTO = postMapper.toLatestExpertPostDTO(post);

		assertEquals(latestUserPostDTO.getId(), post.getId());
		assertEquals(latestUserPostDTO.getTitle(), post.getTitle());
	}
}