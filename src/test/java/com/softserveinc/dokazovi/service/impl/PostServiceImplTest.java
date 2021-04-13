package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.annotations.DirectionExists;
import com.softserveinc.dokazovi.annotations.OriginExists;
import com.softserveinc.dokazovi.annotations.TagExists;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.InvalidIdDtoException;
import com.softserveinc.dokazovi.mapper.PostMapper;
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
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
	private PostMapper postMapper;
	@Mock
	private Pageable pageable;

	@InjectMocks
	private PostServiceImpl postService;

	private Page<PostEntity> postEntityPage;

	private UserEntity userEntity;


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
	void saveFromUser_WhenIdIsNull() {
		when(postMapper.toPostEntity(any(PostSaveFromUserDTO.class))).thenReturn(new PostEntity());
		when(userRepository.getOne(any(Integer.class))).thenReturn(userEntity);
		UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
		postService.saveFromUser(new PostSaveFromUserDTO(), userPrincipal);
		verify(postMapper, times(1)).toPostEntity(any(PostSaveFromUserDTO.class));
		verify(postRepository, times(1)).save(any(PostEntity.class));
		verify(postMapper, times(1)).toPostDTO(any());
	}

	@Test
	void saveFromUser_WhenIdIsPresent_isOk() {
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.of(new PostEntity()));
		when(postMapper.updatePostEntityFromDTO(any(), any())).thenReturn(new PostEntity());
		when(userRepository.getOne(any(Integer.class))).thenReturn(userEntity);
		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.build();
		UserPrincipal userPrincipal = UserPrincipal.create(userEntity);
		postService.saveFromUser(dto, userPrincipal);
		verify(postMapper, times(1)).updatePostEntityFromDTO(any(), any());
		verify(postRepository, times(1)).findById(any());
		verify(postMapper, times(1)).toPostDTO(any());
	}

	@Test
	void saveFromUser_WhenIdIsWrong_ThrowException() {
		when(postRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(1)
				.build();
		UserPrincipal userPrincipal = new UserPrincipal();
		assertThrows(EntityNotFoundException.class, () -> postService.saveFromUser(dto, userPrincipal));
	}


	@Test
	void findAllByStatus() {
		when(postRepository.findAllByStatus(any(PostStatus.class), any(Pageable.class))).thenReturn(postEntityPage);
		postService.findAllByStatus(PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findImportantPosts() {
		when(postRepository.findAllByImportantIsTrueAndStatus(any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findImportantPosts(pageable);
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
		when(postRepository.findAllByAuthorIdAndStatus(
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpert(expertId, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByExpertAndType() {
		Integer expertId = 5;
		Set<Integer> typeId = Set.of(1, 2);
		when(postRepository.findAllByAuthorIdAndTypeIdInAndStatus(any(Integer.class),
				anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByExpert(expertId, typeId, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPosts() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Set<Integer> typesIds = null;
		Set<Integer> originsIds = null;
		Set<Integer> directionsIds = null;

		Mockito.when(postRepository.findAll(any(Pageable.class))).thenReturn(postEntityPage);
		postService.findAllByDirectionsAndByPostTypesAndByOrigins(directionsIds, typesIds, originsIds, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPosts_WhenIdsAreWrong_ReturnEmptyPage() {
		Set<Integer> typesIds = Set.of(1220, 1999);
		Set<Integer> originsIds = Set.of(12340, 1999);
		Set<Integer> directionsIds = Set.of(1234, 1999);
		Page<PostEntity> postEntityPage = Page.empty();

		Mockito.when(postRepository
				.findAllByDirectionsAndByPostTypesAndByOrigins(typesIds, originsIds, directionsIds, pageable))
				.thenReturn(postEntityPage);
		assertEquals(postEntityPage.getContent(),
				postService.findAllByDirectionsAndByPostTypesAndByOrigins(directionsIds, typesIds, originsIds, pageable)
						.getContent());
	}

	@Test
	void findAllPostsByDirections() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Set<Integer> typesIds = new HashSet<>();
		Set<Integer> originsIds = new HashSet<>();
		Set<Integer> directionsIds = Set.of(1, 2);

		Mockito.when(postRepository
				.findAllByDirectionsAndByPostTypesAndByOrigins(typesIds, originsIds, directionsIds, pageable))
				.thenReturn(postEntityPage);
		postService.findAllByDirectionsAndByPostTypesAndByOrigins(directionsIds, typesIds, originsIds, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPostsByDirections_WhenIdsAreWrong_ReturnEmptyPage() {
		Set<Integer> typesIds = new HashSet<>();
		Set<Integer> originsIds = new HashSet<>();
		Set<Integer> directionsIds = Set.of(-1, -2, -3);
		Page<PostEntity> postEntityPage = Page.empty();

		Mockito.when(postRepository
				.findAllByDirectionsAndByPostTypesAndByOrigins(typesIds, originsIds, directionsIds, pageable))
				.thenReturn(postEntityPage);
		assertEquals(postEntityPage.getContent(),
				postService.findAllByDirectionsAndByPostTypesAndByOrigins(directionsIds, typesIds, originsIds, pageable)
						.getContent());
	}

	@Test
	void findAllPostsByDirections_ThrowException() {
		Set<Integer> typesIds = new HashSet<>();
		Set<Integer> originsIds = new HashSet<>();
		Set<Integer> directionsIds = null;

		Mockito.when(postRepository
				.findAllByDirectionsAndByPostTypesAndByOrigins(typesIds, originsIds, directionsIds, pageable))
				.thenThrow(new EntityNotFoundException("Id does not exist"));
		assertThrows(EntityNotFoundException.class, () -> postService
				.findAllByDirectionsAndByPostTypesAndByOrigins(directionsIds, typesIds, originsIds, pageable));
	}

	@Test
	void findAllByPostTypesAndOrigins() {
		Page<PostEntity> postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
		Set<Integer> typesIds = Set.of(1, 2);
		Set<Integer> originsIds = Set.of(2, 3);
		Set<Integer> directionsIds = new HashSet<>();

		Mockito.when(postRepository
				.findAllByDirectionsAndByPostTypesAndByOrigins(typesIds, originsIds, directionsIds, pageable))
				.thenReturn(postEntityPage);
		postService.findAllByDirectionsAndByPostTypesAndByOrigins(directionsIds, typesIds, originsIds, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllPostsByPostTypesAndOrigins_WhenIdsAreWrong_ReturnEmptyPage() {
		Set<Integer> typesIds = Set.of(-1, -2);
		Set<Integer> originsIds = Set.of(-1, -2, -3);
		Set<Integer> directionsIds = new HashSet<>();
		Page<PostEntity> postEntityPage = Page.empty();

		Mockito.when(postRepository
				.findAllByDirectionsAndByPostTypesAndByOrigins(typesIds, originsIds, directionsIds, pageable))
				.thenReturn(postEntityPage);
		assertEquals(postEntityPage.getContent(),
				postService.findAllByDirectionsAndByPostTypesAndByOrigins(directionsIds, typesIds, originsIds, pageable)
						.getContent());
	}

	@Test
	void archivePostById_WhenExists_isOk_Admin() {
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
				.id(27)
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
		Assertions.assertThat(postService.archivePostById(userPrincipal, id)).isTrue();
	}

	@Test
	void archivePostById_WhenExists_isOk_Doctor() {
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
		Assertions.assertThat(postService.archivePostById(userPrincipal, id)).isTrue();
	}

	@Test
	void updatePostById_WhenExists_isOk_Admin() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_POST);

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

		Set<@DirectionExists DirectionDTO> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTO originDTO = new OriginDTO();
		originDTO.setId(1);
		originDTO.setName("name");
		originDTO.setParameter("param");

		Set<@OriginExists OriginDTO> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(27)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		UserEntity adminUserEntity = UserEntity.builder()
				.id(27)
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
				.tags(tags)
				.origins(origins)
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
	void updatePostById_WhenExists_isOk_Doctor() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_OWN_POST);

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

		Set<@DirectionExists DirectionDTO> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTO originDTO = new OriginDTO();
		originDTO.setId(1);
		originDTO.setName("name");
		originDTO.setParameter("param");

		Set<@OriginExists OriginDTO> origins = new HashSet<>();
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
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.tags(tags)
				.origins(origins)
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
	void archivePostById_WhenNotExists_NotFound_ThrowException_Admin() {
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
		assertThrows(EntityNotFoundException.class, () -> postService.archivePostById(userPrincipal, id));
	}

	@Test
	void archivePostById_WhenNotExists_NotFound_ThrowException_Doctor() {
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
		assertThrows(EntityNotFoundException.class, () -> postService.archivePostById(userPrincipal, id));
	}

	@Test
	void updatePostById_WhenNotExists_NotFound_ThrowException_Admin() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_POST);

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

		Set<@DirectionExists DirectionDTO> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTO originDTO = new OriginDTO();
		originDTO.setId(1);
		originDTO.setName("name");
		originDTO.setParameter("param");

		Set<@OriginExists OriginDTO> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(27)
				.email("admin@mail.com")
				.password("$2y$10$GtQSp.P.EyAtCgUD2zWLW.01OBz409TGPl/Jo3U30Tig3YbbpIFv2")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(-1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.tags(tags)
				.origins(origins)
				.build();

		when(postRepository.findById(-1)).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class, () -> postService.updatePostById(userPrincipal, dto));
	}

	@Test
	void updatePostById_WhenNotExists_NotFound_ThrowException_Doctor() {
		Set<RolePermission> permissions = new HashSet<>();
		permissions.add(RolePermission.UPDATE_POST);

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

		Set<@DirectionExists DirectionDTO> directions = new HashSet<>();
		directions.add(directionDTO);

		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(1);
		tagDTO.setTag("tag");

		Set<@TagExists TagDTO> tags = new HashSet<>();
		tags.add(tagDTO);

		OriginDTO originDTO = new OriginDTO();
		originDTO.setId(1);
		originDTO.setName("name");
		originDTO.setParameter("param");

		Set<@OriginExists OriginDTO> origins = new HashSet<>();
		origins.add(originDTO);

		UserPrincipal userPrincipal = UserPrincipal.builder()
				.id(38)
				.email("doctor@mail.com")
				.password("$2a$10$ubeFvFhz0/P5js292OUaee9QxaBsI7cvoAmSp1inQ0MxI/gxazs8O")
				.role(roleEntity)
				.build();

		PostSaveFromUserDTO dto = PostSaveFromUserDTO.builder()
				.id(-1)
				.title("title")
				.videoUrl("videoUrl")
				.previewImageUrl("previewImageUrl")
				.preview("preview")
				.content("content")
				.type(postTypeDTO)
				.directions(directions)
				.tags(tags)
				.origins(origins)
				.build();

		when(postRepository.findById(-1)).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class, () -> postService.updatePostById(userPrincipal, dto));
	}
}