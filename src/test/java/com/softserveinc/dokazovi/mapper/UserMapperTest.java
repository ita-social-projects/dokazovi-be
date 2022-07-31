package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.dto.user.UserInstitutionDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {

	private final Timestamp createdAt = Timestamp.valueOf("1991-05-22 10:10:10.0");
	private final Timestamp latestCreatedAt = Timestamp.valueOf("1992-05-22 10:10:10.0");
	private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
	private UserEntity userEntity;
	private DoctorEntity doctorEntity;
	private InstitutionEntity mainInstitution;
	private InstitutionEntity institution1;
	private DirectionEntity direction1;
	private DirectionEntity direction2;
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

		direction1 = DirectionEntity.builder()
				.id(1)
				.name("Direction 1")
				.build();

		direction2 = DirectionEntity.builder()
				.id(2)
				.name("Direction 2")
				.build();

		userEntity = UserEntity.builder()
				.id(1)
				.firstName("Some firstname")
				.lastName("Some lastname")
				.email("mail@mail.com")
				.phone("380990099009")
				.avatar("Some avatar url")
				.build();

		doctorEntity = DoctorEntity.builder()
				.qualification("qualification 1")
				.bio("bio 1")
				.mainInstitution(mainInstitution)
				.socialNetwork("Facebook")
				.build();

		userEntity.setDoctor(doctorEntity);

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
		doctorEntity.setDirections(Set.of(direction1, direction2));
		doctorEntity.setInstitutions(Set.of(mainInstitution, institution1));
	}

	@Test
	void toUserDTO() {
		UserDTO userDTO = mapper.toUserDTO(userEntity);

		assertEquals(userDTO.getId(), userEntity.getId());
		assertEquals(userDTO.getFirstName(), userEntity.getFirstName());
		assertEquals(userDTO.getLastName(), userEntity.getLastName());
		assertEquals(userDTO.getEmail(), userEntity.getEmail());
		assertEquals(userDTO.getQualification(), userEntity.getDoctor().getQualification());
		assertEquals(userDTO.getPhone(), userEntity.getPhone());
		assertEquals(userDTO.getAvatar(), userEntity.getAvatar());
		assertEquals(userDTO.getBio(), userEntity.getDoctor().getBio());

		assertEquals(userDTO.getMainInstitution().getId(), userEntity.getDoctor().getMainInstitution().getId());
		assertEquals(userDTO.getMainInstitution().getName(), userEntity.getDoctor().getMainInstitution().getName());
		assertEquals(userDTO.getMainInstitution().getCity().getName(),
				userEntity.getDoctor().getMainInstitution().getCity().getName());
		assertEquals(userDTO.getMainInstitution().getCity().getName(),
				userEntity.getDoctor().getMainInstitution().getCity().getName());

		assertEquals(userDTO.getLastAddedPost().getId(), userEntity.getLatestExpertPost().getId());
		assertEquals(userDTO.getLastAddedPost().getTitle(), userEntity.getLatestExpertPost().getTitle());

		assertEquals(userDTO.getLastAddedPost().getId(), latestPostEntity.getId());
		assertEquals(userDTO.getLastAddedPost().getTitle(), latestPostEntity.getTitle());

		assertEquals(userDTO.getSocialNetwork(), userEntity.getDoctor().getSocialNetwork());

	}

	@Test
	public void toUserDtoInstitutions() {
		UserDTO userDTO = mapper.toUserDTO(userEntity);

		assertEquals(userDTO.getInstitutions().size(), userEntity.getDoctor().getInstitutions().size());

		List<UserInstitutionDTO> institutionDTOList = new ArrayList<>(userDTO.getInstitutions());
		institutionDTOList.sort(Comparator.comparingInt(UserInstitutionDTO::getId));

		List<InstitutionEntity> institutionEntityList = new ArrayList<>(userEntity.getDoctor().getInstitutions());
		institutionEntityList.sort(Comparator.comparingInt(InstitutionEntity::getId));

		for (int i = 0; i < institutionDTOList.size(); i++) {
			assertEquals(institutionDTOList.get(i).getId(), institutionEntityList.get(i).getId());
			assertEquals(institutionDTOList.get(i).getName(), institutionEntityList.get(i).getName());
			assertEquals(institutionDTOList.get(i).getCity().getId(), institutionEntityList.get(i).getCity().getId());
			assertEquals(institutionDTOList.get(i).getCity().getName(), institutionEntityList.get(i).getCity().getName());
		}
	}

	@Test
	public void toUserDtoDirections() {
		UserDTO userDTO = mapper.toUserDTO(userEntity);

		assertEquals(userDTO.getDirections().size(), userEntity.getDoctor().getDirections().size());

		List<DirectionDTO> directionDTOList = new ArrayList<>(userDTO.getDirections());
		directionDTOList.sort(Comparator.comparingInt(DirectionDTO::getId));

		List<DirectionEntity> directionEntityList = new ArrayList<>(userEntity.getDoctor().getDirections());
		directionEntityList.sort(Comparator.comparingInt(DirectionEntity::getId));

		for (int i = 0; i < directionDTOList.size(); i++) {
			assertEquals(directionDTOList.get(i).getId(), directionEntityList.get(i).getId());
			assertEquals(directionDTOList.get(i).getName(), directionEntityList.get(i).getName());
			assertEquals(directionDTOList.get(i).getColor(), directionEntityList.get(i).getColor());
			assertEquals(directionDTOList.get(i).getHasDoctors(), directionEntityList.get(i).getHasDoctors());
			assertEquals(directionDTOList.get(i).getHasPosts(), directionEntityList.get(i).getHasPosts());
			assertEquals(directionDTOList.get(i).getLabel(), directionEntityList.get(i).getLabel());
		}
	}

	@Test
	public void tooUserDtoEmptyOrNullCases() {
		userEntity.getDoctor().getMainInstitution().setCity(null);
		UserDTO userDTO = mapper.toUserDTO(userEntity);
		assertNull(userDTO.getMainInstitution().getCity());

		userDTO = mapper.toUserDTO(new UserEntity());
		assertNull(userDTO.getBio());
		assertNull(userDTO.getInstitutions());
		assertNull(userDTO.getQualification());
		assertNull(userDTO.getMainInstitution());
		assertNull(userDTO.getDirections());
		assertNull(userDTO.getSocialNetwork());

		userEntity.setDoctor(new DoctorEntity());
		userDTO = mapper.toUserDTO(userEntity);
		assertNull(userDTO.getBio());
		assertNull(userDTO.getInstitutions());
		assertNull(userDTO.getQualification());
		assertNull(userDTO.getMainInstitution());
		assertNull(userDTO.getDirections());
		assertNull(userDTO.getSocialNetwork());

		userDTO = mapper.toUserDTO(null);
		assertNull(userDTO);
	}

}
