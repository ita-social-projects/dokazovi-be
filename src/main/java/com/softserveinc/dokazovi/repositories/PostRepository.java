package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM posts WHERE important = true LIMIT 3;")
	List<PostEntity> getImportantPosts();
}
