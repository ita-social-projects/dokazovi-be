package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM posts WHERE important = true AND status = 'PUBLISHED';")
	Page<PostEntity> getImportantPosts(Pageable pageable);
}
