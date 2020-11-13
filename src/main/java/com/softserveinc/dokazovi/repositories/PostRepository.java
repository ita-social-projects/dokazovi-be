package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {


	Page<PostEntity> findAllByImportantIsTrueAndStatus(PostStatus status,Pageable pageable);

	Page<PostEntity> findAllByStatus(PostStatus postStatus, Pageable pageable);

}
