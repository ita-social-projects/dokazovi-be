package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PostService {

	PostDTO findPostById(Integer postId);

	Page<PostDTO> findAllByStatus(PostStatus postStatus, Pageable pageable);

	Page<PostDTO> findImportantPosts(Pageable pageable);

	Page<PostDTO> findAllByDirection(
			Integer directionId, Set<Integer> typeId, Set<Integer> tagId, PostStatus postStatus, Pageable pageable);

	Page<PostDTO> findAllByExpert(Integer expertId, Set<Integer> typeId, PostStatus postStatus, Pageable pageable);

	PostDTO saveFromUser(PostSaveFromUserDTO postSaveDTO, UserEntity user);
}
