package com.softserveinc.dokazovi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * The Region Entity is responsible for correlating with
 * Regions table in the database.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "region_entity")
@Table(name = "regions")
public class RegionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Integer id;

	private String name;

	private Boolean usersPresent;

	@OneToMany(mappedBy = "region")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<CityEntity> cities;
}
