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

import javax.annotation.Nonnull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
				.socialNetworks(Set.of("Facebook", "Twitter"))
				.build();

		doctorEntity = DoctorEntity.builder()
				.qualification("qualification 1")
				.bio("bio 1")
				.mainInstitution(mainInstitution)
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

		assertEquals(userDTO.getSocialNetworks(), userEntity.getSocialNetworks());

		toUserDtoInstitutionsTest(userDTO, userEntity);
		toUserDtoDirections(userDTO, userEntity);
	}

	private void toUserDtoInstitutionsTest(@Nonnull UserDTO userDTO, @Nonnull UserEntity userEntity) {

		if (userEntity.getDoctor() == null) {
			return;
		}

		final UserInstitutionDTO emptyInstitutionDTO = UserInstitutionDTO.builder().build();
		final InstitutionEntity emptyInstitutionEntity = new InstitutionEntity();
		final Set<UserInstitutionDTO> dtoSet = userDTO.getInstitutions();
		final Set<InstitutionEntity> entitySet = userEntity.getDoctor().getInstitutions();

		if (dtoSet == null || entitySet == null) {
			assertNull(dtoSet);
			assertNull(entitySet);
			return;
		}

		assertEquals(dtoSet.size(), entitySet.size());

		if (dtoSet.contains(emptyInstitutionDTO) || entitySet.contains(emptyInstitutionEntity)) {
			assertTrue(dtoSet.contains(emptyInstitutionDTO));
			assertTrue(entitySet.contains(emptyInstitutionEntity));

			dtoSet.remove(emptyInstitutionDTO);
			entitySet.remove(emptyInstitutionEntity);
		}

		List<UserInstitutionDTO> dtoList = new ArrayList<>(dtoSet);
		dtoList.sort(Comparator.comparingInt(UserInstitutionDTO::getId));

		List<InstitutionEntity> entityList = new ArrayList<>(entitySet);
		entityList.sort(Comparator.comparingInt(InstitutionEntity::getId));

		for (int i = 0; i < dtoList.size(); i++) {
			assertEquals(dtoList.get(i).getId(), entityList.get(i).getId());
			assertEquals(dtoList.get(i).getName(), entityList.get(i).getName());
			assertEquals(dtoList.get(i).getCity().getId(), entityList.get(i).getCity().getId());
			assertEquals(dtoList.get(i).getCity().getName(), entityList.get(i).getCity().getName());
		}
	}

	private void toUserDtoDirections(@Nonnull UserDTO userDTO, @Nonnull UserEntity userEntity) {

		if (userEntity.getDoctor() == null) {
			return;
		}

		final DirectionDTO emptyDirectionDTO = DirectionDTO.builder().build();
		final DirectionEntity emptyDirectionEntity = new DirectionEntity();
		final Set<DirectionDTO> dtoSet = userDTO.getDirections();
		final Set<DirectionEntity> entitySet = userEntity.getDoctor().getDirections();

		if (dtoSet == null || entitySet == null) {
			assertNull(dtoSet);
			assertNull(entitySet);
			return;
		}

		assertEquals(dtoSet.size(), entitySet.size());

		if (dtoSet.contains(emptyDirectionDTO) || entitySet.contains(emptyDirectionEntity)) {
			assertTrue(dtoSet.contains(emptyDirectionDTO));
			assertTrue(entitySet.contains(emptyDirectionEntity));

			dtoSet.remove(emptyDirectionDTO);
			entitySet.remove(emptyDirectionEntity);
		}

		List<DirectionDTO> dtoList = new ArrayList<>(dtoSet);
		dtoList.sort(Comparator.comparingInt(DirectionDTO::getId));

		List<DirectionEntity> entityList = new ArrayList<>(entitySet);
		entityList.sort(Comparator.comparingInt(DirectionEntity::getId));

		for (int i = 0; i < dtoList.size(); i++) {
			assertEquals(dtoList.get(i).getId(), entityList.get(i).getId());
			assertEquals(dtoList.get(i).getName(), entityList.get(i).getName());
			assertEquals(dtoList.get(i).getColor(), entityList.get(i).getColor());
			assertEquals(dtoList.get(i).getHasDoctors(), entityList.get(i).getHasDoctors());
			assertEquals(dtoList.get(i).getHasPosts(), entityList.get(i).getHasPosts());
			assertEquals(dtoList.get(i).getLabel(), entityList.get(i).getLabel());
		}
	}

	@Test
	public void tooUserDtoEmptyOrNullCases() {
		userEntity.getDoctor().getMainInstitution().setCity(null);
		UserDTO userDTO = mapper.toUserDTO(userEntity);
		assertNull(userDTO.getMainInstitution().getCity());

		UserEntity userEntity1 = new UserEntity();
		userDTO = mapper.toUserDTO(userEntity1);
		assertNull(userDTO.getId());
		assertNull(userDTO.getBio());
		assertNull(userDTO.getQualification());
		assertNull(userDTO.getMainInstitution());
		assertNull(userDTO.getSocialNetworks());
		toUserDtoInstitutionsTest(userDTO, userEntity1);
		toUserDtoDirections(userDTO, userEntity1);

		userEntity.setDoctor(new DoctorEntity());
		userDTO = mapper.toUserDTO(userEntity);
		assertEquals(userDTO.getId(), userEntity.getId());
		assertNull(userDTO.getBio());
		assertNull(userDTO.getQualification());
		assertNull(userDTO.getMainInstitution());
		toUserDtoInstitutionsTest(userDTO, userEntity);
		toUserDtoDirections(userDTO, userEntity);

		userDTO = mapper.toUserDTO(null);
		assertNull(userDTO);
	}
}
