package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.analytics.GoogleAnalytics;
import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostMainPageDTO;
import com.softserveinc.dokazovi.dto.post.PostPublishedAtDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.post.PostStatusDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.exception.EntityNotFoundException;
import com.softserveinc.dokazovi.exception.ForbiddenPermissionsException;
import com.softserveinc.dokazovi.exception.StatusNotFoundException;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import com.softserveinc.dokazovi.security.UserPrincipal;
import com.softserveinc.dokazovi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final DirectionServiceImpl directionService;
    private final GoogleAnalytics googleAnalytics;

    @Override
    public PostDTO findPostById(Integer postId) {
        return postMapper.toPostDTO(postRepository.findById(postId).orElse(null));
    }

    @Override
    public PostDTO saveFromUser(PostSaveFromUserDTO postDTO, UserPrincipal userPrincipal) {
        Optional<PostEntity> oldEntity = (postDTO.getId() != null)
                ? postRepository.findById(postDTO.getId())
                : Optional.empty();

        PostEntity mappedEntity = getPostEntityFromPostDTO(postDTO);

        Set<DirectionEntity> directionsToUpdate = getDirectionsFromPostsEntities(
                oldEntity,
                mappedEntity
        );
        mappedEntity.setImportant(false);
        mappedEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        UserEntity userEntity = userRepository.getOne(userPrincipal.getId());

        if (userEntity.getId().equals(postDTO.getAuthorId()) && userPrincipal.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority
                        .getAuthority().equals("SAVE_OWN_PUBLICATION"))) {
            mappedEntity.setAuthor(userEntity);
            PostDTO dto = postMapper.toPostDTO(postRepository.save(mappedEntity));
            directionService.updateDirectionsHasPostsStatusByEntities(directionsToUpdate);
            return dto;
        }

        if (!userEntity.getId().equals(postDTO.getAuthorId()) && userPrincipal.getAuthorities()
                .stream().anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("SAVE_PUBLICATION"))) {
            mappedEntity.setAuthor(userRepository.getOne(postDTO.getAuthorId()));
            PostDTO dto = postMapper.toPostDTO(postRepository.save(mappedEntity));
            directionService.updateDirectionsHasPostsStatusByEntities(directionsToUpdate);
            return dto;
        }

        if (!userEntity.getId().equals(postDTO.getAuthorId()) || userPrincipal.getAuthorities().stream()
                .noneMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("SAVE_OWN_PUBLICATION"))
                && userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("SAVE_PUBLICATION"))) {
            throw new ForbiddenPermissionsException();
        }
        return postMapper.toPostDTO(mappedEntity);
    }

    @Override
    public Page<PostDTO> findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(
            Set<Integer> directionIds, Set<Integer> typeIds, Set<Integer> originIds, Set<Integer> statuses,
            String title, String author, Integer authorId, LocalDate startDate, LocalDate endDate,
            Pageable pageable) {
        boolean isAuthorIdNotSet = authorId == null;

        if (directionIds == null && typeIds == null && originIds == null && statuses == null &&
                startDate == null && endDate == null && title.isEmpty() && author.isEmpty()) {
            if (isAuthorIdNotSet) {
                return postRepository.findAll(pageable)
                        .map(postMapper::toPostDTO);
            } else {
                return postRepository.findAllByAuthorId(authorId, pageable)
                        .map(postMapper::toPostDTO);
            }
        }

        if (pageable.getSort().isSorted()) {
            Sort sort = pageable.getSort().and(Sort.by("modified_at").descending());
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        LocalDate startLocalDate = Optional.ofNullable(startDate).orElse(LocalDate.EPOCH);
        LocalDate endLocalDate = Optional.ofNullable(endDate).orElse(LocalDate.now());
        Timestamp startDateTimestamp = Timestamp.valueOf(startLocalDate.atStartOfDay());
        Timestamp endDateTimestamp = Timestamp.valueOf(endLocalDate.atTime(LocalTime.MAX));
        directionIds = validateValues(directionIds);
        typeIds = validateValues(typeIds);
        originIds = validateValues(originIds);
        PostStatus[] statusesArray = PostStatus.values();
        Set<String> statusNames = statuses == null ? Collections.emptySet() :
                statuses.stream()
                        .map(statusOrdinal -> statusesArray[statusOrdinal])
                        .map(PostStatus::name)
                        .collect(Collectors.toSet());
        try {
            if (isAuthorIdNotSet) {
                return postRepository
                        .findAllByTypesAndStatusAndDirectionsAndOriginsAndTitleAndAuthor(typeIds, directionIds,
                                statusNames,
                                originIds, title, author, startDateTimestamp, endDateTimestamp, pageable)
                        .map(postMapper::toPostDTO);
            } else {
                return postRepository.findAllByAuthorIdByTypesAndStatusAndDirectionsAndOriginsAndTitle(typeIds,
                                directionIds, statusNames, originIds, title, authorId, startDateTimestamp,
                                endDateTimestamp, pageable)
                        .map(postMapper::toPostDTO);
            }
        } catch (Exception e) {
            logger.error(
                    String.format("Fail with posts filter with params typeIds=%s, directionIds=%s, statuses=%s, "
                                    + "originIds=%s, title=%s, author=%s, startDate=%s, endDate=%s",
                            typeIds, directionIds, statuses, originIds, title, author, startDate, endDate));
            throw new EntityNotFoundException("Id does not exist");
        }
    }


    private <T> Set<T> validateValues(Set<T> set) {
        return set != null ? set : Collections.emptySet();
    }

    @Override
    public Page<PostDTO> findAllByStatus(PostStatus postStatus, Pageable pageable) {
        return postRepository.findAllByStatus(postStatus, pageable)
                .map(postMapper::toPostDTO);
    }

    @Override
    public Page<PostDTO> findImportantPosts(Pageable pageable) {
        return postRepository
                .findAllByImportantIsTrueAndStatusOrderByImportanceOrder(PostStatus.PUBLISHED, pageable)
                .map(postMapper::toPostDTO);
    }

    @Override
    public Page<PostDTO> findAllByDirection(
            Integer directionId, Set<Integer> typeId, Set<Integer> tagId, PostStatus postStatus, Pageable pageable) {
        DirectionEntity direction = DirectionEntity.builder()
                .id(directionId)
                .build();
        if (typeId == null && tagId == null) {
            return postRepository.findAllByDirectionsContainsAndStatus(direction, postStatus, pageable)
                    .map(postMapper::toPostDTO);
        } else if (typeId == null) {
            return postRepository.findAllByDirectionsContainsAndTagsIdInAndStatus(
                            direction, tagId, postStatus, pageable)
                    .map(postMapper::toPostDTO);
        } else if (tagId == null) {
            return postRepository.findAllByDirectionsContainsAndTypeIdInAndStatus(
                            direction, typeId, postStatus, pageable)
                    .map(postMapper::toPostDTO);
        }
        return postRepository.findAllByDirectionsContainsAndTypeIdInAndTagsIdInAndStatus(
                        direction, typeId, tagId, postStatus, pageable)
                .map(postMapper::toPostDTO);
    }

    @Override
    public Page<PostDTO> findPostsByAuthorIdAndDirections(
            Pageable pageable, Integer authorId, Set<Integer> directions) {

        return postRepository.findPostsByAuthorIdAndDirections(pageable, authorId, directions)
                .map(postMapper::toPostDTO);
    }

    @Override
    @Transactional
    public Boolean removePostById(UserPrincipal userPrincipal, Integer postId, boolean delete)
            throws EntityNotFoundException {

        Optional<PostEntity> oldEntity = postRepository.findById(postId);

        PostEntity mappedEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Post with %s not found", postId)));

        Integer userId = userPrincipal.getId();
        Integer authorId = mappedEntity.getAuthor().getId();

        final Set<DirectionEntity> directionsToUpdate = getDirectionsFromPostsEntities(
                oldEntity,
                mappedEntity
        );

        if ((userId.equals(authorId) && userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("DELETE_OWN_POST"))) ||
                (!userId.equals(authorId) && userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("DELETE_POST")))) {
            if (delete) {
                postRepository.delete(mappedEntity);
            } else {
                mappedEntity.setStatus(PostStatus.ARCHIVED);
                mappedEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
                postRepository.save(mappedEntity);
            }

        }

        if ((!userId.equals(authorId) || userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("DELETE_OWN_POST")))
                && userPrincipal.getAuthorities().stream().noneMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals("DELETE_POST"))) {
            throw new ForbiddenPermissionsException();
        }
        directionService.updateDirectionsHasPostsStatusByEntities(directionsToUpdate);
        return true;
    }

    @Override
    @Transactional
    public Boolean updatePostById(UserPrincipal userPrincipal, PostSaveFromUserDTO postDTO)
            throws EntityNotFoundException {

        PostEntity mappedEntity = getPostEntityFromPostDTO(postDTO);
        mappedEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));

        Integer userId = userPrincipal.getId();
        Integer authorId = mappedEntity.getAuthor().getId();

        if (mappedEntity.getStatus().equals(PostStatus.ARCHIVED)) {
            return removePostById(userPrincipal, mappedEntity.getId(), false);
        }

        if (userId.equals(authorId) && checkAuthority(userPrincipal, "UPDATE_OWN_POST")) {
            saveEntity(mappedEntity);
        } else if (!userId.equals(authorId) && checkAuthority(userPrincipal, "UPDATE_POST")) {
            mappedEntity.setAuthor(userRepository.getOne(postDTO.getAuthorId()));
            saveEntity(mappedEntity);
        } else {
            throw new ForbiddenPermissionsException();
        }
        return true;
    }

    private void saveEntity(PostEntity mappedEntity) {
        postRepository.save(mappedEntity);
        directionService.updateDirectionsHasPostsStatusByEntities(
                getDirectionsFromPostsEntities(Optional.empty(), mappedEntity)
        );
    }

    private boolean checkAuthority(UserPrincipal userPrincipal, String authority) {
        return userPrincipal.getAuthorities().stream().anyMatch(grantedAuthority ->
                grantedAuthority.getAuthority().equals(authority));
    }

    @Override
    @Transactional
    public Page<PostMainPageDTO> findLatestByPostTypesAndOrigins(Pageable pageable) {
        PostMainPageDTO expertOptions = PostMainPageDTO.builder()
                .fieldName("expertOpinion")
                .postDTOS(postRepository.findLatestByPostTypeExpertOpinion(PageRequest.of(pageable.getPageNumber(), 4))
                        .map(postMapper::toPostDTO).toList()).build();
        PostMainPageDTO media = PostMainPageDTO.builder()
                .fieldName("media")
                .postDTOS(postRepository.findLatestByPostTypeMedia(PageRequest.of(pageable.getPageNumber(), 4))
                        .map(postMapper::toPostDTO).toList()).build();
        PostMainPageDTO translation = PostMainPageDTO.builder()
                .fieldName("translation")
                .postDTOS(postRepository.findLatestByPostTypeTranslation(PageRequest.of(pageable.getPageNumber(), 4))
                        .map(postMapper::toPostDTO).toList()).build();
        PostMainPageDTO video = PostMainPageDTO.builder()
                .fieldName("video")
                .postDTOS(postRepository.findLatestByOriginVideo(PageRequest.of(pageable.getPageNumber(), 4))
                        .map(postMapper::toPostDTO).toList()).build();

        return new PageImpl<>(List.of(expertOptions, media, translation, video));
    }

    @Override
    public Page<PostMainPageDTO> findLatestByPostTypesAndOriginsForMobile(Pageable pageable) {
        PostMainPageDTO expertOptions = PostMainPageDTO.builder()
                .fieldName("expertOpinion")
                .postDTOS(postRepository.findLatestByPostTypeExpertOpinion(PageRequest.of(pageable.getPageNumber(), 10))
                        .map(postMapper::toPostDTO).toList()).build();
        PostMainPageDTO media = PostMainPageDTO.builder()
                .fieldName("media")
                .postDTOS(postRepository.findLatestByPostTypeMedia(PageRequest.of(pageable.getPageNumber(), 10))
                        .map(postMapper::toPostDTO).toList()).build();
        PostMainPageDTO translation = PostMainPageDTO.builder()
                .fieldName("translation")
                .postDTOS(postRepository.findLatestByPostTypeTranslation(PageRequest.of(pageable.getPageNumber(), 10))
                        .map(postMapper::toPostDTO).toList()).build();
        PostMainPageDTO video = PostMainPageDTO.builder()
                .fieldName("video")
                .postDTOS(postRepository.findLatestByOriginVideo(PageRequest.of(pageable.getPageNumber(), 10))
                        .map(postMapper::toPostDTO).toList()).build();

        return new PageImpl<>(List.of(expertOptions, media, translation, video));
    }

    @Override
    public Page<PostDTO> findAllByExpertAndTypeAndDirections(Integer expertId, Set<Integer> typeId,
            Set<Integer> directionId, Pageable pageable) {
        if (typeId == null && directionId == null) {
            return postRepository.findAllByAuthorIdAndStatusOrderByPublishedAtDesc(expertId, PostStatus.PUBLISHED,
                            pageable)
                    .map(postMapper::toPostDTO);
        }
        if (typeId == null) {
            return postRepository.findPostsByAuthorIdAndDirections(pageable, expertId, directionId)
                    .map(postMapper::toPostDTO);
        }
        if (directionId == null) {
            return postRepository
                    .findAllByAuthorIdAndTypeIdInAndStatus(expertId, typeId, PostStatus.PUBLISHED, pageable)
                    .map(postMapper::toPostDTO);
        }
        return postRepository.findAllByExpertAndByDirectionsAndByPostType(expertId, typeId, directionId, pageable)
                .map(postMapper::toPostDTO);
    }

    @Override
    public Page<PostDTO> findAllByExpertAndTypeAndStatus(Integer expertId, Set<Integer> typeId,
            PostStatus postStatus, Pageable pageable) {
        if (typeId == null) {
            return postRepository
                    .findAllByAuthorIdAndStatusOrderByPublishedAtDesc(expertId, postStatus, pageable)
                    .map(postMapper::toPostDTO);
        }
        return postRepository
                .findAllByAuthorIdAndTypeIdInAndStatus(expertId, typeId, postStatus, pageable)
                .map(postMapper::toPostDTO);
    }

    @Override
    @Transactional
    public Boolean setPostsAsImportantWithOrder(Set<Integer> importantPostIds) {
        if (importantPostIds == null) {
            return false;
        }
        postRepository.removeImportantPostsAndOrder(importantPostIds);
        List<Integer> importantPostIdsList = new ArrayList<>(importantPostIds);
        for (int i = 0; i < importantPostIdsList.size(); i++) {
            postRepository.setImportantPostOrder((i + 1), importantPostIdsList.get(i));
        }
        return true;
    }


    private PostEntity getPostEntityFromPostDTO(PostSaveFromUserDTO postDTO) {
        Integer postId = postDTO.getId();
        PostEntity mappedEntity;
        if (postId == null) {
            mappedEntity = postMapper.toPostEntity(postDTO);

        } else {
            PostEntity byId = postRepository.findById(postId)
                    .orElseThrow(EntityNotFoundException::new);
            Integer fakeViewsById = byId.getFakeViews();
            Integer realViewsById = byId.getRealViews();
            mappedEntity = postMapper.updatePostEntityFromDTO(postDTO, byId);

            if (postDTO.getFakeViews() == null) {
                mappedEntity.setFakeViews(fakeViewsById);
            }
            if (postDTO.getRealViews() == null) {
                mappedEntity.setRealViews(realViewsById);
            }
        }

        mappedEntity.setStatus(PostStatus.values()[postDTO.getPostStatus()]);

        return mappedEntity;
    }

    @Override
    public Integer getPostViewCount(String url) {
        return googleAnalytics.getPostViewCount(url);
    }

    @Override
    public Integer getFakeViewsByPostUrl(String url) {
        Scanner scanner = new Scanner(url);
        int id = Integer.parseInt(scanner.findInLine("\\d+"));
        scanner.close();
        return postRepository.getFakeViewsByPostId(id);
    }

    @Override
    public void setFakeViewsForPost(Integer postId, Integer view) {
        Optional<PostEntity> post;
        if ((post = postRepository.findById(postId)).isPresent()) {
            post.get().setFakeViews(view);
            postRepository.save(post.get());
        } else {
            throw new EntityNotFoundException("Post with this id=" + postId + " doesn't exist");
        }
    }

    @Override
    public Page<PostDTO> findPublishedNotImportantPostsWithFiltersSortedByImportantImagePresence(
            Set<Integer> directions, Set<Integer> types, Set<Integer> origins, Pageable pageable) {
        return postRepository.findByDirectionsAndTypesAndOriginsAndStatusAndImportantSortedByImportantImagePresence(
                directions, types, origins, PostStatus.PUBLISHED, false, pageable).map(postMapper::toPostDTO);
    }

    public Set<DirectionEntity> getDirectionsFromPostsEntities(Optional<PostEntity> oldEntity, PostEntity newEntity) {
        Set<DirectionEntity> directionsToUpdate = new TreeSet<>(Comparator.comparing(DirectionEntity::getId));
        if (oldEntity.isPresent() && oldEntity.get().getDirections() != null) {
            directionsToUpdate.addAll(
                    oldEntity
                            .get()
                            .getDirections());
        }
        if (!Objects.isNull(newEntity) && newEntity.getDirections() != null) {
            directionsToUpdate.addAll(newEntity.getDirections());
        }
        return directionsToUpdate;
    }

    /**
     * Updates views for each post by post_id each 15 min
     */

    @Override
    @Transactional
    @Scheduled(cron = "0 0/10 * * * *")
    public void updateRealViews() {
        Map<Integer, Integer> postIdsAndViews = googleAnalytics.getAllPostsViewCount();
        postIdsAndViews.forEach(postRepository::updateRealViews);
    }

    @Override
    @Transactional
    public boolean setPublishedAt(Integer postId, PostPublishedAtDTO publishedAt) {
        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isPresent()) {
            PostEntity postEntity = post.get();
            Timestamp newPublishedAt = publishedAt.getPublishedAt();
            postEntity.setPublishedAt(newPublishedAt);
            if (postEntity.getStatus() != null) {
                if (newPublishedAt.before(new Timestamp(System.currentTimeMillis()))) {
                    if (postEntity.getStatus().equals(PostStatus.PLANNED)) {
                        postEntity.setStatus(PostStatus.PUBLISHED);
                    }
                } else if (postEntity.getStatus().equals(PostStatus.PUBLISHED) ||
                        postEntity.getStatus().equals(PostStatus.ARCHIVED) ||
                        postEntity.getStatus().equals(PostStatus.MODERATION_FIRST_SIGN) ||
                        postEntity.getStatus().equals(PostStatus.MODERATION_SECOND_SIGN)) {
                    postEntity.setStatus(PostStatus.PLANNED);
                }
            }
            postRepository.save(postEntity);
            return true;
        } else {
            throw new EntityNotFoundException("Post with this id=" + postId + " doesn't exist");
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0/5 * * * *")
    public void updatePlannedStatus() {
        List<PostEntity> allByStatus = postRepository.findAllByStatus(PostStatus.PLANNED);
        Timestamp date = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        allByStatus.stream()
                .filter(postEntity -> postEntity.getPublishedAt().before(date))
                .forEach(postEntity -> postEntity.setStatus(PostStatus.PUBLISHED));
    }

    @Override
    @Transactional
    public void setPostStatus(UserPrincipal userPrincipal, Integer postId, PostStatusDTO postStatusDTO)
            throws EntityNotFoundException {
        Integer userId = userPrincipal.getId();

        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isPresent()) {
            PostEntity postEntity = post.get();

            Integer authorId = postEntity.getAuthor().getId();

            String newStatus = postStatusDTO.getStatus();

            if ((userId.equals(authorId) && checkAuthority(userPrincipal, "UPDATE_OWN_POST")) ||
                    (!userId.equals(authorId) && checkAuthority(userPrincipal, "UPDATE_POST"))) {
                if (isValidStatus(newStatus)) {
                    postEntity.setStatus(PostStatus.valueOf(newStatus));
                    postEntity.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
                    saveEntity(postEntity);
                } else {
                    throw new StatusNotFoundException("Status '" + newStatus + "' does not exist");
                }
            } else {
                throw new ForbiddenPermissionsException();
            }
        } else {
            throw new EntityNotFoundException("Post with id " + postId + " does not exist");
        }
    }

    public boolean isValidStatus(String status) {
        return EnumSet.allOf(PostStatus.class)
                .stream()
                .anyMatch(e -> e.name().equals(status));
    }
}
