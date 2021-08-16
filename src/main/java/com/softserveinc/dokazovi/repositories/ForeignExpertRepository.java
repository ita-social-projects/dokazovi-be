package com.softserveinc.dokazovi.repositories;

import com.softserveinc.dokazovi.entity.ForeignExpertEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ForeignExpertRepository extends JpaRepository<ForeignExpertEntity, Integer> {
	@Query(
			nativeQuery = true,
			value = "SELECT e.* FROM FOREIGN_EXPERTS e " +
					"WHERE e.full_name LIKE :searchQuery || '%'",
			countQuery = "SELECT count(*) FROM FOREIGN_EXPERTS " +
					"WHERE full_name LIKE :searchQuery || '%'"
	)
	Page<ForeignExpertEntity> searchByFullName(String searchQuery, Pageable pageable);
}
