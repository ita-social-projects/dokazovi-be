package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

	private final Timestamp createdAt = Timestamp.valueOf("1991-05-22 10:10:10.0");
	private final Timestamp latestCreatedAt = Timestamp.valueOf("1992-05-22 10:10:10.0");
	private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
	private UserEntity userEntity;
	private InstitutionEntity mainInstitution;
	private DirectionEntity mainUserDirection;
	private CityEntity cityEntity;
	private PostEntity postEntity;
	private PostEntity latestPostEntity;

	@BeforeEach
	void init() {
		cityEntity = CityEntity.builder()
				.id(1)
				.name("City name")
				.build();

		mainInstitution = InstitutionEntity.builder()
				.id(1)
				.name("Some institution name")
				.city(cityEntity)
				.build();

		mainUserDirection = DirectionEntity.builder()
				.id(1)
				.name("Direction")
				.build();

		userEntity = UserEntity.builder()
				.id(1)
				.firstName("Some firstname")
				.lastName("Some lastname")
				.avatar("Some avatar url")
				.mainInstitution(mainInstitution)
				.mainDirection(mainUserDirection)
				.build();

		postEntity = PostEntity.builder()
				.id(1)
				.title("Post title")
				.author(userEntity)
				.createdAt(createdAt)
				.status(PostStatus.PUBLISHED)
				.build();

		latestPostEntity = PostEntity.builder()
				.id(2)
				.title("Latest Post title")
				.author(userEntity)
				.createdAt(latestCreatedAt)
				.status(PostStatus.PUBLISHED)
				.build();

		userEntity.getPosts().add(postEntity);
		userEntity.getPosts().add(latestPostEntity);
	}


	@Test
	void toPostUserDTO_whenMaps_thenCorrect() {
		PostUserDTO postUserDTO = mapper.toPostUserDTO(userEntity);
		assertEquals(userEntity.getId(), postUserDTO.getId());
		assertEquals(userEntity.getFirstName(), postUserDTO.getFirstName());
		assertEquals(userEntity.getLastName(), postUserDTO.getLastName());
		assertEquals(userEntity.getAvatar(), postUserDTO.getAvatar());
		assertEquals(userEntity.getMainInstitution().getId(), postUserDTO.getMainInstitution().getId());
		assertEquals(userEntity.getMainInstitution().getName(), postUserDTO.getMainInstitution().getName());
	}

	@Test
	void toExpertPreviewDTO_whenMaps_thenCorrect() {
		ExpertPreviewDTO expertPreviewDTO = mapper.toExpertPreviewDTO(userEntity);

		assertEquals(expertPreviewDTO.getId(), userEntity.getId());
		assertEquals(expertPreviewDTO.getFirstName(), userEntity.getFirstName());
		assertEquals(expertPreviewDTO.getLastName(), userEntity.getLastName());
		assertEquals(expertPreviewDTO.getAvatar(), userEntity.getAvatar());
		assertEquals(expertPreviewDTO.getQualification(), userEntity.getQualification());

		assertEquals(expertPreviewDTO.getMainDirection().getId(), userEntity.getMainDirection().getId());
		assertEquals(expertPreviewDTO.getMainDirection().getName(), userEntity.getMainDirection().getName());

		assertEquals(expertPreviewDTO.getMainInstitution().getId(), userEntity.getMainInstitution().getId());
		assertEquals(expertPreviewDTO.getMainInstitution().getName(), userEntity.getMainInstitution().getName());
		assertEquals(expertPreviewDTO.getMainInstitution().getCity().getName(),
				userEntity.getMainInstitution().getCity().getName());
		assertEquals(expertPreviewDTO.getMainInstitution().getCity().getName(),
				userEntity.getMainInstitution().getCity().getName());

		assertEquals(expertPreviewDTO.getLastAddedPost().getId(), userEntity.getLatestExpertPost().getId());
		assertEquals(expertPreviewDTO.getLastAddedPost().getTitle(), userEntity.getLatestExpertPost().getTitle());

		assertEquals(expertPreviewDTO.getLastAddedPost().getId(), latestPostEntity.getId());
		assertEquals(expertPreviewDTO.getLastAddedPost().getTitle(), latestPostEntity.getTitle());
	}
}