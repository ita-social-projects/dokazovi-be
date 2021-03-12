package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.dto.user.LatestUserPostDTO;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostMapperTest {

	private final Timestamp createdAt = Timestamp.valueOf("1991-05-22 10:10:10.0");
	private final Timestamp modifiedAt = Timestamp.valueOf("1991-05-22 10:10:10.0");
	private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

	private PostEntity post;
	private DoctorEntity doctor;
	private UserEntity author;
	private PostTypeEntity type;
	private InstitutionEntity mainInstitution;
	private PostSaveFromUserDTO postSaveFromUserDTO;

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
				.build();

		doctor = DoctorEntity.builder()
				.id(2)
				.mainInstitution(mainInstitution)
				.profile(author)
				.build();

		author.setDoctor(doctor);

		type = PostTypeEntity.builder()
				.id(1)
				.name("Article")
				.build();

		post = PostEntity.builder()
				.id(1)
				.title("Post title")
				.content("Post content")
				.previewImageUrl("Post previewImageUrl")
				.videoUrl("Post videoUrl")
				.author(author)
				.type(type)
				.createdAt(createdAt)
				.modifiedAt(modifiedAt)
				.build();

		postSaveFromUserDTO = PostSaveFromUserDTO.builder()
				.id(1)
				.title("PostSaveFromUserDTO title")
				.preview("PostSaveFromUserDTO preview")
				.content("PostSaveFromUserDTO content")
				.previewImageUrl("PostSaveFromUserDTO previewImageUrl")
				.videoUrl("PostSaveFromUserDTO videoUrl")
				.type(PostTypeDTO.builder()
						.id(1)
						.name("1")
						.build())
				.directions(new HashSet<>())
				.tags(new HashSet<>())
				.sources(new HashSet<>())
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
		assertEquals(
				postDTO.getAuthor().getMainInstitution().getId(), author.getDoctor().getMainInstitution().getId());
		assertEquals(
				postDTO.getAuthor().getMainInstitution().getName(), author.getDoctor().getMainInstitution().getName());
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

	@Test
	void toPostEntity() {
		PostEntity postEntity = postMapper.toPostEntity(postSaveFromUserDTO);
		assertEquals(postEntity.getId(), postSaveFromUserDTO.getId());
		assertEquals(postEntity.getTitle(), postSaveFromUserDTO.getTitle());
		assertEquals(postEntity.getPreview(), postSaveFromUserDTO.getPreview());
		assertEquals(postEntity.getContent(), postSaveFromUserDTO.getContent());
		assertEquals(postEntity.getType().getId(), postSaveFromUserDTO.getType().getId());
		assertEquals(postEntity.getType().getName(), postSaveFromUserDTO.getType().getName());
		assertEquals(postEntity.getDirections().hashCode(), postSaveFromUserDTO.getDirections().hashCode());
		assertEquals(postEntity.getTags().hashCode(), postSaveFromUserDTO.getTags().hashCode());
		assertEquals(postEntity.getSources().hashCode(), postSaveFromUserDTO.getSources().hashCode());
	}

	@Test
	void updatePostEntityFromDTO() {
		PostEntity postEntity = post;
		postMapper.updatePostEntityFromDTO(postSaveFromUserDTO, postEntity);
		assertEquals(postEntity.getId(), postSaveFromUserDTO.getId());
		assertEquals(postEntity.getTitle(), postSaveFromUserDTO.getTitle());
		assertEquals(postEntity.getPreview(), postSaveFromUserDTO.getPreview());
		assertEquals(postEntity.getContent(), postSaveFromUserDTO.getContent());
		assertEquals(postEntity.getType().getId(), postSaveFromUserDTO.getType().getId());
		assertEquals(postEntity.getType().getName(), postSaveFromUserDTO.getType().getName());
		assertEquals(postEntity.getDirections().hashCode(), postSaveFromUserDTO.getDirections().hashCode());
		assertEquals(postEntity.getTags().hashCode(), postSaveFromUserDTO.getTags().hashCode());
		assertEquals(postEntity.getSources().hashCode(), postSaveFromUserDTO.getSources().hashCode());
	}
}