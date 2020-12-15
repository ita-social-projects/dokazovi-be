package com.softserveinc.dokazovi.repositories;


import com.softserveinc.dokazovi.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderEntity, Integer> {

	Boolean existsByEmailAndAndName(String email, String name);
}
