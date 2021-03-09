package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.RoleEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.RolePermission;
import com.softserveinc.dokazovi.exception.InvalidIdDtoException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.DoctorRepository;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
		assertThrows(InvalidIdDtoException.class, () -> postService.saveFromUser(dto, userPrincipal));
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
}