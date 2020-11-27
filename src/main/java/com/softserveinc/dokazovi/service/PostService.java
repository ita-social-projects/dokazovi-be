package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostLatestByDirectionFilterDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

	Page<PostDTO> findAllByStatus(PostStatus postStatus, Pageable pageable);

	Page<PostDTO> findImportantPosts(Pageable pageable);

	Page<PostDTO> findAllByMainDirection(
			PostLatestByDirectionFilterDTO postParamsDTO, PostStatus postStatus, Pageable pageable);
}
