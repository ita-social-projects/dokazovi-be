package com.softserveinc.dokazovi.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity(name = "city_entity")
@Table(name = "cities")
public class CityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private Integer id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private RegionEntity region;

  @Builder
	public CityEntity(Integer id, String name, RegionEntity region) {
    this.id = id;
		this.name = name;
		this.region = region;
	}
}
