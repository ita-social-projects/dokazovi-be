package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.direction.DirectionDTOForSavingPost;
import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.dto.origin.OriginDTOForSavingPost;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeIdOnlyDTO;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.user.LatestUserPostDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.OriginEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.entity.TagEntity;
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
	private DirectionEntity direction1;
	private DirectionEntity direction2;
	private TagEntity tag1;
	private TagEntity tag2;
	private OriginEntity origin1;
	private OriginEntity origin2;
	private CityEntity city;

	@BeforeEach
	void init() {
		mainInstitution = InstitutionEntity.builder()
				.id(1)
				.name("Some institution")
				.build();

		city = CityEntity.builder()
				.id(1)
				.name("Kyiv")
				.build();

		mainInstitution.setCity(city);

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
				.bio("Some bio")
				.build();

		author.setDoctor(doctor);

		type = PostTypeEntity.builder()
				.id(1)
				.name("Article")
				.build();

		direction1 = DirectionEntity.builder()
				.id(1)
				.name("Direction 1")
				.build();

		direction2 = DirectionEntity.builder()
				.id(2)
				.name("Direction 2")
				.build();

		tag1 = TagEntity.builder()
				.id(1)
				.tag("COVID")
				.build();

		tag2 = TagEntity.builder()
				.id(2)
				.tag("Cancer")
				.build();

		origin1 = OriginEntity.builder()
				.id(1)
				.name("Title 1")
				.parameters("Some parameters")
				.build();

		origin2 = OriginEntity.builder()
				.id(2)
				.name("Title 2")
				.parameters("Some parameters")
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
				.status(PostStatus.DRAFT)
				.directions(Set.of(direction1, direction2))
				.tags(Set.of(tag1, tag2))
				.origins(Set.of(origin1, origin2))
				.build();

		postSaveFromUserDTO = PostSaveFromUserDTO.builder()
				.id(1)
				.title("PostSaveFromUserDTO title")
				.preview("PostSaveFromUserDTO preview")
				.content("PostSaveFromUserDTO content")
				.videoUrl("PostSaveFromUserDTO videoUrl")
				.previewImageUrl("PostSaveFromUserDTO previewImageUrl")
				.importantImageUrl("Some import image url")
				.importantMobileImageUrl("Some import mobile image url")
				.views(100)
				.realViews(23)
				.fakeViews(77)
				.type(PostTypeIdOnlyDTO.builder()
						.id(1)
						.build())
				.directions(Set.of(
						DirectionDTOForSavingPost.builder()
								.id(1)
								.build(),
						DirectionDTOForSavingPost.builder()
								.id(2)
								.build()))
				.origins(Set.of(
						OriginDTOForSavingPost.builder()
								.id(1)
								.build(),
						OriginDTOForSavingPost.builder()
								.id(2)
								.build()))
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
		assertEquals(postDTO.getAuthor().getBio(), author.getDoctor().getBio());
		assertEquals(
				postDTO.getAuthor().getMainInstitution().getId(), author.getDoctor().getMainInstitution().getId());
		assertEquals(
				postDTO.getAuthor().getMainInstitution().getName(), author.getDoctor().getMainInstitution().getName());
		assertEquals(
				postDTO.getAuthor().getMainInstitution().getCity().getId(), author.getDoctor().getMainInstitution().getCity().getId());
		assertEquals(
				postDTO.getAuthor().getMainInstitution().getCity().getName(), author.getDoctor().getMainInstitution().getCity().getName());
		assertEquals(postDTO.getType().getId(), type.getId());
		assertEquals(postDTO.getType().getName(), type.getName());
		assertEquals(postDTO.getCreatedAt(), post.getCreatedAt());
		assertEquals(postDTO.getModifiedAt(), post.getModifiedAt());
		assertEquals(postDTO.getVideoUrl(), post.getVideoUrl());
		assertEquals(postDTO.getPreviewImageUrl(), post.getPreviewImageUrl());
		assertEquals(postDTO.getContent(), post.getContent());
		assertEquals(postDTO.getStatus(), post.getStatus().name());

		checkToDtoDirections(postDTO, post);
		checkToDtoTags(postDTO, post);
		checkToDtoOrigins(postDTO, post);
	}

	@Test
	public void toPostDtoEmptyNullCases() {
		PostDTO postDTO;

		PostEntity post1 = new PostEntity();
		postDTO = postMapper.toPostDTO(post1);
		assertNull(postDTO.getId());
		assertNull(postDTO.getStatus());
		assertNull(postDTO.getAuthor());
		checkToDtoDirections(postDTO, post1);
		checkToDtoOrigins(postDTO, post1);
		checkToDtoTags(postDTO, post1);

		post.setAuthor(new UserEntity());
		postDTO = postMapper.toPostDTO(post);
		assertNull(postDTO.getAuthor().getId());
		assertNull(postDTO.getAuthor().getFirstName());
		assertNull(postDTO.getAuthor().getLastName());
		assertNull(postDTO.getAuthor().getAvatar());
		assertNull(postDTO.getAuthor().getBio());
		assertNull(postDTO.getAuthor().getMainInstitution());
		checkToDtoDirections(postDTO, post);
		checkToDtoOrigins(postDTO, post);
		checkToDtoTags(postDTO, post);

		author.setDoctor(new DoctorEntity());
		post.setAuthor(author);
		postDTO = postMapper.toPostDTO(post);
		assertNull(postDTO.getAuthor().getBio());

		postDTO = postMapper.toPostDTO(null);
		assertNull(postDTO);
	}

	@Test
	void toPostEntity() {
		PostEntity postEntity = postMapper.toPostEntity(postSaveFromUserDTO);
		assertEquals(postEntity.getId(), postSaveFromUserDTO.getId());
		assertEquals(postEntity.getTitle(), postSaveFromUserDTO.getTitle());
		assertEquals(postEntity.getPreview(), postSaveFromUserDTO.getPreview());
		assertEquals(postEntity.getContent(), postSaveFromUserDTO.getContent());
		assertEquals(postEntity.getVideoUrl(), postSaveFromUserDTO.getVideoUrl());
		assertEquals(postEntity.getPreviewImageUrl(), postSaveFromUserDTO.getPreviewImageUrl());
		assertEquals(postEntity.getImportantImageUrl(), postSaveFromUserDTO.getImportantImageUrl());
		assertEquals(postEntity.getImportantMobileImageUrl(), postSaveFromUserDTO.getImportantMobileImageUrl());
		assertEquals(postEntity.getViews(), postSaveFromUserDTO.getViews());
		assertEquals(postEntity.getRealViews(), postSaveFromUserDTO.getRealViews());
		assertEquals(postEntity.getFakeViews(), postSaveFromUserDTO.getFakeViews());
		assertEquals(postEntity.getType().getId(), postSaveFromUserDTO.getType().getId());

		checkToEntityOrigins(postSaveFromUserDTO, postEntity);
		checkToEntityDirections(postSaveFromUserDTO, postEntity);
	}

	@Test
	public void toPostEntityEmptyNullCase() {
		PostEntity postEntity = postMapper.toPostEntity(null);
		assertNull(postEntity);

		PostSaveFromUserDTO postSaveFromUserDTO1 = new PostSaveFromUserDTO();
		postEntity = postMapper.toPostEntity(postSaveFromUserDTO1);
		assertEquals(0, postEntity.getViews());
		assertEquals(0, postEntity.getRealViews());
		assertEquals(0, postEntity.getFakeViews());
		assertNull(postEntity.getType());
		assertNull(postEntity.getDirections());
		assertNull(postEntity.getOrigins());
		assertNull(postEntity.getAuthor());
		checkToEntityOrigins(postSaveFromUserDTO1, postEntity);
		checkToEntityDirections(postSaveFromUserDTO1, postEntity);
	}

	@Test
	void updatePostEntityFromDTO() {
		postMapper.updatePostEntityFromDTO(postSaveFromUserDTO, post);
		assertEquals(post.getId(), postSaveFromUserDTO.getId());
		assertEquals(post.getTitle(), postSaveFromUserDTO.getTitle());
		assertEquals(post.getPreview(), postSaveFromUserDTO.getPreview());
		assertEquals(post.getContent(), postSaveFromUserDTO.getContent());
		assertEquals(post.getVideoUrl(), postSaveFromUserDTO.getVideoUrl());
		assertEquals(post.getPreviewImageUrl(), postSaveFromUserDTO.getPreviewImageUrl());
		assertEquals(post.getImportantImageUrl(), postSaveFromUserDTO.getImportantImageUrl());
		assertEquals(post.getImportantMobileImageUrl(), postSaveFromUserDTO.getImportantMobileImageUrl());
		assertEquals(post.getRealViews(), postSaveFromUserDTO.getRealViews());
		assertEquals(post.getFakeViews(), postSaveFromUserDTO.getFakeViews());
		assertEquals(post.getType().getId(), postSaveFromUserDTO.getType().getId());

		checkToEntityOrigins(postSaveFromUserDTO, post);
		checkToEntityDirections(postSaveFromUserDTO, post);
	}

	@Test
	void toLatestExpertPostDTO() {
		LatestUserPostDTO latestUserPostDTO = postMapper.toLatestExpertPostDTO(post);

		assertEquals(latestUserPostDTO.getId(), post.getId());
		assertEquals(latestUserPostDTO.getTitle(), post.getTitle());

		latestUserPostDTO = postMapper.toLatestExpertPostDTO(null);
		assertNull(latestUserPostDTO);
	}

	private void checkToDtoDirections(@Nonnull PostDTO postDTO, @Nonnull PostEntity post) {

		if (post.getAuthor() == null || post.getAuthor().getDoctor() == null) {
			return;
		}

		final DirectionDTO emptyDirectionDTO = DirectionDTO.builder().build();
		final DirectionEntity emptyDirectionEntity = new DirectionEntity();
		final Set<DirectionDTO> dtoSet = postDTO.getDirections();
		final Set<DirectionEntity> entitySet = post.getDirections();

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

	private void checkToDtoTags(@Nonnull PostDTO postDTO, @Nonnull PostEntity post) {

		final TagDTO emptyTagDTO = TagDTO.builder().build();
		final TagEntity emptyTagEntity = new TagEntity();
		final Set<TagDTO> dtoSet = postDTO.getTags();
		final Set<TagEntity> entitySet = post.getTags();

		if (dtoSet == null || entitySet == null) {
			assertNull(dtoSet);
			assertNull(entitySet);
			return;
		}

		assertEquals(dtoSet.size(), entitySet.size());

		if (dtoSet.contains(emptyTagDTO) || entitySet.contains(emptyTagEntity)) {
			assertTrue(dtoSet.contains(emptyTagDTO));
			assertTrue(entitySet.contains(emptyTagEntity));

			dtoSet.remove(emptyTagDTO);
			entitySet.remove(emptyTagEntity);
		}

		List<TagDTO> dtoList = new ArrayList<>(dtoSet);
		dtoList.sort(Comparator.comparingInt(TagDTO::getId));

		List<TagEntity> entityList = new ArrayList<>(entitySet);
		entityList.sort(Comparator.comparingInt(TagEntity::getId));

		for (int i = 0; i < dtoList.size(); i++) {
			assertEquals(dtoList.get(i).getId(), entityList.get(i).getId());
			assertEquals(dtoList.get(i).getTag(), entityList.get(i).getTag());
		}

	}

	private void checkToDtoOrigins(@Nonnull PostDTO postDTO, @Nonnull PostEntity post) {

		final OriginDTO emptyOriginDTO = OriginDTO.builder().build();
		final OriginEntity emptyOriginEntity = new OriginEntity();
		final Set<OriginDTO> dtoSet = postDTO.getOrigins();
		final Set<OriginEntity> entitySet = post.getOrigins();

		if (dtoSet == null || entitySet == null) {
			assertNull(dtoSet);
			assertNull(entitySet);
			return;
		}

		assertEquals(dtoSet.size(), entitySet.size());

		if (dtoSet.contains(emptyOriginDTO) || entitySet.contains(emptyOriginEntity)) {
			assertTrue(dtoSet.contains(emptyOriginDTO));
			assertTrue(entitySet.contains(emptyOriginEntity));

			dtoSet.remove(emptyOriginDTO);
			entitySet.remove(emptyOriginEntity);
		}

		List<OriginDTO> dtoList = new ArrayList<>(dtoSet);
		dtoList.sort(Comparator.comparingInt(OriginDTO::getId));

		List<OriginEntity> entityList = new ArrayList<>(entitySet);
		entityList.sort(Comparator.comparingInt(OriginEntity::getId));

		for (int i = 0; i < dtoList.size(); i++) {
			assertEquals(dtoList.get(i).getId(), entityList.get(i).getId());
			assertEquals(dtoList.get(i).getName(), entityList.get(i).getName());
			assertEquals(dtoList.get(i).getParameters(), entityList.get(i).getParameters());
		}

	}

	private void checkToEntityOrigins(@Nonnull PostSaveFromUserDTO postDTO, @Nonnull PostEntity post) {

		final OriginDTOForSavingPost emptyOriginDTO = OriginDTOForSavingPost.builder().build();
		final OriginEntity emptyOriginEntity = new OriginEntity();
		final Set<OriginDTOForSavingPost> dtoSet = postDTO.getOrigins();
		final Set<OriginEntity> entitySet = post.getOrigins();

		if (dtoSet == null || entitySet == null) {
			assertNull(dtoSet);
			assertNull(entitySet);
			return;
		}

		assertEquals(dtoSet.size(), entitySet.size());

		if (dtoSet.contains(emptyOriginDTO) || entitySet.contains(emptyOriginEntity)) {
			assertTrue(dtoSet.contains(emptyOriginDTO));
			assertTrue(entitySet.contains(emptyOriginEntity));

			dtoSet.remove(emptyOriginDTO);
			entitySet.remove(emptyOriginEntity);
		}

		List<OriginDTOForSavingPost> dtoList = new ArrayList<>(dtoSet);
		dtoList.sort(Comparator.comparingInt(OriginDTOForSavingPost::getId));

		List<OriginEntity> entityList = new ArrayList<>(entitySet);
		entityList.sort(Comparator.comparingInt(OriginEntity::getId));

		for (int i = 0; i < dtoList.size(); i++) {
			assertEquals(dtoList.get(i).getId(), entityList.get(i).getId());
		}

	}

	private void checkToEntityDirections(@Nonnull PostSaveFromUserDTO postDTO, @Nonnull PostEntity post) {

		if (post.getAuthor() == null || post.getAuthor().getDoctor() == null) {
			return;
		}

		final DirectionDTOForSavingPost emptyDirectionDTO = DirectionDTOForSavingPost.builder().build();
		final DirectionEntity emptyDirectionEntity = new DirectionEntity();
		final Set<DirectionDTOForSavingPost> dtoSet = postDTO.getDirections();
		final Set<DirectionEntity> entitySet = post.getDirections();

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

		List<DirectionDTOForSavingPost> dtoList = new ArrayList<>(dtoSet);
		dtoList.sort(Comparator.comparingInt(DirectionDTOForSavingPost::getId));

		List<DirectionEntity> entityList = new ArrayList<>(entitySet);
		entityList.sort(Comparator.comparingInt(DirectionEntity::getId));

		for (int i = 0; i < dtoList.size(); i++) {
			assertEquals(dtoList.get(i).getId(), entityList.get(i).getId());
		}
	}

}
