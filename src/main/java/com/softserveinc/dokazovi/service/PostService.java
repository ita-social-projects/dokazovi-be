package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostMainPageDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Set;

public interface PostService {

	PostDTO findPostById(Integer postId);

	Page<PostDTO> findAllByStatus(PostStatus postStatus, Pageable pageable);

	Page<PostDTO> findImportantPosts(Pageable pageable);

	Page<PostDTO> findAllByDirection(
			Integer directionId, Set<Integer> typeId, Set<Integer> tagId, PostStatus postStatus, Pageable pageable);

	PostDTO saveFromUser(PostSaveFromUserDTO postSaveDTO, UserPrincipal user);

	Page<PostDTO> findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(
			Set<Integer> directionId, Set<Integer> typeId, Set<Integer> originId, Set<Integer> statuses,
			String title, String author, String startDate, String endDate, Pageable pageable);

	Page<PostDTO> findPostsByAuthorIdAndDirections(
			Pageable pageable, Integer expertId, Set<Integer> directions);

	Boolean removePostById(UserPrincipal userId, Integer postId, boolean delete);

	Boolean updatePostById(UserPrincipal userId, PostSaveFromUserDTO postSaveDTO);

	Page<PostMainPageDTO> findLatestByPostTypesAndOrigins(Pageable pageable);

	Page<PostMainPageDTO> findLatestByPostTypesAndOriginsForMobile(Pageable pageable);

	Page<PostDTO> findAllByExpertAndTypeAndDirections(Integer expertId, Set<Integer> typeId, Set<Integer> directionId,
			Pageable pageable);

	Page<PostDTO> findAllByExpertAndTypeAndStatus(Integer expertId, Set<Integer> typeId,
			PostStatus postStatus, Pageable pageable);

	Boolean setPostsAsImportantWithOrder(Set<Integer> importantPostIds);

	Integer getPostViewCount(String url);

	Integer getFakeViewsByPostUrl(String url);

	void setFakeViewsForPost(Integer postId, Integer view);

	Page<PostDTO> findPublishedNotImportantPostsWithFiltersSortedByImportantImagePresence(
			Set<Integer> directionIds, Set<Integer> typeIds, Set<Integer> originIds, Pageable pageable);
}
