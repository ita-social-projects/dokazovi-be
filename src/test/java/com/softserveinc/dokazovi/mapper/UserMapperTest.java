package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.dto.user.UserDTO;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

	private final Timestamp createdAt = Timestamp.valueOf("1991-05-22 10:10:10.0");
	private final Timestamp latestCreatedAt = Timestamp.valueOf("1992-05-22 10:10:10.0");
	private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
	private UserEntity userEntity;
	private InstitutionEntity mainInstitution;
	private InstitutionEntity institution1;
	private DirectionEntity mainUserDirection;
	private DirectionEntity direction1;
	private CityEntity cityEntity1;
	private CityEntity cityEntity2;
	private PostEntity postEntity;
	private PostEntity latestPostEntity;

	@BeforeEach
	void init() {
		cityEntity1 = CityEntity.builder()
				.id(1)
				.name("City name 1")
				.build();

		cityEntity2 = CityEntity.builder()
				.id(2)
				.name("City name 1")
				.build();

		mainInstitution = InstitutionEntity.builder()
				.id(1)
				.name("Main institution")
				.city(cityEntity1)
				.build();

		institution1 = InstitutionEntity.builder()
				.id(2)
				.name("Institution 1")
				.city(cityEntity2)
				.build();

		mainUserDirection = DirectionEntity.builder()
				.id(1)
				.name("Main direction")
				.build();

		direction1 = DirectionEntity.builder()
				.id(2)
				.name("Direction 1")
				.build();

		userEntity = UserEntity.builder()
				.id(1)
				.firstName("Some firstname")
				.lastName("Some lastname")
				.email("mail@mail.com")
				.qualification("qualification 1")
				.phone("380990099009")
				.avatar("Some avatar url")
				.bio("bio 1")
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

		userEntity.setPosts(Set.of(postEntity, latestPostEntity));
		userEntity.setDirections(Set.of(mainUserDirection, direction1));
		userEntity.setInstitutions(Set.of(mainInstitution, institution1));
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
	void toUserDTO() {
		UserDTO userDTO = mapper.toUserDTO(userEntity);

		assertEquals(userDTO.getId(), userEntity.getId());
		assertEquals(userDTO.getFirstName(), userEntity.getFirstName());
		assertEquals(userDTO.getLastName(), userEntity.getLastName());
		assertEquals(userDTO.getEmail(), userEntity.getEmail());
		assertEquals(userDTO.getQualification(), userEntity.getQualification());
		assertEquals(userDTO.getPhone(), userEntity.getPhone());
		assertEquals(userDTO.getAvatar(), userEntity.getAvatar());
		assertEquals(userDTO.getBio(), userEntity.getBio());

		assertEquals(userDTO.getMainDirection().getId(), userEntity.getMainDirection().getId());
		assertEquals(userDTO.getMainDirection().getName(), userEntity.getMainDirection().getName());

		assertEquals(userDTO.getMainInstitution().getId(), userEntity.getMainInstitution().getId());
		assertEquals(userDTO.getMainInstitution().getName(), userEntity.getMainInstitution().getName());
		assertEquals(userDTO.getMainInstitution().getCity().getName(),
				userEntity.getMainInstitution().getCity().getName());
		assertEquals(userDTO.getMainInstitution().getCity().getName(),
				userEntity.getMainInstitution().getCity().getName());

		assertEquals(userDTO.getLastAddedPost().getId(), userEntity.getLatestExpertPost().getId());
		assertEquals(userDTO.getLastAddedPost().getTitle(), userEntity.getLatestExpertPost().getTitle());

		assertEquals(userDTO.getLastAddedPost().getId(), latestPostEntity.getId());
		assertEquals(userDTO.getLastAddedPost().getTitle(), latestPostEntity.getTitle());
	}
}