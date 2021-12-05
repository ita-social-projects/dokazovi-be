package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.analytics.GoogleAnalytics;
import com.softserveinc.dokazovi.annotations.DirectionExists;
import com.softserveinc.dokazovi.annotations.OriginExists;
import com.softserveinc.dokazovi.annotations.TagExists;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.direction.DirectionDTOForSavingPost;
import com.softserveinc.dokazovi.dto.origin.OriginDTOForSavingPost;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeIdOnlyDTO;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.DoctorEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.PostFakeViewEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

	@Mock
	private PostRepository postRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private DoctorRepository doctorRepository;
	@Mock
	private DirectionRepository directionRepository;
	@Mock
	private PostMapper postMapper;
	@Mock
	private Pageable pageable;
	@Mock
	private DirectionServiceImpl directionService;

	@InjectMocks
	private PostServiceImpl postService;

	private Page<PostEntity> postEntityPage;

	private UserEntity userEntity;

	@Mock
	private GoogleAnalytics googleAnalytics;

	@BeforeEach
	void init() {
		postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Set<RolePermission> rolePermissions = new HashSet<>();
		rolePermissions.add(RolePermission.SAVE_OWN_PUBLICATION);
		RoleEntity roleEntity = RoleEntity.builder()
				.id(1)
				.name("Doctor")
				.permissions(rolePermissions)
				.build();
		userEntity = UserEntity.builder()
				.id(1)
				.email("test@nail.com")
				.password("12345")
				.role(roleEntity)
				.build();
	}

	@Test
	void findPostById() {
		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.build();
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		postService.findPostById(id);
		verify(postMapper).toPostDTO(eq(postEntity));
	}

	@Test
	void saveFromUser_WhenIdIsPresent_isNull_DoctorRole() {

		Set<RolePermission> rolePermissions = new HashSet<>();
		rolePermissions.add(RolePermission.SAVE_OWN_PUBLICATION);
		RoleEntity roleEntity = RoleEntity.builder()
				.id(1)
				.name("Doctor")
				.permissions(rolePermissions)
				.build();
		UserEntity author = UserEntity.builder()
				.id(1)
				.email("test@nail.com")
				.password("12345")
				.role(roleEntity)
				.firstName("test")
				.lastName("test")
				.avatar("test")
				.status(UserStatus.ACTIVE)
				.createdAt(Timestamp.valueOf(LocalDateTime.now()))
				.doctor(new DoctorEntity())
				.phone("test")
				.userProviderEntities(new HashSet<>())
				.enabled(true)
				.build();

		PostEntity postEntity = PostEntity.builder()
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.build();

		when(postMapper.toPostEntity(any(PostSaveFromUserDTO.class))).thenReturn(postEntity);
		when(userRepository.getOne(any(Integer.class))).thenReturn(author);
		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.authorId(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.postStatus(3)
				.build();

		UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
		postService.saveFromUser(dto, userPrincipal);
		verify(postMapper, times(1)).toPostDTO(any());
	}

	@Test
	void saveFromUser_WhenIdIsPresent_isNull_AdminRole() {
		Set<RolePermission> rolePermissions = new HashSet<>();
		rolePermissions.add(RolePermission.SAVE_PUBLICATION);
		RoleEntity roleEntity = RoleEntity.builder()
				.id(1)
				.name("Administrator")
				.permissions(rolePermissions)
				.build();
		UserEntity author = UserEntity.builder()
				.id(1)
				.email("test@nail.com")
				.password("12345")
				.role(roleEntity)
				.firstName("test")
				.lastName("test")
				.avatar("test")
				.status(UserStatus.ACTIVE)
				.createdAt(Timestamp.valueOf(LocalDateTime.now()))
				.doctor(new DoctorEntity())
				.phone("test")
				.userProviderEntities(new HashSet<>())
				.enabled(true)
				.build();

		PostEntity postEntity = PostEntity.builder()
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.build();

		when(postMapper.toPostEntity(any(PostSaveFromUserDTO.class))).thenReturn(postEntity);
		when(userRepository.getOne(any(Integer.class))).thenReturn(author);
		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.authorId(2)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.postStatus(3)
				.build();

		UserPrincipal userPrincipal = UserPrincipal.create(author);
		postService.saveFromUser(dto, userPrincipal);
		verify(postMapper, times(1)).toPostDTO(any());
	}

	@Test
	void saveFromUser_WhenIdIsWrong_ThrowException() {
		Set<RolePermission> rolePermissions = new HashSet<>();
		rolePermissions.add(RolePermission.DELETE_OWN_POST);
		RoleEntity roleEntity = RoleEntity.builder()
				.id(1)
				.name("Doctor")
				.permissions(rolePermissions)
				.build();
		UserEntity author = UserEntity.builder()
				.id(1)
				.email("test@nail.com")
				.password("12345")
				.role(roleEntity)
				.firstName("test")
				.lastName("test")
				.avatar("test")
				.status(UserStatus.ACTIVE)
				.createdAt(Timestamp.valueOf(LocalDateTime.now()))
				.doctor(new DoctorEntity())
				.phone("test")
				.userProviderEntities(new HashSet<>())
				.enabled(true)
				.build();

		PostEntity postEntity = PostEntity.builder()
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.build();

		when(postMapper.toPostEntity(any(PostSaveFromUserDTO.class))).thenReturn(postEntity);
		when(userRepository.getOne(any(Integer.class))).thenReturn(author);
		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.authorId(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.postStatus(3)
				.build();

		UserPrincipal userPrincipal = UserPrincipal.create(author);
		assertThrows(ForbiddenPermissionsException.class, () -> postService.saveFromUser(dto, userPrincipal));
	}


	@Test
	void findAllByStatus() {
		when(postRepository.findAllByStatus(any(PostStatus.class), any(Pageable.class))).thenReturn(postEntityPage);
		postService.findAllByStatus(PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findImportantPosts() {
		when(postRepository.findAllByImportantIsTrueAndStatusOrderByImportanceOrder(any(PostStatus.class),
				any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findImportantPosts(pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findPostsByAuthorIdAndDirections_WhenWrong_ThrowException() {
		Set<Integer> directions = Set.of(1, 4);
		Pageable pageable = PageRequest.of(0, 12);
		when(postRepository.findPostsByAuthorIdAndDirections(any(), any(), any()))
				.thenThrow(new EntityNotFoundException("Id does not exist"));
		assertThrows(EntityNotFoundException.class, () -> postService
				.findPostsByAuthorIdAndDirections(pageable, 1, directions));
	}

	@Test
	void findPostsByAuthorIdAndDirections() {
		Set<Integer> directions = Set.of(1, 4);
		Pageable pageable = PageRequest.of(0, 12);
		when(postRepository.findPostsByAuthorIdAndDirections(any(), any(), any()))
				.thenReturn(postEntityPage);
		postService.findPostsByAuthorIdAndDirections(pageable, 1, directions);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirection() {
		Integer directionId = 1;
		when(postRepository.findAllByDirectionsContainsAndStatus(
				any(DirectionEntity.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, null, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirectionAndType() {
		Integer directionId = 1;
		Set<Integer> types = Set.of(1, 2, 3);
		when(postRepository.findAllByDirectionsContainsAndTypeIdInAndStatus(
				any(DirectionEntity.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, types, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirectionAndTags() {
		Integer directionId = 1;
		Set<Integer> tags = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByDirectionsContainsAndTagsIdInAndStatus(
				any(DirectionEntity.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, null, tags, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByDirectionAndTypeAndTags() {
		Integer directionId = 1;
		Set<Integer> types = Set.of(1, 2, 3);
		Set<Integer> tags = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByDirectionsContainsAndTypeIdInAndTagsIdInAndStatus(
				any(DirectionEntity.class), anySet(), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByDirection(directionId, types, tags, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpert() {
		Integer expertId = 3;
		when(postRepository.findAllByAuthorIdAndStatusOrderByPublishedAtDesc(
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpertAndTypeAndDirections(expertId, null, null, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpertAndType() {
		Integer expertId = 5;
		Set<Integer> typeId = Set.of(1, 2);
		when(postRepository.findAllByAuthorIdAndTypeIdInAndStatus(any(Integer.class),
				anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpertAndTypeAndDirections(expertId, typeId, null, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpertAndDirections() {
		Integer expertId = 5;
		Set<Integer> directionId = Set.of(2, 3);
		when(postRepository.findPostsByAuthorIdAndDirections(any(Pageable.class), any(Integer.class),
				anySet()))
				.thenReturn(postEntityPage);
		postService.findAllByExpertAndTypeAndDirections(expertId, null, directionId, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpertAndTypeAndDirection() {
		Integer expertId = 5;
		Set<Integer> directionId = Set.of(2, 3);
		Set<Integer> typeId = Set.of(1, 2);
		when(postRepository.findAllByExpertAndByDirectionsAndByPostType(any(Integer.class),
				anySet(), anySet(), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpertAndTypeAndDirections(expertId, typeId, directionId, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpertAndStatus() {
		Integer expertId = 3;
		PostStatus postStatus = PostStatus.DRAFT;
		when(postRepository.findAllByAuthorIdAndStatusOrderByPublishedAtDesc(
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpertAndTypeAndStatus(expertId, null, postStatus, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpertAndTypeAndStatus() {
		Integer expertId = 5;
		Set<Integer> typeId = Set.of(1, 2);
		PostStatus postStatus = PostStatus.DRAFT;
		when(postRepository.findAllByAuthorIdAndTypeIdInAndStatus(any(Integer.class),
				anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpertAndTypeAndStatus(expertId, typeId, postStatus, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPosts() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Set<Integer> typesIds = null;
		Set<Integer> originsIds = null;
		Set<Integer> directionsIds = null;
		Set<Integer> statuses = null;
		String author = "";
		String title = "";
		String startDate = "";
		String endDate = "";

		Mockito.when(postRepository.findAll(any(Pageable.class))).thenReturn(postEntityPage);
		postService.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds,
				originsIds, statuses, title, author,startDate,endDate, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPosts_WhenIdsAreWrong_ReturnEmptyPage() {
		Set<Integer> typesIds = Set.of(1220, 1999);
		Set<Integer> originsIds = Set.of(12340, 1999);
		Set<Integer> directionsIds = Set.of(1234, 1999);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.EPOCH, LocalTime.MIN));
		Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2021,1,3),
				LocalTime.MIN));
		Page<PostEntity> postEntityPage = Page.empty();

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds,
								directionsIds, statusNames,originsIds, title, author,startDate,endDate, pageable))
				.thenReturn(postEntityPage);
		assertEquals(postEntityPage.getContent(),
				postRepository.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(
								typesIds, directionsIds, statusNames, originsIds, title, author,
								startDate,endDate, pageable
						)
						.getContent());
	}

	@Test
	void findAllPostsByDirections() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Set<Integer> typesIds = new HashSet<>();
		Set<Integer> originsIds = new HashSet<>();
		Set<Integer> directionsIds = Set.of(1, 2);
		Set<Integer> statuses = Set.of(5);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		String startDat = "";
		String endDat = "";
		Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.EPOCH, LocalTime.MIN));
		Timestamp endDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds, directionsIds,
								statusNames,originsIds, title, author, startDate, endDate, pageable))
				.thenReturn(postEntityPage);
		postService.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds,
				originsIds, statuses, title, author, startDat, endDat, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPostsByDirections_WhenIdsAreWrong_ReturnEmptyPage() {
		Set<Integer> typesIds = new HashSet<>();
		Set<Integer> originsIds = new HashSet<>();
		Set<Integer> directionsIds = Set.of(-1, -2, -3);
		Set<Integer> statuses = Set.of(5);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		String startDate = "";
		String endDate = "";
		Timestamp timestampStartDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.EPOCH,LocalTime.MIN));
		Timestamp timestampEndDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.MAX));
		Page<PostEntity> postEntityPage = Page.empty();

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds,
								directionsIds, statusNames,originsIds, title, author, timestampStartDate,
								timestampEndDate, pageable))
				.thenReturn(postEntityPage);
		assertEquals(postEntityPage.getContent().size(),
				postService.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds,
								originsIds, statuses, title, author, startDate, endDate, pageable)
						.getContent().size());
	}

	@Test
	void findAllPostsByDirections_ThrowException() {
		Set<Integer> typesIds = new HashSet<>();
		Set<Integer> originsIds = new HashSet<>();
		Set<Integer> directionsIds = null;
		Set<Integer> statuses = Set.of(3);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		String startDate = "";
		String endDate = "";
		Timestamp timestampStartDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.EPOCH,LocalTime.MIN));
		Timestamp timestampEndDate = Timestamp.valueOf(LocalDateTime.now());

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds,
								directionsIds, statusNames,originsIds, title, author,
								timestampStartDate,timestampEndDate, pageable))
				.thenThrow(new EntityNotFoundException("Id does not exist"));
		assertThrows(EntityNotFoundException.class, () -> postService
				.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds, originsIds,
						statuses, title, author,startDate,endDate, pageable));
	}

	@Test
	void findAllByPostTypesAndOrigins() {
		PostEntity postEntity = new PostEntity();
		PostEntity postEntity1 = new PostEntity();
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(postEntity,postEntity1));
		Set<Integer> typesIds = Set.of(1, 2);
		Set<Integer> originsIds = Set.of(2, 3);
		Set<Integer> directionsIds = new HashSet<>();
		Set<Integer> statuses = Set.of(5);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		String startDate = "";
		String endDate = "";
		Timestamp timestampStartDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.EPOCH,LocalTime.MIN));
		Timestamp timestampEndDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.MAX));

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds,
								directionsIds, statusNames,	originsIds, title, author, timestampStartDate,
								timestampEndDate, pageable))
				.thenReturn(postEntityPage);
		postService.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds, originsIds,
				statuses, title, author,startDate,endDate, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPosts_When_Only_StartDateProvided() {
		PostEntity postEntity = new PostEntity();
		PostEntity postEntity1 = new PostEntity();
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(postEntity,postEntity1));
		Set<Integer> typesIds = Set.of(1, 2);
		Set<Integer> originsIds = Set.of(2, 3);
		Set<Integer> directionsIds = new HashSet<>();
		Set<Integer> statuses = Set.of(5);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		String startDate = "01.01.2021";
		String endDate = "";

		Timestamp timestampStartDate = Timestamp.valueOf(LocalDateTime.of(
				LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
				LocalTime.MIN));
		Timestamp timestampEndDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.MAX));

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds,
								directionsIds, statusNames,	originsIds, title, author, timestampStartDate,
								timestampEndDate, pageable))
				.thenReturn(postEntityPage);
		postService.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds, originsIds,
				statuses, title, author,startDate,endDate, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPosts_When_Only_EndDateProvided() {
		PostEntity postEntity = new PostEntity();
		PostEntity postEntity1 = new PostEntity();
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(postEntity,postEntity1));
		Set<Integer> typesIds = Set.of(1, 2);
		Set<Integer> originsIds = Set.of(2, 3);
		Set<Integer> directionsIds = new HashSet<>();
		Set<Integer> statuses = Set.of(5);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		String startDate = "";
		String endDate = "01.06.2021";

		Timestamp timestampStartDate = Timestamp.valueOf(LocalDateTime.of(
				LocalDate.EPOCH,
				LocalTime.MIN));
		Timestamp timestampEndDate = Timestamp.valueOf(LocalDateTime.of(
				LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
				LocalTime.MAX));

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds,
								directionsIds, statusNames,	originsIds, title, author, timestampStartDate,
								timestampEndDate, pageable))
				.thenReturn(postEntityPage);
		postService.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds, originsIds,
				statuses, title, author,startDate,endDate, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPostsByPostTypesAndOrigins_WhenIdsAreWrong_ReturnEmptyPage() {
		Set<Integer> typesIds = Set.of(-1, -2);
		Set<Integer> originsIds = Set.of(-1, -2, -3);
		Set<Integer> directionsIds = new HashSet<>();
		Set<Integer> statuses = Set.of(5);
		Set<String> statusNames = Set.of(PostStatus.PUBLISHED.name());
		String author = "";
		String title = "";
		Page<PostEntity> postEntityPage = Page.empty();
		String startDate = "";
		String endDate = "";
		Timestamp timestampStartDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.EPOCH,LocalTime.MIN));
		Timestamp timestampEndDate = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.MAX));

		Mockito.when(postRepository
						.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typesIds,
								directionsIds, statusNames,originsIds, title, author, timestampStartDate,
								timestampEndDate, pageable))
				.thenReturn(postEntityPage);
		assertEquals(postEntityPage.getContent().size(),
				postService.findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(directionsIds, typesIds,
								originsIds, statuses, title, author,startDate,endDate, pageable)
						.getContent().size());
	}

	@Test
	void deletePostById_WhenExists_isOk_AdminRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.DELETE_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(1);
		roleEntity.setName("Administrator");
		roleEntity.setPermissions(permissions);

		PostTypeDTO postTypeDTO = new PostTypeDTO();
		postTypeDTO.setId(1);
		postTypeDTO.setName("type");

		DirectionDTO directionDTO = new DirectionDTO();
		directionDTO.setId(1);
		directionDTO.setName("name");
		directionDTO.setLabel("label");
		directionDTO.setColor("color");

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(27)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		UserEntity adminUserEntity = UserEntity.builder()
				.id(28)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.author(adminUserEntity)
				.build();

		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		Assertions.assertThat(postService.removePostById(userPrincipal, id, true)).isTrue();
	}

	@Test
	void deletePostById_WhenExists_isOk_DoctorRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.DELETE_OWN_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(3);
		roleEntity.setName("Doctor");
		roleEntity.setPermissions(permissions);

		PostTypeDTO postTypeDTO = new PostTypeDTO();
		postTypeDTO.setId(1);
		postTypeDTO.setName("type");

		DirectionDTO directionDTO = new DirectionDTO();
		directionDTO.setId(1);
		directionDTO.setName("name");
		directionDTO.setLabel("label");
		directionDTO.setColor("color");

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		UserEntity doctorUserEntity = UserEntity.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.author(doctorUserEntity)
				.build();

		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		Assertions.assertThat(postService.removePostById(userPrincipal, id, true)).isTrue();
	}

	@Test
	void deletePostById_WhenNotPermission_throwsException() {
		Set<RolePermission> permissions = new HashSet<>();

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(3);
		roleEntity.setName("Doctor");
		roleEntity.setPermissions(permissions);

		PostTypeDTO postTypeDTO = new PostTypeDTO();
		postTypeDTO.setId(1);
		postTypeDTO.setName("type");

		DirectionDTO directionDTO = new DirectionDTO();
		directionDTO.setId(1);
		directionDTO.setName("name");
		directionDTO.setLabel("label");
		directionDTO.setColor("color");

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		UserEntity doctorUserEntity = UserEntity.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.author(doctorUserEntity)
				.build();

		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		assertThrows(ForbiddenPermissionsException.class, () -> postService.removePostById(userPrincipal, id, true));
	}

	@Test
	void updatePostById_WhenExists_isOk_AdminRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(1);
		roleEntity.setName("Administrator");
		roleEntity.setPermissions(permissions);

		PostTypeIdOnlyDTO postTypeDTO = new PostTypeIdOnlyDTO();
		postTypeDTO.setId(1);

		DirectionDTOForSavingPost directionDTO = new DirectionDTOForSavingPost();
		directionDTO.setId(1);

		Set<@DirectionExists DirectionDTOForSavingPost> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTOForSavingPost originDTO = new OriginDTOForSavingPost();
		originDTO.setId(1);

		Set<@OriginExists OriginDTOForSavingPost> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(27)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		UserEntity adminUserEntity = UserEntity.builder()
				.id(28)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.origins(origins)
				.postStatus(3)
				.build();

		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.author(adminUserEntity)
				.build();

		when(postMapper.updatePostEntityFromDTO(dto, postEntity)).thenReturn(postEntity);
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		Assertions.assertThat(postService.updatePostById(userPrincipal, dto)).isTrue();
	}

	@Test
	void archivePostById_WhenExists_isOk_AdminRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_POST);
		permissions.add(RolePermission.DELETE_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(1);
		roleEntity.setName("Administrator");
		roleEntity.setPermissions(permissions);

		PostTypeIdOnlyDTO postTypeDTO = new PostTypeIdOnlyDTO();
		postTypeDTO.setId(1);

		DirectionDTOForSavingPost directionDTO = new DirectionDTOForSavingPost();
		directionDTO.setId(1);

		Set<@DirectionExists DirectionDTOForSavingPost> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTOForSavingPost originDTO = new OriginDTOForSavingPost();
		originDTO.setId(1);

		Set<@OriginExists OriginDTOForSavingPost> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(27)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		UserEntity adminUserEntity = UserEntity.builder()
				.id(28)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.origins(origins)
				.postStatus(6)
				.build();

		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.author(adminUserEntity)
				.build();

		when(postMapper.updatePostEntityFromDTO(dto, postEntity)).thenReturn(postEntity);
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		Assertions.assertThat(postService.updatePostById(userPrincipal, dto)).isTrue();
	}

	@Test
	void updatePostById_WhenExists_isOk_DoctorRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_OWN_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(3);
		roleEntity.setName("Doctor");
		roleEntity.setPermissions(permissions);

		PostTypeIdOnlyDTO postTypeDTO = new PostTypeIdOnlyDTO();
		postTypeDTO.setId(1);

		DirectionDTOForSavingPost directionDTO = new DirectionDTOForSavingPost();
		directionDTO.setId(1);

		Set<@DirectionExists DirectionDTOForSavingPost> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTOForSavingPost originDTO = new OriginDTOForSavingPost();
		originDTO.setId(1);

		Set<@OriginExists OriginDTOForSavingPost> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		UserEntity doctorUserEntity = UserEntity.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.authorId(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.origins(origins)
				.postStatus(3)
				.build();

		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.author(doctorUserEntity)
				.build();

		when(postMapper.updatePostEntityFromDTO(dto, postEntity)).thenReturn(postEntity);
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		Assertions.assertThat(postService.updatePostById(userPrincipal, dto)).isTrue();
	}

	@Test
	void archivePostById_WhenExists_isOk_DoctorRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_OWN_POST);
		permissions.add(RolePermission.DELETE_OWN_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(3);
		roleEntity.setName("Doctor");
		roleEntity.setPermissions(permissions);

		PostTypeIdOnlyDTO postTypeDTO = new PostTypeIdOnlyDTO();
		postTypeDTO.setId(1);

		DirectionDTOForSavingPost directionDTO = new DirectionDTOForSavingPost();
		directionDTO.setId(1);

		Set<@DirectionExists DirectionDTOForSavingPost> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTOForSavingPost originDTO = new OriginDTOForSavingPost();
		originDTO.setId(1);

		Set<@OriginExists OriginDTOForSavingPost> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		UserEntity doctorUserEntity = UserEntity.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.authorId(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.origins(origins)
				.postStatus(6)
				.build();

		Integer id = 1;
		PostEntity postEntity = PostEntity
				.builder()
				.id(id)
				.author(doctorUserEntity)
				.build();

		when(postMapper.updatePostEntityFromDTO(dto, postEntity)).thenReturn(postEntity);
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(postEntity));
		Assertions.assertThat(postService.updatePostById(userPrincipal, dto)).isTrue();
	}

	@Test
	void deletePostById_WhenNotExists_NotFound_ThrowException_AdminRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.DELETE_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(1);
		roleEntity.setName("Administrator");
		roleEntity.setPermissions(permissions);

		PostTypeDTO postTypeDTO = new PostTypeDTO();
		postTypeDTO.setId(1);
		postTypeDTO.setName("name");

		DirectionDTO directionDTO = new DirectionDTO();
		directionDTO.setId(1);
		directionDTO.setName("name");
		directionDTO.setLabel("label");
		directionDTO.setColor("color");

		Integer id = -1;
		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(27)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		when(postRepository.findById(-1)).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class, () -> postService.removePostById(userPrincipal, id, true));
	}

	@Test
	void deletePostById_WhenNotExists_NotFound_ThrowException_DoctorRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.DELETE_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(3);
		roleEntity.setName("Doctor");
		roleEntity.setPermissions(permissions);

		PostTypeDTO postTypeDTO = new PostTypeDTO();
		postTypeDTO.setId(1);
		postTypeDTO.setName("name");

		DirectionDTO directionDTO = new DirectionDTO();
		directionDTO.setId(1);
		directionDTO.setName("name");
		directionDTO.setLabel("label");
		directionDTO.setColor("color");

		Integer id = -1;
		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		when(postRepository.findById(-1)).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class, () -> postService.removePostById(userPrincipal, id, true));
	}

	@Test
	void updatePostById_WhenNotExists_NotFound_ThrowException_AdminRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(1);
		roleEntity.setName("Administrator");
		roleEntity.setPermissions(permissions);

		PostTypeIdOnlyDTO postTypeDTO = new PostTypeIdOnlyDTO();
		postTypeDTO.setId(1);

		DirectionDTOForSavingPost directionDTO = new DirectionDTOForSavingPost();
		directionDTO.setId(1);

		Set<@DirectionExists DirectionDTOForSavingPost> directions = new HashSet<>();
		directions.add(directionDTO);

		OriginDTOForSavingPost originDTO = new OriginDTOForSavingPost();
		originDTO.setId(1);

		Set<@OriginExists OriginDTOForSavingPost> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(27)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(-1)
				.authorId(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.origins(origins)
				.build();

		when(postRepository.findById(-1)).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class, () -> postService.updatePostById(userPrincipal, dto));
	}

	@Test
	void updatePostById_WhenNotExists_NotFound_ThrowException_DoctorRole() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_POST);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(3);
		roleEntity.setName("Doctor");
		roleEntity.setPermissions(permissions);

		PostTypeIdOnlyDTO postTypeDTO = new PostTypeIdOnlyDTO();
		postTypeDTO.setId(1);

		DirectionDTOForSavingPost directionDTO = new DirectionDTOForSavingPost();
		directionDTO.setId(1);

		Set<@DirectionExists DirectionDTOForSavingPost> directions = new HashSet<>();
		directions.add(directionDTO);

		OriginDTOForSavingPost originDTO = new OriginDTOForSavingPost();
		originDTO.setId(1);

		Set<@OriginExists OriginDTOForSavingPost> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(-1)
				.authorId(1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.origins(origins)
				.build();

		when(postRepository.findById(-1)).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class, () -> postService.updatePostById(userPrincipal, dto));
	}

	@Test
	void findLatestPostsByPostTypesAndOrigin_isOk() {
		Pageable pageable = PageRequest.of(0, 4);
		when(postRepository.findLatestByPostTypeMedia(any(Pageable.class)))
				.thenReturn(postEntityPage);
		when(postRepository.findLatestByOriginVideo(pageable)).thenReturn(postEntityPage);
		when(postRepository.findLatestByPostTypeTranslation(pageable)).thenReturn(postEntityPage);
		when(postRepository.findLatestByPostTypeExpertOpinion(pageable)).thenReturn(postEntityPage);
		postService.findLatestByPostTypesAndOrigins(pageable);
		verify(postMapper, times(8)).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findLatestPostsByPostTypesAndOriginForMobile_isOk() {
		Pageable pageable = PageRequest.of(0, 10);
		when(postRepository.findLatestByPostTypeMedia(any(Pageable.class)))
				.thenReturn(postEntityPage);
		when(postRepository.findLatestByOriginVideo(pageable)).thenReturn(postEntityPage);
		when(postRepository.findLatestByPostTypeTranslation(pageable)).thenReturn(postEntityPage);
		when(postRepository.findLatestByPostTypeExpertOpinion(pageable)).thenReturn(postEntityPage);
		postService.findLatestByPostTypesAndOriginsForMobile(pageable);
		verify(postMapper, times(8)).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findLatestPostsByPostTypesAndOrigin_NotFound() {
		Pageable pageable = PageRequest.of(0, 4);
		when(postRepository.findLatestByPostTypeMedia(any(Pageable.class)))
				.thenReturn(Page.empty());
		when(postRepository.findLatestByOriginVideo(pageable)).thenReturn(Page.empty());
		when(postRepository.findLatestByPostTypeTranslation(pageable)).thenReturn(Page.empty());
		when(postRepository.findLatestByPostTypeExpertOpinion(pageable)).thenReturn(Page.empty());

		postService.findLatestByPostTypesAndOrigins(pageable);
		verify(postMapper, times(0)).toPostDTO(any(PostEntity.class));
		Assertions.assertThat(postService.findLatestByPostTypesAndOrigins(pageable).isEmpty());
	}

	@Test
	void findLatestPostsByPostTypesAndOriginForMobile_NotFound() {
		Pageable pageable = PageRequest.of(0, 10);
		when(postRepository.findLatestByPostTypeMedia(any(Pageable.class)))
				.thenReturn(Page.empty());
		when(postRepository.findLatestByOriginVideo(pageable)).thenReturn(Page.empty());
		when(postRepository.findLatestByPostTypeTranslation(pageable)).thenReturn(Page.empty());
		when(postRepository.findLatestByPostTypeExpertOpinion(pageable)).thenReturn(Page.empty());

		postService.findLatestByPostTypesAndOriginsForMobile(pageable);
		verify(postMapper, times(0)).toPostDTO(any(PostEntity.class));
		assertThat(postService.findLatestByPostTypesAndOriginsForMobile(pageable).isEmpty());
	}

	@Test
	void setPostsAsImportant() {
		Set<Integer> postIds = Set.of(2, 4, 9);
		Assertions.assertThat(postService.setPostsAsImportantWithOrder(postIds));
	}

	@Test
	void getPostViewCount() {
		when(googleAnalytics.getPostViewCount("some")).thenReturn(1);

		assertEquals(1, postService.getPostViewCount("some"));
	}

	@Test
	void getFakeViewsByPostUrl() {
		PostEntity postEntity = PostEntity.builder().id(10).fakeViews(150).build();
		when(postRepository.getFakeViewsByPostId(10)).thenReturn(150);

		assertEquals(150, postService.getFakeViewsByPostUrl("/posts/10"));
	}

	@Test
	void setFakeViewsForPost_withExistPostId() {
		PostEntity postEntity = PostEntity.builder().id(10).build();
		when(postRepository.findById(10))
				.thenReturn(Optional.of(postEntity));
		postService.setFakeViewsForPost(10, 110);
		assertEquals(110, postEntity.getFakeViews());
	}

	@Test
	void setFakeViewsForPost_withNotExistPostId() {
		PostEntity postEntity = PostEntity.builder().id(10).build();

		when(postRepository.findById(10)).thenReturn(Optional.of(postEntity));

		postService.setFakeViewsForPost(10, 110);

		verify(postRepository, times(1)).save(any(PostEntity.class));
	}

	@Test
	void findPublishedNotImportantPostsWithFiltersSortedByImportantImagePresence_isOk() {
		Pageable pageable = PageRequest.of(0, 12);
		when(postRepository.findByDirectionsAndTypesAndOriginsAndStatusAndImportantSortedByImportantImagePresence(
				anySet(), anySet(), anySet(), any(PostStatus.class), anyBoolean(), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findPublishedNotImportantPostsWithFiltersSortedByImportantImagePresence(
				new HashSet<>(), new HashSet<>(), new HashSet<>(), pageable);
		verify(postMapper, times(2)).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findPublishedNotImportantPostsWithFiltersSortedByImportantImagePresence_NotFound() {
		Pageable pageable = PageRequest.of(0, 12);
		when(postRepository.findByDirectionsAndTypesAndOriginsAndStatusAndImportantSortedByImportantImagePresence(
				anySet(), anySet(), anySet(), any(PostStatus.class), anyBoolean(), any(Pageable.class)))
				.thenReturn(Page.empty());
		postService.findPublishedNotImportantPostsWithFiltersSortedByImportantImagePresence(
				Set.of(1), new HashSet<>(), new HashSet<>(), pageable);
		verify(postMapper, times(0)).toPostDTO(any(PostEntity.class));
	}

	@Test
	void getDirectionsFromPostsEntities_bothNull_returnsEmpty() {
		Optional<PostEntity> oldEntity = Optional.empty();
		PostEntity newEntity = null;
		Set<DirectionEntity> result = postService.getDirectionsFromPostsEntities(oldEntity, newEntity);
		assertEquals(0, result.size());
	}

	@Test
	void getDirectionsFromPostsEntities_oldNotNullNewNull_returnsEmpty() {
		Set<DirectionEntity> oldDirections = new HashSet<>();
		oldDirections.add(DirectionEntity.builder()
				.id(2)
				.name("b")
				.build());
		Optional<PostEntity> oldEntity = Optional.of(PostEntity.builder()
				.id(1)
				.directions(oldDirections)
				.build());

		PostEntity newEntity = null;

		Set<DirectionEntity> result = postService.getDirectionsFromPostsEntities(oldEntity, newEntity);
		assertEquals(1, result.size());
	}

	@Test
	void getDirectionsFromPostsEntities_oldNullNewNotNull_returnsEmpty() {
		Optional<PostEntity> oldEntity = Optional.empty();

		Set<DirectionEntity> newDirections = new HashSet<>();
		newDirections.add(DirectionEntity.builder()
				.id(1)
				.name("a")
				.build());
		PostEntity newEntity = PostEntity.builder()
				.id(1)
				.directions(newDirections)
				.build();

		Set<DirectionEntity> result = postService.getDirectionsFromPostsEntities(oldEntity, newEntity);
		assertEquals(1, result.size());
	}

	@Test
	void getDirectionsFromPostsEntities_bothNotNull_returnsEmpty() {
		Set<DirectionEntity> oldDirections = new HashSet<>();
		oldDirections.add(DirectionEntity.builder()
				.id(2)
				.name("b")
				.build());
		Optional<PostEntity> oldEntity = Optional.of(PostEntity.builder()
				.id(1)
				.directions(oldDirections)
				.build());

		Set<DirectionEntity> newDirections = new HashSet<>();
		newDirections.add(DirectionEntity.builder()
				.id(1)
				.name("a")
				.build());
		PostEntity newEntity = PostEntity.builder()
				.id(1)
				.directions(newDirections)
				.build();

		Set<DirectionEntity> result = postService.getDirectionsFromPostsEntities(oldEntity, newEntity);
		assertEquals(2, result.size());
	}

}
